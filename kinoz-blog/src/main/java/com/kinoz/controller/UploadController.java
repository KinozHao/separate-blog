package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/22
 **/
@RestController
public class UploadController {

    @Resource
    UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(File img){
        return uploadService.uploadImg(img);
    }
}
