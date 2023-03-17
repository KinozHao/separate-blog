package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author LiuMiss
 * @Description
 * @Date 2023/3/17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {

    private String token;
    private UserInfoVo userInfo;
}
