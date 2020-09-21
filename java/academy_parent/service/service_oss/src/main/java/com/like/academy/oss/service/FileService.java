package com.like.academy.oss.service;

import java.io.InputStream;

/**
 * @author likeLove
 * @time 2020-08-26  15:29
 */
public interface FileService {
    /**
     * 阿里云oss文件上传
     * @param is               输入流
     * @param module           文件夹名字
     * @param originalFileName 原始文件名
     *
     * @return 文件url
     */
    String upload(InputStream is, String module, String originalFileName);
}
