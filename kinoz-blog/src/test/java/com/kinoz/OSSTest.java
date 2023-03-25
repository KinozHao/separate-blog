package com.kinoz;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.*;
import java.util.UUID;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/23
 **/
@SpringBootTest
@Data
public class OSSTest {
    @Value("${oss.accessKey}")
    String accessKey;
    @Value("${oss.secretKey}")
    String secretKey;
    @Value("${oss.bucket}")
    String bucket;

    @Test
    public void valueTest(){
        System.out.println(accessKey);
        System.out.println(secretKey);
        System.out.println(bucket);
    }

    @Test
    public void OSTest(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        /*String accessKey = "Ed4zcmU1BH1xsUD7rpf5qS00fh0VaxQID_KgT8PK";
        String secretKey = "dqnf1yXBBq1ML1MhT7lMDWwpFidnBjDgOL5quEfn";
        String bucket = "kinoz-blog";*/


        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString();

        try {
            /*byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);*/

            var file = new File("C:\\Users\\Hao\\Pictures\\Blog\\linux.png");
            var up_file = new FileInputStream(file);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(up_file,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            //ignore
        }

    }

}
