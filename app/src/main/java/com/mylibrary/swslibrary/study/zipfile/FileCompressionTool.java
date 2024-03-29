package com.mylibrary.swslibrary.study.zipfile;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author Sws
 * @time 15:17 2022/3/16
 * @dec
 **/
public class FileCompressionTool {
    private final String TAG = "ZipFileUtils";
    //文件根目录
    private String parentPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android-ZipFile";
    //压缩前文件地址
    private String zipStartFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/测试文件压缩/";
    //压缩文件地址
    private String zipLocalPath = parentPath + "/压缩文件/";
    //压缩文件名
    private String zipLocalName = "test.zip";
    //解压缩文件地址
    private String unZipLocalPath = parentPath + "/解压缩文件/";

    private static FileCompressionTool instance;
    private Context context;

    public synchronized static FileCompressionTool getInstance() {
        if (instance == null) {
            synchronized (FileCompressionTool.class) {
                instance = new FileCompressionTool();
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        /*copyDbFile(context, "a.txt");
        copyDbFile(context, "b.txt");
        copyDbFile(context, "c.txt");
        copyDbFile(context, "d.txt");
        copyDbFile(context, "e.txt");
        copyDbFile(context, "f.txt");*/
    }

    /**
     * 开启文件压缩
     * @param zipOutputStream
     * @param name
     * @param fileSrc
     * @throws IOException
     */
    private void zip(ZipOutputStream zipOutputStream, String name, File fileSrc) throws IOException {

        if (fileSrc.isDirectory()) {
            System.out.println("需要压缩的地址是目录");
            File[] files = fileSrc.listFiles();

            name = name + "/";
            zipOutputStream.putNextEntry(new ZipEntry(name));  // 建一个文件夹
            System.out.println("目录名: " + name);

            for (File f : files) {
                zip(zipOutputStream, name + f.getName(), f);
                System.out.println("目录: " + name + f.getName());
            }

        } else {
            System.out.println("需要压缩的地址是文件");
            zipOutputStream.putNextEntry(new ZipEntry(name));
            System.out.println("文件名: " + name);
            FileInputStream input = new FileInputStream(fileSrc);
            System.out.println("文件路径: " + fileSrc);
            byte[] buf = new byte[1024];
            int len = -1;

            while ((len = input.read(buf)) != -1) {
                zipOutputStream.write(buf, 0, len);
            }

            zipOutputStream.flush();
            input.close();
        }
    }

    /**
     * 解压缩
     * 将zipFile文件解压到folderPath目录下.
     *
     * @param zipFile    zip文件
     * @param folderPath 解压到的地址
     */
    private void upZipFile(File zipFile, String folderPath) throws IOException {
        ZipFile zfile = new ZipFile(zipFile);
        Enumeration zList = zfile.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                Log.d(TAG, "ze.getName() = " + ze.getName());
                String dirstr = folderPath + ze.getName();
                dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
                Log.d(TAG, "str = " + dirstr);
                File f = new File(dirstr);
                f.mkdir();
                continue;
            }
            Log.d(TAG, "ze.getName() = " + ze.getName());
            OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
            int readLen = 0;
            while ((readLen = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, readLen);
            }
            is.close();
            os.close();
        }
        zfile.close();
    }

    void compressFile() {
        AsyncTask.execute(() -> {
            //要压缩的文件的路径
            File file = new File(zipLocalPath + zipLocalName);
            //创建文件夹
            File filePath = new File(zipLocalPath);
            if (!filePath.exists())
                filePath.mkdirs();

            if (file.exists())
                return;

            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file), new CRC32()));
                zip(zipOutputStream, zipLocalName, new File(zipStartFilePath));
                zipOutputStream.flush();
                zipOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG,"压缩完成");
            //Toast.makeText(context, "压缩完成", Toast.LENGTH_SHORT).show();
        });
    }

    void unZip() {
       AsyncTask.execute(() -> {
           File FILE = new File(zipLocalPath + zipLocalName);
           try {
               upZipFile(FILE, unZipLocalPath);
           } catch (IOException e) {
               e.printStackTrace();
           }
           Log.d(TAG,"解压完成");
           //Toast.makeText(context, "解压完成", Toast.LENGTH_SHORT).show();
       });
    }


    /**
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     *
     * @param baseDir     指定根目录
     * @param absFileName 相对路径名，来自于ZipEntry中的name
     * @return java.io.File 实际的文件
     */
    private File getRealFileName(String baseDir, String absFileName) {
        String[] dirs = absFileName.split("/");
        File ret = new File(baseDir);
        String substr = null;
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                substr = dirs[i];
                try {
                    substr = new String(substr.getBytes("8859_1"), "GB2312");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ret = new File(ret, substr);

            }
            Log.d(TAG, "1ret = " + ret);
            if (!ret.exists())
                ret.mkdirs();
            substr = dirs[dirs.length - 1];
            try {
                substr = new String(substr.getBytes("8859_1"), "GB2312");
                Log.d(TAG, "substr = " + substr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ret = new File(ret, substr);
            Log.d(TAG, "2ret = " + ret);
            return ret;
        }
        return ret;
    }

    /**
     * 在/data/data/下创建一个res文件夹，存放4个文件
     */
    private void copyDbFile(Context context, String fileName) {
        InputStream in = null;
        FileOutputStream out = null;
        File file = new File(zipStartFilePath + fileName);

        //创建文件夹
        File filePath = new File(zipStartFilePath);
        if (!filePath.exists())
            filePath.mkdirs();

        if (file.exists())
            return;

        try {
            in = context.getAssets().open(fileName); // 从assets目录下复制
            out = new FileOutputStream(file);
            int length = -1;
            byte[] buf = new byte[1024];
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
