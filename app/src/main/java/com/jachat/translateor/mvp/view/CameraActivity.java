package com.jachat.translateor.mvp.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jachat.translateor.MainActivity;
import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseActivity;
import com.jachat.translateor.base.BaseFragment;
import com.jachat.translateor.base.BasePresenter;
import com.jachat.translateor.config.App;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.utils.BitmapUtils;
import com.jachat.translateor.utils.PopWindowUtils;
import com.jachat.translateor.utils.SPUtils;
import com.jachat.translateor.utils.WheelView;
import com.jachat.translateor.utils.mycamer.CameraPreview;
import com.jachat.translateor.utils.mycamer.GenericProgressDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//照相页面
public class CameraActivity extends BaseFragment implements View.OnTouchListener, OnClickListener {
    private CameraPreview preview;
    private Camera camera;
    private Context mContext;
    @BindView(R.id.focus_index)
    View focusIndex;
    private ImageView flashBtn;
    private int mCurrentCameraId = 0; // 1是前置 0是后置
    @BindView(R.id.surfaceView)
    SurfaceView mSurfaceView;
    @BindView(R.id.layout)
    FrameLayout mFragmentLaytou;


    private String mRecognitionluange = LngConstant.LANGUAGE_CHINESE;//识别语言(默认中文简体)

    List<String> mList;
    private int mChooseIndex;


    @BindView(R.id.img_camera_photo_focus_toast)
    ImageView mImgFocusOff;


    boolean mIsPitru;


    @Override
    protected void initData() {
        mContext = getActivity();
        setLuangData();
        preview = new CameraPreview(getActivity(), mSurfaceView);
        preview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        mFragmentLaytou.addView(preview);
        preview.setKeepScreenOn(true);
        mSurfaceView.setOnTouchListener(this);
        CameraPreview.mIsCmaeraFlash = (boolean) SPUtils.get(getActivity(), Constant.SP_CAMERA_FLASH, false);
        if (CameraPreview.mIsCmaeraFlash) {
            mImgFocusOff.setImageResource(R.drawable.img_flash_open);
        } else {
            mImgFocusOff.setImageResource(R.drawable.img_flash_of);
        }

    }


    private Handler handler = new Handler();

