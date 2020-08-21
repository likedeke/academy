package com.like.academy.edu.service.impl;

import com.like.academy.edu.entity.Comment;
import com.like.academy.edu.mapper.CommentMapper;
import com.like.academy.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author like
 * @since 2020-08-20
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
