package com.example.zhihu_daily.datastorage;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class FileStorageForNews extends AppCompatActivity {

    private String mFileName, content,imageUrl;
    private String mBaseFilePath,id ;

    public FileStorageForNews(String id, String content,String storagePath,String imageUrl) {
        this.content = content;
        mFileName = id+".txt";
        this.id = id;
        mBaseFilePath = storagePath;
        this.imageUrl = imageUrl;
        isFileExert(mBaseFilePath);

    }
    private void isFileExert(String mBaseFilePath){
            //以新闻id为名创建txt文件
            creatFilesForNews(mBaseFilePath);
            //存入文件内容
            save( mBaseFilePath+File.separator+"news");
            //将txt文件改成html文件
            reNameFile(mBaseFilePath);
    }


    private void save(String FilePath) {
        File dest = new File(FilePath+File.separator+mFileName);
        File image = new File(FilePath+File.separator+"pic"+mFileName);
        OutputStream outputStreamDest =null;
        OutputStream outputStreamImage =null;
        try {
            outputStreamDest = new FileOutputStream(dest,false);
            outputStreamImage = new FileOutputStream(image,false);

            byte[] data1 = content.getBytes();
            byte[] data2 = imageUrl.getBytes();

            outputStreamDest.write(data1,0,data1.length);
            outputStreamImage.write(data2,0,data2.length);

            outputStreamDest.flush();
            outputStreamImage.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件没找到");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件写出失败");
        }finally {
            if(null!=outputStreamImage){
                try {
                    outputStreamImage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=outputStreamDest){
                try {
                    outputStreamDest.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void creatFilesForNews(String baseFilePath){


        File file1 = new File(baseFilePath+File.separator+"news"+File.separator+mFileName);
        File file2 = new File(baseFilePath+File.separator+"news"+File.separator+"pic"+mFileName);

            file1.getParentFile().mkdirs();
            file2.getParentFile().mkdirs();
            try {
                file1.createNewFile();
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    private void reNameFile(String baseFilePath){
        String filePath = baseFilePath+File.separator+"news"+File.separator+mFileName;
        String newFilePath = baseFilePath+File.separator+"news"+File.separator+id+".html";
        File toBeRenamed = new File(filePath);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            return;
        }
        File newFile = new File(newFilePath);

        //修改文件名
        toBeRenamed.renameTo(newFile);
    }



}