    private void takePhoto() {
        try {
            camera.takePicture(null, null, jpegCallback);
        } catch (Throwable t) {
            t.printStackTrace();
            Toast.makeText(getActivity(), "Photo Failure", Toast.LENGTH_LONG)//拍照失败
                    .show();
            try {
                camera.startPreview();
            } catch (Throwable e) {

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsPitru = false;
        int numCams = Camera.getNumberOfCameras();
        if (numCams > 0) {
            try {
                mCurrentCameraId = 0;
                camera = Camera.open(mCurrentCameraId);
                preview.setCamera(camera);//设置相机的相关参数
                camera.startPreview();//取景
            } catch (RuntimeException ex) {//未发现相机
                ex.printStackTrace();
                Toast.makeText(mContext, "No Camera", Toast.LENGTH_LONG).show();
            }
        }

    }


    @Override
    public void onPause() {
        if (camera != null) {
            camera.stopPreview();
            preview.setCamera(null);
            camera.release();
            camera = null;
            preview.setNull();
        }
        super.onPause();

    }


    private void resetCam() {
        camera.startPreview();
        preview.setCamera(camera);
    }

    PictureCallback jpegCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            new SaveImageTask(data).execute();//开子线程
            resetCam();
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                preview.pointFocus(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Logger.w("打印+" + event.getX() + "s" + event.getY());

        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
                focusIndex.getLayoutParams());
        layout.setMargins((int) event.getX() - 26, (int) event.getY() - 26, 0, 0);

        focusIndex.setLayoutParams(layout);
        focusIndex.setVisibility(View.VISIBLE);

        ScaleAnimation sa = new ScaleAnimation(3f, 1f, 3f, 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(800);
        focusIndex.startAnimation(sa);
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                focusIndex.setVisibility(View.INVISIBLE);
            }
        }, 800);
        return false;
    }


    @OnClick({R.id.img_return, R.id.img_home, R.id.img_camera_photo_focus_toast,R.id.action_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.action_button:
                if (!mIsPitru) {
                    mIsPitru = true;
                    takePhoto();
                    preview.resetHandler();//置空Handler
                }
                break;
            case R.id.img_return:    //处理选中状态
                EventBus.getDefault().post(new EventObject(Constant.RETURN_MIAN,null));
                break;
            case R.id.img_home:
                EventBus.getDefault().post(new EventObject(Constant.RETURN_MIAN,null));
                break;
//            case R.id.btn_choose_language:    //选择语言
//                showChooseLuang();
//                break;
            case R.id.img_camera_photo_focus_toast:
                showFlshPop();
                break;

            case R.id.tx_camera_flash_on:
                mImgFocusOff.setImageResource(R.drawable.img_flash_open);
                CameraPreview.mIsCmaeraFlash = true;
                SPUtils.put(getActivity(), Constant.SP_CAMERA_FLASH, true);
                mPopWindow.closePopupWindow();

                preview.setCamera(camera);//设置相机的相关参数

                break;
            case R.id.tx_camera_flash_off:
                mImgFocusOff.setImageResource(R.drawable.img_flash_of);
                CameraPreview.mIsCmaeraFlash = false;
                SPUtils.put(getActivity(), Constant.SP_CAMERA_FLASH, false);
                mPopWindow.closePopupWindow();

                preview.setCamera(camera);//设置相机的相关参数

                break;
        }
    }


    //处理拍摄的照片
    private class SaveImageTask extends AsyncTask<Void, Void, String> {
        private byte[] data;

        SaveImageTask(byte[] data) {//  jpeg格式照片
            this.data = data;
        }

        @Override
        protected String doInBackground(Void... params) {//图片拍照完成进行路径获取和存储
            showProgressDialog("拍照中...");
            camera.stopPreview();// 关闭预览
            BufferedOutputStream bos = null;
            Bitmap bm = null;
            try {
                // 获得图片
                bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                bm = BitmapUtils.getZoomImage(bm, 800, 600);//尺寸OOM处理 800,600
                bm = BitmapUtils.compressImage(bm, 512, 90);//质量OOM处理
                bm = BitmapUtils.rotateBitmap(bm, 90);
                //显示图片
                //EventBus.getDefault().post(new EventObject(Constant.EventType.BITMA, bm));
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String storageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                    String filePath = "/sdcard/dyk" + "test_one" + ".jpg";//照片保存路径
                    //photoPath = filePath;
                    //EventBus.getDefault().post(new EventObject(Constant.EventType.PHOTO_PATH, filePath));//数据回调
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    bos = new BufferedOutputStream(new FileOutputStream(file));
                    bm.compress(Bitmap.CompressFormat.JPEG, 70, bos);//将图片压缩到流中
                    Log.w("wtf","以保存入内存卡");
                } else {
                    Log.w("wtf","无内存卡");
                    Toast.makeText(mContext, "No SdCar", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.w("wtf","保存异常");
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.flush();//输出
                        bos.close();//关闭
                        bm.recycle();// 回收bitmap空间
                        camera.stopPreview();// 关闭预览
                        //camera.startPreview();// 开启预览
                    }
                } catch (IOException e) {
                    Log.w("wtf","IO保存异常");
                    e.printStackTrace();
                }
            }
            return "/sdcard/dyk" + "test_one" + ".jpg";
        }

        @Override
        protected void onPostExecute(String path) {//相机处理完成进行页面跳转
            super.onPostExecute(path);
            if (!TextUtils.isEmpty(path)) {
                dismissProgressDialog();
                Intent intent = new Intent();
                intent.setClass(getActivity(), PhotoProcessActivity.class);
                //intent.putExtra(CAMERA_PATH_VALUE1, path);
                intent.putExtra(Constant.PHOTO_RECOGNITION_LUANG, mRecognitionluange);
                startActivityForResult(intent, Constant.PROCESS);
            } else {//照相失败
                dismissProgressDialog();
                Toast.makeText(getActivity(), "Camera Failure",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private AlertDialog mAlertDialog;

    private void dismissProgressDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAlertDialog != null && mAlertDialog.isShowing()
                        && !getActivity().isFinishing()) {
                    mAlertDialog.dismiss();
                    mAlertDialog = null;
                }
            }
        });
    }

    private void showProgressDialog(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAlertDialog == null) {
                    mAlertDialog = new GenericProgressDialog(
                            getActivity());
                }
                mAlertDialog.setMessage(msg);
                ((GenericProgressDialog) mAlertDialog)
                        .setProgressVisiable(true);
                mAlertDialog.setCancelable(false);
                mAlertDialog.setOnCancelListener(null);
                mAlertDialog.show();
                mAlertDialog.setCanceledOnTouchOutside(false);
            }
        });
    }


    private void showChooseLuang() {
        View outerView = LayoutInflater.from(getActivity()).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(2);
        wv.setItems(mList);
        wv.setSeletion(0);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                mChooseIndex = selectedIndex;
            }
        });

        new AlertDialog.Builder(getActivity())
                .setTitle("选择语言")
                .setView(outerView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//点击事件
                        if (mChooseIndex >= 2) {
                            mChooseIndex = mChooseIndex - 2;
                        }
                        chooseOriginal(mList.get(mChooseIndex));
                    }
                })
                .show();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.camera_home;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected boolean setEventBus() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.PROCESS) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent();
                if (data != null) {
                    intent.putExtra(Constant.CAMERA_RETURN_PATH,
                            data.getStringExtra(Constant.CAMERA_PATH_VALUE2));
                }
                getActivity().setResult(RESULT_OK, intent);
            } else {
                if (data != null) {//删除该文件
                    File dir = new File(data.getStringExtra(Constant.CAMERA_PATH_VALUE2));
                    if (dir != null) {
                        dir.delete();

                    }
                }
            }
        }
    }


    public void setLuangData() {
        mList = new ArrayList<>();
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND));
    }

    public void chooseOriginal(String str) {
        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE))) {
            mRecognitionluange = LngConstant.LANGUAGE_CHINESE;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH))) {
            mRecognitionluange = LngConstant.LANGUAGE_ENGLISH;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE))) {
            mRecognitionluange = LngConstant.LANGUAGE_JAPENESE;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN))) {
            mRecognitionluange = LngConstant.LANGUAGE_KOREAN;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN))) {
            mRecognitionluange = LngConstant.LANGUAGE_RUSSIAN;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH))) {
            mRecognitionluange = LngConstant.LANGUAGE_SPANISH;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH))) {
            mRecognitionluange = LngConstant.LANGUAGE_FRENCH;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN))) {
            mRecognitionluange = LngConstant.LANGUAGE_GERMAN;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL))) {
            mRecognitionluange = LngConstant.LANGUAGE_PORTUGAL;
        }

        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN))) {
            mRecognitionluange = LngConstant.LANGUAGE_ITALIAN;
        }
        if (str.equals(App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND))) {
            mRecognitionluange = LngConstant.LANGUAGE_HOLLAND;
        }
    }


    //    popwindow对象
    private PopWindowUtils mPopWindow;
    private View mPopPwdView;
    private TextView mTxOn;
    private TextView mTxOff;

    public void showFlshPop() {
        if (mPopWindow == null) {
            mPopWindow = new PopWindowUtils(getActivity());
            mPopPwdView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_item_camera_flash, null);
            mTxOn = (TextView) mPopPwdView.findViewById(R.id.tx_camera_flash_on);
            mTxOff = (TextView) mPopPwdView.findViewById(R.id.tx_camera_flash_off);
            if (App.mSysLanguage.equals("zh") || App.mSysLanguage.equals("")) {
                Logger.w("设置中文");
                mTxOn.setText("开");
                mTxOff.setText("关");
            } else {
                Logger.w("设置英文");
                mTxOn.setText("ON");
                mTxOff.setText("OFF");
            }

            mTxOn.setOnClickListener(this);
            mTxOff.setOnClickListener(this);
        }
        mPopWindow.showCameraFlashPopupWindow(mPopPwdView).dropDown(mImgFocusOff, 30, -32);

    }


    @Override
    public void onDestroy() {
        preview.resetHandler();
        super.onDestroy();
    }

}
