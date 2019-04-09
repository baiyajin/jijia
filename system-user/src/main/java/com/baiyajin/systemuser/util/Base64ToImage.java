package com.baiyajin.systemuser.util;

import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;


public class Base64ToImage {



    //图片转化成base64字符串
    public static String GetImageStr(String filePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr,String imageId,String userPhone,String imgPath) {

        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
//            "file:/home/"+userPhone+"/"

            File path = new File(ResourceUtils.getURL(imgPath).getPath());
            if(!path.exists()) path.mkdirs();
            OutputStream out = new FileOutputStream(path+"/"+imageId);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    /*把上传的交易数据excel文件保存到指定路径下*/
    public static boolean saveExcel(String excelBase64,String fileName) {
        if (excelBase64 == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(excelBase64);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File path = new File(ResourceUtils.getURL("file:/home/EXCEL/").getPath());
//            System.out.print(path);
            if(!path.exists()) path.mkdirs();
            OutputStream out = new FileOutputStream(path+"/"+fileName);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}



