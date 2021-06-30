//package com.yc.mapgoogle;
//
//import android.content.Context;
//import android.text.TextUtils;
//
//public class Constant {
//    private static Constant constant = new Constant();
//    private String key;
//
//    public Constant() {
//    }
//
//    public static Constant getInstance() {
//        return constant;
//    }
//
//    public String getKey(Context context) {
//        if (TextUtils.isEmpty(this.key)) {
//            this.key = (String)SharedPreferencesUtils.getInstance(context).getParam("google_key", "");
//        }
//
//        return this.key;
//    }
//
//    public void setKey(String key, Context context) {
//        this.key = key;
//        SharedPreferencesUtils.getInstance(context).setParam("google_key", key);
//    }
//}
//
