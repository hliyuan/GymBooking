package utils;

/**
 * Created by liyuan on 2017/3/16.
 */

import java.util.regex.Pattern;

/**
 * 正则匹配
 */
public final class PatternKit {

    /**
     * 验证日期（年月日）
     * @param date 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isDate(String date) {
        String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        return Pattern.matches(regex,date);
    }

    /**
     * 验证(时间段)
     * @param hours 格式:15:00~17:00
     * @return
     */
    public static boolean isHours(String hours) {
        String regex = "^(\\d{2}):(00)~(\\d{2}):(00)$";
        return Pattern.matches(regex,hours);
    }

    public static boolean isWhere(String where) {
        return true;
    }

    /**
     * 判断是否取消
     * @param cancel
     * @return
     */
    public static boolean isCancel(String cancel) {
        return cancel.equals("C");
    }

    public static String transfer(int time){
        if (time<10){
            return "0"+time;
        }
        return String.valueOf(time);
    }

}
