package com.kinoz.service.impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.Gson;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.exception.SystemException;
import com.kinoz.mapper.UserMapper;
import com.kinoz.service.UploadService;
import com.kinoz.service.UserService;
import com.kinoz.utils.PathUtils;
import com.kinoz.utils.SecurityUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author kinoz
 * @create 2023-03-25 11:14
 * 4/17问题解决 oss的对应值应该在blog的application中
 * 需要天添加@service下的两个注解
*/
@Service
@ConfigurationProperties(prefix = "oss")
@Data
public class UploadServiceImpl implements UploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


        @Override
    public ResponseResult uploadImg(@JSONField(serialize = false)MultipartFile img) {
        //判断文件类型
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        /*if(!originalFilename.endsWith(".png")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }*/

        //如果判断通过上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        //格式:2099/2/3/wqeqeqe.png
        String url = uploadOss(img,filePath);

        //将头像上传至数据库
        /*Long userId = SecurityUtils.getUserId();
        User user = new User();
        user.setAvatar(url);
        user.setId(userId);
        userMapper.updateById(user);*/
        return ResponseResult.okResult(url);
    }


    private String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
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
                //更换为自己bucket的前缀地址
                return "http://rrybt3j8m.hd-bkt.clouddn.com/"+key;
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
