package com.iqoverflow.lostandfound.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class ImageUtils {

    private final static String SAVE_IMAGE_PATH = "C:/images/";

    /**
     * 返回文件后缀
     * @param file 图片文件
     * @return 文件后缀
     */
    public static String getImagePath(MultipartFile file) {
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        int index = fileName.indexOf(".");
        return fileName.substring(index, fileName.length());
    }

    /**
     * 保存图片
     * @param mfile 图片文件
     * @param file 保存后的文件
     * @return 保存结果
     */
    public static boolean saveImage(MultipartFile mfile , File file) {
        //查看文件夹是否存在，不存在则创建
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            //使用此方法保存必须要绝对路径且文件夹必须已存在,否则报错
            mfile.transferTo(file);
            return true;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回新文件名
     * @param suffix 文件后缀名
     * @return 新文件名
     */
    public static String getNewFileName(String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        return date + UUID.randomUUID() + suffix;
    }

    /**
     * 返回图片保存地址
     * @param name 图片名
     * @return 地址
     */
    public static String getNewImagePath(String name) {
        return SAVE_IMAGE_PATH+name;
    }

    /**
     * 读取图片文件，将二进制码转为base64编码后返回
     * @param imagePath 图片文件的路径
     * @return base64编码的结果
     * @throws IOException
     */
    public static String readImageByBase64(String imagePath) throws IOException {
        String imgStr = "";
        FileInputStream fileInputStream = null;
        try {
            File file = new File(imagePath);
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = fileInputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            imgStr = Base64.getEncoder().encodeToString(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        return imgStr;
    }

}
