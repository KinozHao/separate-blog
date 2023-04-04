package com.kinoz.service.impl.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Link;
import com.kinoz.domain.vo.LinkVo;
import com.kinoz.service.LinkService;
import com.kinoz.mapper.LinkMapper;
import com.kinoz.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
}




