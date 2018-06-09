package com.jachat.translateor.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.jachat.translateor.R;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.LngConstant;

import java.io.IOException;

/**
 * Created by Perryn on 2017/9/2 0002.
 */

public class MediaPlayerManager {
    private static MediaPlayerManager mInstance;
    private MediaPlayer player;

    /**
     * 获取单例引用
     *
     * @return
     */
    public static MediaPlayerManager getInstance() {
        if (mInstance == null) {
            synchronized (MediaPlayerManager.class) {
                if (mInstance == null) {
                    mInstance = new MediaPlayerManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 播放录音
     *
     * @paramurl
     * @return
     */
    public boolean play(String curLng, MediaPlayer.OnCompletionListener listener) {

        String path = "";
        switch (curLng){
            case LngConstant.LANGUAGE_VIETNAMESE://越南语
            case LngConstant.LANGUAGE_YUEYU://粤语
            case LngConstant.LANGUAGE_CHINESE://普通话
            case LngConstant.LANGUAGE_ENGLISH://英语
            case LngConstant.LANGUAGE_FRENCH://法语
            case LngConstant.LANGUAGE_RUSSIAN://俄语
            case LngConstant.LANGUAGE_SPANISH://西班牙语
                path = Constant.XF_REPLAY_VOICE_PATH;
                break;
            case LngConstant.LANGUAGE_JAPENESE://日语
            case LngConstant.LANGUAGE_KOREAN://韩语
            case LngConstant.LANGUAGE_THAI ://泰语
            case LngConstant.LANGUAGE_ARABIAN://阿拉伯语
            case LngConstant.LANGUAGE_PORTUGAL://葡萄牙语
            case LngConstant.LANGUAGE_GERMAN://德语
            case LngConstant.LANGUAGE_ITALIAN://意大利语
            case LngConstant.LANGUAGE_HOLLAND://荷兰语
            case LngConstant.LANGUAGE_GREEK://希腊语
                path = Constant.HCI_REPLAY_VOICE_PATH;
                break;
        }

        if("".equals(path)) return false;

        return play(path, listener, new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
                stop();
                return false;
            }
        });
    }


    /**
     * 播放录音
     * @param url
     * @return
     */
    public boolean play(String url, MediaPlayer.OnCompletionListener completionListener, MediaPlayer.OnErrorListener errorListener) {
        stop();
        try {
            if (player == null) {
                player = new MediaPlayer();
                player.setDataSource(url);
                player.setVolume(1f, 1f);
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.prepare();
                player.start();
                player.setOnCompletionListener(completionListener);
                player.setOnErrorListener(errorListener);
                return true;
            }
        } catch (Exception e) {
            stop();
        }
        return false;
    }

    public void play(String path){
        stop();
        try {
            if (player == null) {
                player = new MediaPlayer();
                player.setDataSource(path);
                player.setVolume(1f, 1f);
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.prepare();
                player.setLooping(false);
                player.start();
            }
        } catch (Exception e) {
            stop();
        }
    }

    /**
     * 释放资源
     */
    public void stop() {
        if (player != null) {
            try {
                player.stop();
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                player = null;
            }
        }
    }

    public static final int TYPE_SOUND_KEY_PRESS = 1;
    public static final int TYPE_SOUND_SLEEP_WAKE = 2;
    public static final int TYPE_SOUND_NO_NETWORK = 3;
    public static final int TYPE_SOUND_ERROR = 4;

    public void playSound(final Context context, final int type) {
        stop();
        int res = 0;
        switch (type){
            case TYPE_SOUND_KEY_PRESS:
                res = R.raw.ja_key_press;
                break;
            case TYPE_SOUND_SLEEP_WAKE:
                res = R.raw.ja_sleep_wake;
                break;
            case TYPE_SOUND_NO_NETWORK:
                res = R.raw.ja_no_network;
                break;
            case TYPE_SOUND_ERROR:
                res = R.raw.ja_error;
                break;
        }
        if(res == 0) return;
        try {
            if (player == null) {
                player = new MediaPlayer();
                AssetFileDescriptor file = context.getResources().openRawResourceFd(res);
                try {
                    player.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                    player.prepare();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.setVolume(1f, 1f);
                player.setLooping(false);
                player.start();
            }
        } catch (Exception e) {
            stop();
        }
    }

    public void playSound(final Context context, final int type, MediaPlayer.OnCompletionListener listener) {
        stop();
        int res = 0;
        switch (type){
            case TYPE_SOUND_KEY_PRESS:
                res = R.raw.ja_key_press;
                break;
            case TYPE_SOUND_SLEEP_WAKE:
                res = R.raw.ja_sleep_wake;
                break;
            case TYPE_SOUND_NO_NETWORK:
                res = R.raw.ja_no_network;
                break;
            case TYPE_SOUND_ERROR:
                res = R.raw.ja_error;
                break;
        }
        if(res == 0) return;
        try {
            if (player == null) {
                player = new MediaPlayer();
                AssetFileDescriptor file = context.getResources().openRawResourceFd(res);
                try {
                    player.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                    player.prepare();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.setVolume(1f, 1f);
                player.setOnCompletionListener(listener);
                player.setLooping(false);
                player.start();
            }
        } catch (Exception e) {
            stop();
        }
    }

}