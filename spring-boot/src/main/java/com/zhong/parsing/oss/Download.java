package com.zhong.parsing.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

/**
 * @date 2022/6/17 18:25
 */
@Service
public class Download {

    public static void main(String[] args) {
        String endpoint = "xxx";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "xxxx";
        String accessKeySecret = "xxx";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "xxxx";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "bbmall/meta/unit/0a4f389bdef8bd8db91e4950e709c532.json";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
//            OSSObject ossObject = ossClient.getObject(bucketName, objectName);


            Date expiration = new Date();
            expiration.setTime(System.currentTimeMillis() + 30 * 1000 * 30);
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            System.out.println(" ossClient.generatePresignedUrl == " + url.toString());
            OSSObject ossObject = ossClient.getObject(url, new HashMap<>());


            // 读取文件内容。
            System.out.println("Object content:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            reader.close();
            // ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            ossObject.close();

        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
        } catch (Throwable ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }


}
