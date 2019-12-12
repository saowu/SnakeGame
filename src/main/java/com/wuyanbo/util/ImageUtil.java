package com.wuyanbo.util;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @description: 图片工具类
 * @author: wuyanbo
 * @create: 2019-12-03 10:07
 */


public class ImageUtil {


    /**
     * 根据图片路径获取image
     *
     * @param filename 文件路径
     * @return
     */
    public static Image getImage(String filename) {
        return getImageIcon(filename).getImage();

    }


    /**
     * 根据图片路径获取imageIcon
     * resources/img/bg_login.jpg
     *
     * @param filename 文件路径
     * @return
     */
    public static ImageIcon getImageIcon(String filename) {
        InputStream resourceAsStream = ImageUtil.class.getResourceAsStream(filename);
        byte[] bytes = streamToByteArray(resourceAsStream);
        return new ImageIcon(bytes);
    }

    /**
     * 字节流转字节数组
     * jdk9->readAllBytes()
     *
     * @param inputStream
     * @return
     */
    public static byte[] streamToByteArray(InputStream inputStream) {
        byte[] imagebs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            //将文件读入到字节数组中
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            imagebs = baos.toByteArray();
            inputStream.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagebs;
    }
}
