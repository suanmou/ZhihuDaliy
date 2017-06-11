package com.zxz.zhihudaliy.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.zxz.zhihudaliy.Bean.DayNight;



/**
 * Created by suan on 2017/6/9.
 */

public class DayNightHelper {
    private final static String FIFE_NAME = "settings";
    private final static String MODE = "day_night_mode";
    private SharedPreferences mSharedPreferences;
    //保存软件配置参数。使用SharedPreferences保存数据，其背后是用xml文件存放数据
    public DayNightHelper(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(FIFE_NAME,Context.MODE_PRIVATE);
    }
    /**
     *保存模式设计
     */
    public boolean setMODE(DayNight mode) {
         SharedPreferences.Editor  editor = mSharedPreferences.edit();
         editor.putString(MODE,mode.getName());
         return editor.commit();
//        提交
    }
    /**
     *夜间模式
     */
    public boolean isNight(){
        String mode = mSharedPreferences.getString(MODE, DayNight.DAY.getName());
        if(DayNight.NIGHT.getName().equals(mode)){
            return true;
        }else{
            return false;
        }
    }
    /**
     *日间模式
     */
   public boolean isDay(){
       String mode = mSharedPreferences.getString(MODE, DayNight.DAY.getName());
       if(DayNight.DAY.getName().equals(mode)){
           return true;
       }else{
           return false;
       }
   }
}
