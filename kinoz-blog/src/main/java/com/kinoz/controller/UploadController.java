package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Hao Kinoz
 * @Description 采用七牛云的oss
 * @Date 2023/3/22
 **/
@RestController
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}




