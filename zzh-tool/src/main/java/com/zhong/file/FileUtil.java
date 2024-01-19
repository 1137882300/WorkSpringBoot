package com.zhong.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: juzi
 * @date: 2024/1/19
 * @desc:
 */
public class FileUtil {


    /**
     * @author juzi
     * @date 2024/1/12 下午 2:34
     * @description 下载图片，通过 图片URL
     */
    public static class FileDownloader {

        public static class DownloadTask implements Runnable {
            private final String imageUrl;
            private final String outputFolder;

            public DownloadTask(String imageUrl, String outputFolder) {
                this.imageUrl = imageUrl;
                this.outputFolder = outputFolder;

                File folder = new File(outputFolder);
                if (!folder.exists()) {
                    if (folder.mkdir()) {
                        System.out.println("输出文件夹已创建: " + outputFolder);
                    } else {
                        System.err.println("无法创建输出文件夹: " + outputFolder);
                    }
                }
            }

            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                    int responseCode = httpConn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                        InputStream inputStream = httpConn.getInputStream();
                        OutputStream outputStream = Files.newOutputStream(Paths.get(outputFolder + "/" + filename));
//                        FileOutputStream outputStream = new FileOutputStream(outputFolder + "/" + filename);
                        int bytesRead;
                        byte[] buffer = new byte[4096];
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.close();
                        inputStream.close();
                        System.out.println("下载成功: " + filename);
                    } else {
                        System.out.println("下载失败：" + imageUrl + "___" + responseCode);
                    }
                    httpConn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
