package com.kinoz.utils;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kinoz.domain.entity.Article;
import com.kinoz.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kinoz
 * @Date 2023/2/28 14:32
 * @apiNote
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){

    }

    /**
     * 用于把一个对象中的单个数据拷贝到VO对象中
     * @param source
     * @param vClass
     * @return
     * @param <V>
     */
    public static <V> V copyBean(Object source,Class<V> vClass){
        V result = null;
        try {
            result = vClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source,result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 用于把一个集合对象拷贝到vo对象中
     * @param list
     * @param vClass
     * @return
     * @param <V>
     */
    public static <V> List<V> copyBeanList(List<?> list, Class<V> vClass){
        return list.stream()
                .map(o -> copyBean(o, vClass))
                .collect(Collectors.toList());
    }



    public static void main(String[] args) {
        //测试没有问题
        Article article = new Article();
        article.setId(1L);
        article.setTitle("dfafad");
        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo);
    }
}
