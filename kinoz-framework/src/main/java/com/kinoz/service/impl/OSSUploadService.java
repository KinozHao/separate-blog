package com.kinoz.service.impl;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.UploadService;
import com.kinoz.utils.TencentOssUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.UUID;

/**
 * @Author Hao Kinoz
 * @Description 用户头像上传接口
 * @Date 2023/3/22
 **/
@Service
public class OSSUploadService implements UploadService {
    @Override
    public ResponseResult uploadImg(File img) {
        //判断文件大小,类型

        //TODO 判断通过上传到OSS

        return ResponseResult.okResult();
    }
}
