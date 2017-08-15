package com.hjw.base.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by jinwei on 2016/1/6.
 */
public class BigDecimalUtils {

    /*16位整数位，两小数位*/
    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###############0.00");
    public static final DecimalFormat NUMBER_FORMAT_FOUR = new DecimalFormat("###############0.0000");
    public static final DecimalFormat NUMBER_FORMAT_ZERO = new DecimalFormat("###############0");

    public static BigDecimal add(BigDecimal one, BigDecimal two){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        return one.add(two);
    }

    /**
     * BigDecimal小数点后四位四舍五入成两位
     * @param num
     * @param scale
     * @return
     */
    public static BigDecimal round(BigDecimal num , int scale) {
        BigDecimal result = num.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
        return result;
    }

    public static BigDecimal add(BigDecimal... decimals){
        if(decimals == null || decimals.length ==0){
            return new BigDecimal(0);
        }
        BigDecimal total = new BigDecimal(0);
        BigDecimal adds = null;
        for (int i =0;i<decimals.length;i++){
            adds = decimals[i];
            if(null != adds){
                total = add(total,adds);
            }
        }
        return total;
    }

    public static BigDecimal reduce(BigDecimal one, BigDecimal two){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        return one.subtract(two);
    }

    public static BigDecimal reduce(BigDecimal... decimals){
        if(decimals == null || decimals.length < 2){
            return new BigDecimal(0);
        }
        BigDecimal reduces0 = decimals[0];
        if(null == reduces0){
            reduces0 = BigDecimal.ZERO;
        }
        for (int i = 1;i<decimals.length;i++){
            if(null != decimals[i]){
                reduces0 = reduces0.subtract(decimals[i]);
            }
        }

        return reduces0;
    }

    public static BigDecimal multiply(BigDecimal one, BigDecimal two){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        return one.multiply(two);
    }

    public static BigDecimal multiply(BigDecimal... decimals){
        if(decimals == null || decimals.length < 2){
            return new BigDecimal(0);
        }
        BigDecimal total = BigDecimal.ONE;
        BigDecimal multiplies = null;
        for (int i =0;i<decimals.length;i++){
            multiplies = decimals[i];
            total = multiply(total,multiplies);
        }

        return total;
    }

    public static BigDecimal divide(BigDecimal one, BigDecimal two){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(1);
        }
        return one.divide(two,4, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal one, BigDecimal two, int pointNum){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(1);
        }
        return one.divide(two,pointNum, BigDecimal.ROUND_HALF_UP);
    }



    public static BigDecimal divideLeftTwo(BigDecimal one, BigDecimal two){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(1);
        }
        return one.divide(two,2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 整除
     * @param one
     * @param two
     * @return
     */
    public static BigDecimal integerDivide(BigDecimal one, BigDecimal two){
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(1);
        }
        return one.divide(two,0, BigDecimal.ROUND_DOWN);
    }

    public static boolean moreThan(BigDecimal one, BigDecimal two){//大于
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        return one.compareTo(two) == 1;
    }

    public static boolean moreThanOrEquals(BigDecimal one, BigDecimal two){//大于等于
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        if(one.compareTo(two) == 1 || one.compareTo(two) ==0){
            return true;
        }else {
            return false;
        }
    }

    public static boolean lessThan(BigDecimal one, BigDecimal two){//小于
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        return one.compareTo(two) == -1;
    }

    public static boolean lessThanOrEquals(BigDecimal one, BigDecimal two){//小于等于
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        if(one.compareTo(two) == -1 || one.compareTo(two) == 0){
            return true;
        }else {
            return false;
        }
    }

    public static boolean equals(BigDecimal one, BigDecimal two){//等于
        if (null == one){
            one = new BigDecimal(0);
        }
        if (null == two){
            two = new BigDecimal(0);
        }
        return one.compareTo(two) == 0;
    }


    public static String format(BigDecimal one){//格式
        if (null == one){
            return "0.00";
        }
        return NUMBER_FORMAT.format(one);
    }

    public static String formatFour(BigDecimal one){//格式
        if (null == one){
            return "0.0000";
        }
        return NUMBER_FORMAT_FOUR.format(one);
    }

    public static String formatZero(BigDecimal one){//格式
        if (null == one){
            return "0";
        }
        return NUMBER_FORMAT_ZERO.format(one);
    }

    public static String formatRMB(BigDecimal one){//格式
        if (null == one) {
            return "¥0.00";
        }
        return "¥"+NUMBER_FORMAT.format(one);
    }

    public static String formatRMB(String one){//格式
        if (null == one || one.trim().equals("")) {
            return "¥0.00";
        }
        return formatRMB(new BigDecimal(one));
    }

    public static BigDecimal createByString(String decimal){
        if(StringUtils.isNull(decimal)){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(decimal);
    }
}
