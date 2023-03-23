package com.kinoz.service.impl;



import com.google.gson.Gson;
import com.kinoz.domain.ResponseResult;
import com.kinoz.service.UploadService;
import com.kinoz.service.UserService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author coldplay
 * @create 2023-03-08 11:14
 */
/*TODO 测试时候上传不存在问题,POSTMAN测试返回状态200没问题,但七牛云存储桶并没有接收到图片
    可能存在延迟问题明天查看七牛云是否接受到图片,若没接收到跳过此接口*/
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {

    @Override
    public ResponseResult uploadImg(MultipartFile img) {

        String url = uploadOss(img);

        return ResponseResult.okResult(url);
    }

    private String accessKey;
    private String secretKey;
    private String bucket;


    private String uploadOss(MultipartFile imgFile){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID()+".png";

        /*String accessKey= "Ed4zcmU1BH1xsUD7rpf5qS00fh0VaxQID_KgT8PK";
        String secretKey= "dqnf1yXBBq1ML1MhT7lMDWwpFidnBjDgOL5quEfn";
        String bucket= "kinoz-blog";*/

        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "nn";
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }

}

