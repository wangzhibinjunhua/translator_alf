package com.jachat.translateor.utils.mycamer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


//目前完美适配 小屏机型
public class CameraPreview extends ViewGroup implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Size mPreviewSize;
    private Size adapterSize;
    private List<Size> mSupportedPreviewSizes;
    private static Camera mCamera;
    private boolean isSupportAutoFocus = false;
    private Camera.Parameters parameters = null;
    private Context mContext;
    //private int mCurrentCameraId = 0;
    private int screenWidth;
    private int screenHeight;

    private Thread mThread;
    private static boolean mIsClock;//自动对焦锁

    int mSurfaceWidth;
    int mSurfaceHeight;

    public static boolean mIsCmaeraFlash;

    //自定对焦
    private Camera.AutoFocusCallback mAutoFocusCallback;


    public CameraPreview(Context context, SurfaceView sv) {//获取控件的宽高度
        super(context);
        autoFocusHandler = new Handler();
        mContext = context;
        mSurfaceView = sv;
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.setKeepScreenOn(true);
        isSupportAutoFocus = context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_AUTOFOCUS);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    public void setCamera(Camera camera) {//Activity进行调用
        mCamera = camera;

        mAutoFocusCallback = new Camera.AutoFocusCallback() {

            public void onAutoFocus(boolean success, Camera camera) {
                // TODO Auto-generated method stub
                Logger.w("进入聚焦");
                if (success) {
                    mCamera.setOneShotPreviewCallback(null);
                    Logger.w("聚焦成功");
                }

            }
        };

        initCamera();
    }

    private Camera.Parameters myParameters;

    public void initCamera() {
        if (mCamera != null) {
            Logger.w("CameraPreView重置相机相关参数");
            Camera.Parameters params = mCamera.getParameters();
            mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();

            try {
                myParameters = mCamera.getParameters();
                myParameters.setPictureFormat(PixelFormat.JPEG);
                //800, 480
                myParameters.setPictureSize(1280,720);//设置照片的分辨率1280, 960 （800,480）相对识别成度比较高 (640/480 识别度时高时低)
                myParameters.setPreviewSize(800,480);//1280, 720
                //myParameters.setFocusMode("auto");
                //myParameters.setPictureSize(2048, 1152); //1280, 720
                Log.w("wtf","输出mIsCmaeraFlash");
                if (mIsCmaeraFlash) {
                    Log.w("wtf","打开");
                    myParameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);//开启闪光灯
                }else {
                    Log.w("wtf","关闭");
                    myParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);//关闭闪光灯
                }
                //myParameters.set("rotation", 90);//位图旋转
                mCamera.setDisplayOrientation(90);
                mCamera.setParameters(myParameters);
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();

                mCamera.autoFocus(mAutoFocusCallback);
                autoFocus();//自动对焦
            } catch (Exception e) {
                // TODO: handle exception
                Logger.w("初始化异常");
                e.printStackTrace();

            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed && getChildCount() > 0) {
            final View child = getChildAt(0);

            final int width = r - l;
            final int height = b - t;

            int previewWidth = width;
            int previewHeight = height;
            if (mPreviewSize != null) {
                previewWidth = mPreviewSize.width;
                previewHeight = mPreviewSize.height;
            }

            // Center the child SurfaceView within the parent.
            if (width * previewHeight > height * previewWidth) {
                final int scaledChildWidth = previewWidth * height / previewHeight;
                child.layout((width - scaledChildWidth) / 2, 0,
                        (width + scaledChildWidth) / 2, height);
            } else {
                final int scaledChildHeight = previewHeight * width / previewWidth;
                child.layout(0, (height - scaledChildHeight) / 2,
                        width, (height + scaledChildHeight) / 2);
            }
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        try {
            if (mCamera != null) {
                mCamera.setPreviewDisplay(holder);
                mSurfaceWidth = getWidth();
                mSurfaceHeight = getHeight();
            }
        } catch (IOException e) {
            if (null != mCamera) {
                mCamera.release();
                mCamera = null;
            }
            e.printStackTrace();
        }
    }

    static Camera.Parameters mParameters;

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

