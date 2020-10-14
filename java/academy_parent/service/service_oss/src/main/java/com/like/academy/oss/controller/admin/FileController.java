package com.like.academy.oss.controller.admin;


import com.like.academy.oss.service.FileService;
import com.like.academy.server.base.excetpion.AcademyException;
import com.lk.academy.common.base.result.Result;
import com.lk.academy.common.base.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author likeLove
 * @time 2020-08-26  15:54
 */
@CrossOrigin
@Api (tags = "阿里云OSS文件管理")
@RestController
@RequestMapping ("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation ("文件上传")
    @PostMapping ("/upload")
    public Result upload(@ApiParam (value = "需要上传的文件", required = true) @RequestParam ("file") MultipartFile file,
                         @ApiParam (value = "文件夹名字", required = true) @RequestParam ("module") String module) {
        String url = null;
        try {
            InputStream is = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            url = fileService.upload(is, module, originalFilename);
        } catch (Exception e) {
            //文件上传失败错误
            throw new AcademyException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        return Result.ok().message("文件上传成功").data("url", url);
    }

    @ApiOperation ("文件删除")
    @DeleteMapping ("/del")
    public Result del() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok().message("文件删除成功");
    }
}
