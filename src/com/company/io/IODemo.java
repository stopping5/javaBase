package com.company.io;

import java.io.*;

/**
 * @Description IODemo
 * @Author stopping
 * @date: 2021/5/29 21:17
 */

public class IODemo {
    public static final int size = 10240;

    public static void main(String[] args) {
        File file = new File("D:\\code\\java-base\\src\\com\\company\\io\\hello1.txt");
        File file1 = new File("D:\\code\\java-base\\src\\com\\company\\io\\hellocopy.txt");

        File image = new File("G:\\学习视频\\opencv\\第1章 课程导学\\1-1 计算机视觉导学.mp4");
        File imageCopy = new File("G:\\学习视频\\opencv\\第1章 课程导学\\1-2 计算机视觉导学.mp4");
        //fileIOImageCopy(file, file1);

        readToWriteFile(image,imageCopy);
        bufferStreamDemo(image, imageCopy);
    }

    private static void bufferStreamDemo(File image, File imageCopy) {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fileInputStream = new FileInputStream(image);
            fileOutputStream = new FileOutputStream(imageCopy);
            bis = new BufferedInputStream(fileInputStream,size);
            bos = new BufferedOutputStream(fileOutputStream);
            int i ;
            byte[] buf = new byte[size];
            while ((i = bis.read(buf)) != -1){
                bos.write(buf,0,i);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("缓存流花费时间:"+(System.currentTimeMillis() - start));
        }
    }

    private static void fileIOImageCopy(File image, File imageCopy) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream(image);
            fileOutputStream = new FileOutputStream(imageCopy);
            byte [] bbuf = new byte[1024];
            int i ;
            while((i = fileInputStream.read(bbuf)) != -1){
                fileOutputStream.write(bbuf,0,i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void writeDemo(File file) {
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file,false);
            String str = "write data to hello.txt";
            fileWriter.write(str);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readToWriteFile(File file, File file1) {
        //fileReaderDemo(file);
        long start = System.currentTimeMillis();
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        char [] buff = new char[size];
        try {
            fileReader = new FileReader(file);
            //new FileWriter(file,true) 追加内容
            //new FileWriter(file)|new FileWriter(file,false) 覆盖
            //File,append 是否追加到文件尾
            //1. public void write(char cbuf[])
            //2. public void write(int c)
            fileWriter = new FileWriter(file1,false);
            int index;
            while ((index = fileReader.read(buff)) != -1){
                //System.out.println("write data");
                fileWriter.write(buff);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("文件流花费时间:"+(System.currentTimeMillis() - start));
        }

    }

    private static void fileReaderDemo(File file) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            //1. read()
            //2. read(char cbuf[], int offset, int length)
            //3. read(char cbuf[])
            char [] c = new char[15];
            int i =  0;
            int time = 0;
            while ( (i = fileReader.read(c)) != -1){
                System.out.println("遍历次数："+ ++time);
                for (int j = 0; j < i; j++) {
                    System.out.print(c[j]);
                }
                System.out.println("");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null){
                //关闭资源
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
