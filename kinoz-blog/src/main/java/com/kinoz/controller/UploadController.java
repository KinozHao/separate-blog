package com.kinoz.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.kinoz.domain.ResponseResult;
import com.kinoz.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/25
 **/
@RestController
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(@JSONField(serialize = false) MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
