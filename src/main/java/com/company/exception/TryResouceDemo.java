package com.company.exception;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @Classname: TryResouceDemo
 * @Description: TODO
 * @Date: 2023/4/10 2:36 下午
 * @author: stopping
 */
public class TryResouceDemo {
    public static void main(String[] args) {
        System.out.println(readFile());
    }

    public static String readFile(){
        StringBuilder stringBuilder = new StringBuilder();
        byte[] b = null;
        try( FileInputStream fileInputStream = new FileInputStream("/Users/stopping/stopping/javaBase/src/main/java/com/company/io/hello.txt");){
            while (fileInputStream.available() > 0){
                fileInputStream.read(b);
                stringBuilder.append(new String(b));
            }
            return stringBuilder.toString();
        }catch (Exception e){
            System.out.println("发生异常");
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
        System.out.println("over");
        return "";
    }
}
