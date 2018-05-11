package com.hjw.appframe.htmlpars;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ParsHelper {
    public static IHtmlPars getHtmlPars(ParsType type){
        switch (type){
            case DingDian:
                return new DingDianPars();
            default:
                return null;
        }
    }
}
