package com.example.zhihu_daily.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * 本类为长期储存与下载后长期保存服务
 */
public class SrgToPathUtil {

    private String data,path;

    //构造是已经创建好相应的文件（如果文件及路径不存在）
    public SrgToPathUtil(String data, String path) {
        this.path = path;
        this.data = data;
        saveData(data,path);
    }

    public SrgToPathUtil(String path) {
        this.path = path;
    }
    public SrgToPathUtil(){

    }

    private void creatFilesForNews(String path){
        File file1 = new File(path);
        if(!file1.exists()){
            file1.getParentFile().mkdirs();
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveData(String content,String path) {
        File dest = new File(path);
        if(!dest.exists()){
            dest.getParentFile().mkdirs();
            try {
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OutputStream outputStreamDest =null;
        try {
            outputStreamDest = new FileOutputStream(dest,false);
            byte[] data1 = content.getBytes();
            outputStreamDest.write(data1,0,data1.length);
            outputStreamDest.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件没找到");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件写出失败");
        }finally {
            if(null!=outputStreamDest){
                try {
                    outputStreamDest.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getData(String path){
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            while (null!=(s=bufferedReader.readLine())){
                sb.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=bufferedReader){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


}
