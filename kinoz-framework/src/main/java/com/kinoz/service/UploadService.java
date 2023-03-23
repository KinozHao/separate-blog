package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/22
 **/
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
