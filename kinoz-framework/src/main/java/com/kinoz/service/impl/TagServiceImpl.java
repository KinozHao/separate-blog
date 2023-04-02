package com.kinoz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.service.TagService;
import com.kinoz.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author Hao
* @description 针对表【sg_tag(标签)】的数据库操作Service实现
* @createDate 2023-04-02 18:49:37
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




