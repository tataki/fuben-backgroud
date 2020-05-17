package com.foo.common.utils;

import com.foo.common.config.PreReadUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtils {
    public static String  uploadFile(String path, MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        fileName = UUID.randomUUID() + suffixName;
        String upPath=path+fileName;
        File targetFile = new File(upPath);
        file.transferTo(targetFile);
        return  fileName;
    }
    public static String getUrl(String ip,String post,String staticAccessPath) {
        String url="http://"+ip+":"+post+"/static/"+staticAccessPath;
        return  url;
    }

}
