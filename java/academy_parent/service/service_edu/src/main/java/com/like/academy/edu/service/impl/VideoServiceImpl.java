package com.like.academy.edu.service.impl;

import com.like.academy.edu.entity.Video;
import com.like.academy.edu.mapper.VideoMapper;
import com.like.academy.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author like
 * @since 2020-08-20
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
