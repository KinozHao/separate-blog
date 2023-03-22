package com.kinoz.utils;

import com.kinoz.constant.OSSConstant;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;


import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @Author Hao Kinoz
 * @Description 腾讯对象存储上传 尝试过后不知道如何在上传头像功能中运用
 * @Date 2023/3/22
 **/
public class TencentOssUtils {

    public static final TencentOssUtils INSTANCE = new TencentOssUtils();

    private final String bucketName;

    public final COSClient cosClient;
    TencentOssUtils() {
        COSCredentials cred = new BasicCOSCredentials(OSSConstant.SECRET_ID, OSSConstant.SECRET_KEY);
        // 这里填写自己的region 我的是北京 在存储桶列表的所属区域中查看
        Region region = new Region("ap-nanjing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 填写自己的存储桶名称 在存储桶列表的存储同名称处查看
        bucketName = "blog-1310626923";
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }



    /**
     * 上传文件到服务器 如果key重复将被覆盖
     */
    public PutObjectResult put(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        System.out.println("获取上传文件成功:"+putObjectRequest);
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 获取带权限的下载链接
     */
    public URL getSourceUrl(String key) {
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);
        return cosClient.generatePresignedUrl(req);
    }
    /**
     * 删除已上传的资源
     */
    public void deleteSource(String key) {
        cosClient.deleteObject(bucketName, key);
    }


}
