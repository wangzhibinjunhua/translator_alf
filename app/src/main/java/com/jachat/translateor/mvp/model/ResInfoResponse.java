package com.jachat.translateor.mvp.model;

import java.util.List;

/**
 * Created by Perryn on 2017/8/30 0030.
 */

public class ResInfoResponse extends HttpResponse {


    public List<ItemsBean> items;

    public static class ItemsBean {
        public String path;
        public String clientpath;
        public String action;
        public int verno;
        public int id;
        public String time;
        public String memo;
    }
}
