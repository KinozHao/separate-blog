package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.LinkDto;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.vo.PageVo;

/**
* @author haogu
* @description 针对表【sg_link(友链)】的数据库操作Service
* @createDate 2023-03-07 16:12:45
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult<PageVo> showLinkList(Integer pageNum, Integer pageSize, LinkDto linkDto);

    void add(LinkDto linkDto);

    void deleteById(Long id);

    void updateLink(LinkDto linkDto);

    Link getLink(Long id);
}