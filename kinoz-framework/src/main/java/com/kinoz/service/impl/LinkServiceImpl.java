package com.kinoz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.LinkDto;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Article;
import com.kinoz.domain.pojo.ArticleTag;
import com.kinoz.domain.pojo.Link;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.LinkVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.LinkService;
import com.kinoz.mapper.LinkMapper;
import com.kinoz.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author haogu
* @description 针对表【sg_link(友链)】的数据库操作Service实现
* @createDate 2023-03-07 16:12:45
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService {

    /**
     * 在友链页面要查询出所有的审核通过的友链。
     * @return
     */
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        //判断友联是否通过
        wrapper.eq(Link::getStatus, SystemConstant.FRIEND_LINK_STATUS_PASS);
        List<Link> links = list(wrapper);

        //转换vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }


    //-------------------Admin部分-----------------------
    @Resource
    LinkMapper linkMapper;
    //展示友联
    @Override
    public ResponseResult<PageVo> showLinkList(Integer pageNum, Integer pageSize, LinkDto linkDto) {
        //分页查询
        var wrapper = new LambdaQueryWrapper<Link>();
        wrapper.eq(StringUtils.hasText(linkDto.getName()),Link::getName,linkDto.getName());
        wrapper.eq(StringUtils.hasText(linkDto.getStatus()),Link::getStatus,linkDto.getStatus());
        var page = new Page<Link>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public void add(LinkDto linkDto) {
        //添加友联
        var wrapper = new LambdaQueryWrapper<Link>();
        wrapper.select(Link::getId).eq(Link::getName, linkDto.getName());
        Link existLink = linkMapper.selectOne(wrapper);
        Assert.isNull(existLink, linkDto.getName() + "友联已存在");

        Link newLink = Link.builder()
                .id(linkDto.getId())
                .name(linkDto.getName())
                .description(linkDto.getDescription())
                .address(linkDto.getAddress())
                .logo(linkDto.getLogo())
                .status(linkDto.getStatus()).build();
        baseMapper.insert(newLink);
    }

    @Override
    public void deleteById(Long id) {
        linkMapper.deleteById(id);
    }

    @Override
    public void updateLink(LinkDto linkDto) {
        var wrapper = new LambdaQueryWrapper<Link>();
        wrapper.select(Link::getId).eq(Link::getName,linkDto.getName());
        Link existLink = linkMapper.selectOne(wrapper);
        Assert.isNull(existLink,linkDto.getName()+"友联已存在");

        Link newLink = Link.builder()
                .id(linkDto.getId())
                .name(linkDto.getName())
                .description(linkDto.getDescription())
                .address(linkDto.getAddress())
                .logo(linkDto.getLogo())
                .status(linkDto.getStatus()).build();
        baseMapper.updateById(newLink);
    }

    @Override
    public Link getLink(Long id) {
        Link link = linkMapper.selectById(id);
        if (link == null){
            throw new RuntimeException("查询的友联不存在");
        }
        return link;
    }
}




