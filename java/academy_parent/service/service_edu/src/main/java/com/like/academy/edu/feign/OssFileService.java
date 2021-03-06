package com.like.academy.edu.feign;

import com.lk.academy.common.base.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @author likeLove
 * @time 2020-08-27  20:47
 */
@Service
@FeignClient ("service-oss") // 注册在nacos中服务的名字
public interface OssFileService {
    /**
     * openFeign 远程调用文件删除
     *
     * @return Result
     */
    @ApiOperation ("文件删除")
    // 在oss服务中完成的调用地址
    @DeleteMapping ("/admin/oss/file/del")
    Result del();
}