//        if (holder.getSurface() == null) {
//            return;
//        }
//        if (mCamera != null) {
//            //holder.setFixedSize(240, 320);//设置表面固定大小(暂不知道作用)
//            mParameters = parameters = mCamera.getParameters();
//            //parameters.getSupportedPreviewSizes();//获得相机支持的预览图片大小，返回值是一个List<Size>数组
//            for (Camera.Size a : parameters.getSupportedPreviewSizes()) {
//                Logger.w("支持的预览格式有+" + a.width + "+" + a.height);
//            }
//
//            List<String> focusModes = parameters.getSupportedFocusModes();//支持的缩放方式
////            for (String mode : focusModes) {
////                Logger.e("支持的缩放方式+" + mode);
////            }
//            //parameters.setPreviewFormat(ImageFormat.NV21);//设置图片的预览格式
//            //            for (Camera.Size a : parameters.getSupportedPictureSizes()) {
//            // Logger.w("支持设置的width+" + a.width + "height+" + a.height);
//            //}
//            //某些数值不能设置 1024  768   (800,600)识别存在问题  （720,480）识别存在错位
//            //（800,480）相对识别成度比较高比这个参数小的都会导致识别位置偏砸
//            //必须设置照片的大小800, 480
//            //parameters.setPictureSize(800, 480);//设置照片的分辨率1280, 960 （800,480）相对识别成度比较高 (640/480 识别度时高时低)
//            //得到的图片相对模糊，没有对应的预览尺寸、但能做到所拍即所得
//            //Camera.Size size = getCloselyPreSize(mSurfaceWidth,mSurfaceHeight,parameters.getSupportedPreviewSizes());
//            // Logger.w("预览的分辨率+"+size.width+"高"+size.height+"现设宽+"+mPreviewSize.width+"现设高+"+ mPreviewSize.height);
//            //可用预览图片格式 1280,960、800,480、（识别度最高） 720/480  640/480（预览相对比较高清 但是AI识别成度不高（暂选这个））
//            // 576/432（清晰度还可以，AI识别度不高）、480/360(清晰度一般，AI识别不全会出现错位)
//            parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);//设置预览图片的大小mPreviewSize.width, mPreviewSize.height //
//            //Logger.w("曝光补偿最小+" + parameters.getMinExposureCompensation() + "最大+" + parameters.getMaxExposureCompensation() + "是否支持+" + parameters.getMaxExposureCompensation());
//            //parameters.setExposureCompensation(1);//曝光补偿（设置曝光补偿指数）  -12 到 12
////            for (int a : parameters.getSupportedPreviewFormats()) {
////                Logger.e("支持的照片预览格式+" + a);
////            }
//            //#WHITE_BALANCE_AUTO(自动)#WHITE_BALANCE_INCANDESCENT(白炽灯)#WHITE_BALANCE_FLUORESCENT( 荧光)#WHITE_BALANCE_WARM_FLUORESCENT(温暖荧光)
//            //#WHITE_BALANCE_DAYLIGHT(晴天)#WHITE_BALANCE_CLOUDY_DAYLIGHT(多云)#WHITE_BALANCE_TWILIGHT(雾天)#WHITE_BALANCE_SHADE(阴天)
//
//            if (mIsCmaeraFlash) {
//                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);//开启闪光灯
//            }
//            //parameters.setWhiteBalance(parameters.WHITE_BALANCE_DAYLIGHT);//设置白平衡
//
//            //parameters.setSceneMode();//设置运行场景 、夜天或者白天
//            //parameters.setPreviewFormat();//照片的预览格式 17(本机型只支持一种)
//            //parameters.getSupportedPictureSizes();//可设置的照片格式
//
//
//            //parameters.getSupportedPreviewFormats();//获取支持的预览格式
//            //parameters.setPreviewFormat(int pixel_format);//设置预览照片的格式
//            //parameters.getSupportedPictureSizes();//获取手机支持的照片大小
//            //parameters.setPictureSize(int width, int height);//设置保存的图片的大小
//            //parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动对焦
//            // parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//自动对焦模式
//            //parameters.setPreviewFrameRate(3);//设置帧数每秒多少帧
//            mCamera.getParameters().setZoom(50);//设置相机焦距2 或者 50 最大 78（）、10
//            Logger.w("摄像头的最大焦距+" + mCamera.getParameters().getMaxZoom());
//            mCamera.setParameters(parameters);
//
//            try {
//                mCamera.setPreviewDisplay(holder);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mCamera.startPreview();
////            mCamera.cancelAutoFocus();//对应自动对焦、消除所有的自动对焦
//            mIsClock = true;
//            onAutoFocus();//定点对焦一次
//            //reAutoFocus();//自动对焦
//        }


    }


    private static Handler autoFocusHandler;


    public void resetHandler() {
        if (autoFocusHandler != null) {
            autoFocusHandler = null;
        }

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        if (mCamera != null) {
            mCamera.stopPreview();
        }
    }

    /**
     * 最小预览界面的分辨率
     */
    private static final int MIN_PREVIEW_PIXELS = 480 * 320;
    /**
     * 最大宽高比差
     */
    private static final double MAX_ASPECT_DISTORTION = 0.15;//0.15

    /**
     * 找出最适合的预览界面分辨率
     *
     * @return
     */
    private Size findBestPreviewResolution() {
        Camera.Parameters cameraParameters = mCamera.getParameters();
        Size defaultPreviewResolution = cameraParameters.getPreviewSize();//获取画布的数据

        List<Size> rawSupportedSizes = cameraParameters.getSupportedPreviewSizes();
        if (rawSupportedSizes == null) {
            return defaultPreviewResolution;
        }

        // 按照分辨率从大到小排序
        List<Size> supportedPreviewResolutions = new ArrayList<Size>(rawSupportedSizes);
        Collections.sort(supportedPreviewResolutions, new Comparator<Size>() {
            @Override
            public int compare(Size a, Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });

        StringBuilder previewResolutionSb = new StringBuilder();
        for (Size supportedPreviewResolution : supportedPreviewResolutions) {
            previewResolutionSb.append(supportedPreviewResolution.width).append('x').append(supportedPreviewResolution.height)
                    .append(' ');
        }


        // 移除不符合条件的分辨率
        double screenAspectRatio = (double) screenWidth
                / screenHeight;
        Iterator<Size> it = supportedPreviewResolutions.iterator();
        while (it.hasNext()) {
            Size supportedPreviewResolution = it.next();
            int width = supportedPreviewResolution.width;
            int height = supportedPreviewResolution.height;

            // 移除低于下限的分辨率，尽可能取高分辨率
            if (width * height < MIN_PREVIEW_PIXELS) {
                it.remove();
                continue;
            }

            // 在camera分辨率与屏幕分辨率宽高比不相等的情况下，找出差距最小的一组分辨率
            // 由于camera的分辨率是width>height，我们设置的portrait模式中，width<height
            // 因此这里要先交换然preview宽高比后在比较
            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion > MAX_ASPECT_DISTORTION) {
                it.remove();
                continue;
            }

            // 找到与屏幕分辨率完全匹配的预览界面分辨率直接返回
            if (maybeFlippedWidth == screenWidth
                    && maybeFlippedHeight == screenHeight) {
                return supportedPreviewResolution;
            }
        }


        // 如果没有找到合适的，并且还有候选的像素，则设置其中最大比例的，对于配置比较低的机器不太合适
        if (!supportedPreviewResolutions.isEmpty()) {
            Size largestPreview = supportedPreviewResolutions.get(0);
            return largestPreview;
        }


        // 没有找到合适的，就返回默认的

        return defaultPreviewResolution;
    }


    private Size findBestPictureResolution() {
        Camera.Parameters cameraParameters = mCamera.getParameters();
        List<Size> supportedPicResolutions = cameraParameters.getSupportedPictureSizes(); // 至少会返回一个值

        StringBuilder picResolutionSb = new StringBuilder();
        for (Size supportedPicResolution : supportedPicResolutions) {
            picResolutionSb.append(supportedPicResolution.width).append('x')
                    .append(supportedPicResolution.height).append(" ");
        }

        Size defaultPictureResolution = cameraParameters.getPictureSize();

        // 排序
        List<Size> sortedSupportedPicResolutions = new ArrayList<Size>(
                supportedPicResolutions);
        Collections.sort(sortedSupportedPicResolutions, new Comparator<Size>() {
            @Override
            public int compare(Size a, Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });


        // 移除不符合条件的分辨率
        double screenAspectRatio = screenWidth
                / (double) screenHeight;
        Iterator<Size> it = sortedSupportedPicResolutions.iterator();
        while (it.hasNext()) {
            Size supportedPreviewResolution = it.next();
            int width = supportedPreviewResolution.width;
            int height = supportedPreviewResolution.height;

            // 在camera分辨率与屏幕分辨率宽高比不相等的情况下，找出差距最小的一组分辨率
            // 由于camera的分辨率是width>height，我们设置的portrait模式中，width<height
            // 因此这里要先交换然后在比较宽高比
            boolean isCandidatePortrait = width > height;
            int maybeFlippedWidth = isCandidatePortrait ? height : width;
            int maybeFlippedHeight = isCandidatePortrait ? width : height;
            double aspectRatio = (double) maybeFlippedWidth / (double) maybeFlippedHeight;
            double distortion = Math.abs(aspectRatio - screenAspectRatio);
            if (distortion > MAX_ASPECT_DISTORTION) {
                it.remove();
                continue;
            }
        }

        // 如果没有找到合适的，并且还有候选的像素，对于照片，则取其中最大比例的，而不是选择与屏幕分辨率相同的
        if (!sortedSupportedPicResolutions.isEmpty()) {
            return sortedSupportedPicResolutions.get(0);
        }

        // 没有找到合适的，就返回默认的
        return defaultPictureResolution;
    }

    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null)
            return null;

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

    public Size getResolution() {
        Camera.Parameters params = mCamera.getParameters();
        Size s = params.getPreviewSize();
        return s;
    }

    /**
     * 调用定点对焦的代码
     */
    public void pointFocus(MotionEvent event) {
        //mCamera.cancelAutoFocus();//关闭自动对焦
        //关闭自动对焦
        mIsClock = false;
        parameters = mCamera.getParameters();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            //showPoint(x, y);
            focusOnTouch(event);
        }
        //开启摄像头
        // parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        mCamera.setParameters(parameters);
        autoFocus();
    }

    Thread mAutoFocusThread;
    Runnable mRunable = new Runnable() {
        @Override
        public void run() {
            try {
                mAutoFocusThread.sleep(100);
            } catch (InterruptedException e) {
                Logger.w("线程异常");
                e.printStackTrace();
            }
            if (mCamera == null) {
                Logger.w("相机对象为空");
                return;
            }
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (success) {
                        Logger.w("初始化相机参数");
                        initCamera();//实现相机的参数初始化
                        mAutoFocusThread = null;
                    } else {
                        mAutoFocusThread = null;
                        Logger.w("聚焦失败");
                    }
                }
            });
        }
    };

    //实现自动对焦
    public void autoFocus() {
        Logger.e("进入自动对焦" + "autoFocusHandler" + autoFocusHandler + "mCamera" + mCamera);
        if (autoFocusHandler != null) {
            Logger.w("准备开启新的线程");
            if (mAutoFocusThread == null) {
                mAutoFocusThread = new Thread(mRunable);
                mAutoFocusThread.start();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void showPoint(int x, int y) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> areas = new ArrayList<Camera.Area>();
            WindowManager wm = (WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            //xy变换了
            int rectY = -x * 2000 / wm.getDefaultDisplay().getWidth() + 1000;
            int rectX = y * 2000 / wm.getDefaultDisplay().getHeight() - 1000;
            int left = rectX < -900 ? -1000 : rectX - 100;
            int top = rectY < -900 ? -1000 : rectY - 100;
            int right = rectX > 900 ? 1000 : rectX + 100;
            int bottom = rectY > 900 ? 1000 : rectY + 100;
            Rect area1 = new Rect(left, top, right, bottom);
            areas.add(new Camera.Area(area1, 800));
            parameters.setMeteringAreas(areas);
        }

        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void focusOnTouch(MotionEvent event) {
        Rect focusRect = calculateTapArea(event.getRawX(), event.getRawY(), 1f);
        Rect meteringRect = calculateTapArea(event.getRawX(), event.getRawY(), 1.5f);

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        if (parameters.getMaxNumFocusAreas() > 0) {
            List<Camera.Area> focusAreas = new ArrayList<Camera.Area>();
            focusAreas.add(new Camera.Area(focusRect, 1000));

            parameters.setFocusAreas(focusAreas);
        }

        if (parameters.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
            meteringAreas.add(new Camera.Area(meteringRect, 1000));

            parameters.setMeteringAreas(meteringAreas);
        }
        mCamera.setParameters(parameters);
        mCamera.autoFocus(mAutoFocusCallback);
    }




    /**
     * Convert touch position x:y to {@link Camera.Area} position -1000:-1000 to 1000:1000.
     */
    private Rect calculateTapArea(float x, float y, float coefficient) {
        float focusAreaSize = 300;
        int areaSize = Float.valueOf(focusAreaSize * coefficient).intValue();

        int centerX = (int) (x / getResolution().width * 2000 - 1000);
        int centerY = (int) (y / getResolution().height * 2000 - 1000);

        int left = clamp(centerX - areaSize / 2, -1000, 1000);
        int right = clamp(left + areaSize, -1000, 1000);
        int top = clamp(centerY - areaSize / 2, -1000, 1000);
        int bottom = clamp(top + areaSize, -1000, 1000);

        return new Rect(left, top, right, bottom);
    }

    private int clamp(int x, int min, int max) {
        if (x > max) {
            return max;
        }
        if (x < min) {
            return min;
        }
        return x;
    }

    public void setNull() {
        adapterSize = null;
        mPreviewSize = null;
    }


    /**
     * 通过对比得到与宽高比最接近的尺寸（如果有相同尺寸，优先选择）
     *
     * @param surfaceWidth
     * 需要被进行对比的原宽
     * @param surfaceHeight
     * 需要被进行对比的原高
     * @param preSizeList
     * 需要对比的预览尺寸列表
     * @return 得到与原宽高比例最接近的尺寸
     */
    boolean mIsPortrait = true;

}
