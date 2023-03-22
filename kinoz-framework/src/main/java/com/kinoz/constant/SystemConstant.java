package com.kinoz.constant;

/**
 * @author kinoz
 * @Date 2023/2/28 14:18
 * @apiNote
 */
public class SystemConstant {

    /**
     * 文章相关 1草稿 0已发布
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    public static final int ARTICLE_STATUS_NORMAL = 0;


    /**
     * 分类相关 0正常 1禁用
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    public static final String CATEGORY_STATUS_DISABLE = "1";

    /**
     * 友联相关
     * 0审核通过 1审核未通过 2等待审核
     */
    public static final String FRIEND_LINK_STATUS_PASS = "0";
    public static final String FRIEND_LINK_STATUS_NOT_PASS = "1";
    public static final String FRIEND_LINK_STATUS_WAITING = "2";

    /**
     * 评论相关
     */
    public static final Long COMMENT_ROOT_ID = -1L;
    /**
     * 评论类型:文章
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型:友联
     */
    public static final String LINK_COMMENT = "1";
}
