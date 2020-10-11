package com.example.demo.utils;

import java.awt.*;
import java.io.*;

/**
 * @Auther: wuxb
 * @Date: 2019-05-27 16:54
 * @Auto: I AM A CODE MAN -_-!
 * @Description:
 */
public class CyFont {

    private Font definedFont = null;

    public Font getDefinedFont(int ft, float fs) {
        String fontUrl="";
        switch (ft) {
            case 1:
                fontUrl="/usr/share/fonts/pingfang/pingfang-pj.ttf";//苹方
                break;
            case 2:
                fontUrl="/opt/hwkt.ttf";//华文楷体
                break;
            default:
                String fonturllocal="/usr/share/fonts/wqy-zenhei/wqy-zenhei.ttc";
                if(!new File(fonturllocal).exists()){
                    fontUrl="/usr/share/fonts/truetype/wqy/wqy-zenhei.ttc";
                }else{
                    fontUrl=fonturllocal;
                }
                break;
        }
        if (definedFont == null) {
            InputStream is = null;
            BufferedInputStream bis = null;
            try {
                is =new FileInputStream(new File(fontUrl));
                bis = new BufferedInputStream(is);
                definedFont = Font.createFont(Font.TRUETYPE_FONT, is);
                //设置字体大小，float型
                definedFont = definedFont.deriveFont(fs);
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return definedFont;
    }

}
