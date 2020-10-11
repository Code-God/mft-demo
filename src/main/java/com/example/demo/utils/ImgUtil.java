package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * @Auther: wll
 * @Date: 2018/7/27 17:26
 * @Auto: I AM A CODE MAN !
 * @Description: 图片工具类
 */
@Slf4j
public class ImgUtil {

    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return path
     * @Description: 将base64编码字符串转换为图片 默认存jpg
     * @Author:
     * @CreateTime:
     */
    public static String generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return null;
        }
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] b = decoder.decode(imgStr);
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.info("解析图片失败");
        }
        return path;
    }

    /**
     * 改变图片尺寸
     *
     * @param srcFileName 源图片路径
     * @param tagFileName 目的图片路径
     * @param width       修改后的宽度
     * @param height      修改后的高度
     */
    public static void zoomImage(String srcFileName, String tagFileName, int width, int height) {
        try {
            BufferedImage bi = ImageIO.read(new File(srcFileName));
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
            ImageIO.write(tag, "jpg", new File(tagFileName));
        } catch (IOException e) {
            log.info("解析图片失败");
        }
    }


    //byte数组到图片
    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) return;
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    //图片到byte数组
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }


    /**
     * 删除文件
     *
     * @param imgsrc
     */
    public static void destory(String... imgsrc) {
        try {
            for (String img : imgsrc) {
                File srcfile = new File(img);
                // 检查图片文件是否存在
                if (srcfile.exists()) {
                    srcfile.delete();
                }
            }
        } catch (Exception ef) {
            ef.printStackTrace();
        }
    }

    public static String drawImg(String name, String userId, String autographSrc) {
        log.info("================图片地址:{}",autographSrc);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String filename = "/home/admin/imgs/Autograph_" + name + "_" + userId + "_" + System.currentTimeMillis() + ".png";
        try {
            BufferedImage image = ImageIO.read(new URL(autographSrc));

            Graphics2D graphics = image.createGraphics();
            //开启文字抗锯齿
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            CyFont cf = new CyFont();
            Font definedFont = cf.getDefinedFont(1, 31);

//            Font font=Font.createFont(Font.TRUETYPE_FONT, new File("/usr/share/fonts/pingfang/pingfang-pj.ttf"));
//            font.deriveFont(33);
//            font.deriveFont(Font.PLAIN);
            //姓名 PingFangSC-Light
//            graphics.setFont(new Font("苹方", Font.PLAIN, 33));
            graphics.setFont(definedFont);
            graphics.setColor(new Color(107, 105, 107));
            graphics.drawString(name + "，您好！", 89, 490);

            //日期
            CyFont cfDate = new CyFont();
            Font definedFontDate = cfDate.getDefinedFont(1, 25);
//            graphics.setFont(new Font("苹方", Font.PLAIN, 28));
            graphics.setFont(definedFontDate);
            graphics.drawString(format.format(new Date()), 519, 1250); //521

            image.flush();
            graphics.dispose();
            File folder = new File("/home/admin/imgs/");
            //文件夹路径不存在
            if (!folder.exists() && !folder.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:/home/admin/imgs/");
                folder.mkdirs();
            }
            ImageIO.write(image, "png", new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;

    }


    public static void main(String[] args) {
        String img = "https://dev-m-bucket.oss-cn-shanghai.aliyuncs.com/imgupload/1552374384891-pro.jpg";

        drawImg1("黄洢楦","421127199112013718","总代理", img);
    }


    public static String drawImg1(String name, String idCard, String level, String autographSrc) {
        log.info("================图片地址:{}",autographSrc);
        String filename = "/home/admin/imgs/Autograph_" + name + "_" + idCard + "_" + System.currentTimeMillis() + ".png";
        try {
            BufferedImage image = ImageIO.read(new URL(autographSrc));

            Graphics2D graphics = image.createGraphics();
            //开启文字抗锯齿
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            //颜色
            graphics.setColor(new Color(58, 56, 58));

            //姓名
            CyFont cf = new CyFont();
            Font definedFont = cf.getDefinedFont(1, 38);
            graphics.setFont(definedFont);
            graphics.drawString(name , 575, 790);

            //身份证
            CyFont cfDate = new CyFont();
            Font definedFontDate = cfDate.getDefinedFont(1, 37);
            graphics.setFont(definedFontDate);
            graphics.drawString(idCard, 462, 875);

            //等级
            CyFont cfLevel = new CyFont();
            Font definedFontLevel = cfLevel.getDefinedFont(1, 38);
            graphics.setFont(definedFontLevel);
            graphics.drawString(level, 690, 990);

            image.flush();
            graphics.dispose();
            File folder = new File("/home/admin/imgs/");
            //文件夹路径不存在
            if (!folder.exists() && !folder.isDirectory()) {
                log.info("文件夹路径不存在，创建路径:/home/admin/imgs/");
                folder.mkdirs();
            }
            ImageIO.write(image, "png", new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;

    }


}