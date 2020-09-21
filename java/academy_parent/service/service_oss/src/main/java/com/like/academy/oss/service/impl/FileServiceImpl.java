package com.like.academy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.like.academy.oss.service.FileService;
import com.like.academy.oss.utils.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author likeLove
 * @time 2020-08-26  15:32
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    OssProperties ossProperties;

    @Override
    public String upload(InputStream is, String module, String originalFileName) {
        //读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();
        //创建oss
        OSS oss = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        //判断这个bucket是否存在
        if (!oss.doesBucketExist(bucketname)) {
            oss.createBucket(bucketname);//创建
            oss.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);//设置为公共读
        }
        //objectName: 文件路径 avatar/2020/08/26/default.jpg
        String folder = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String key = module + "/" + folder + "/" + fileName + fileExtension;
        //上传文件流
        oss.putObject(bucketname, key, is);
        oss.shutdown();
        return "https://" + bucketname + "." + endpoint + "/" + key;
    }
}
