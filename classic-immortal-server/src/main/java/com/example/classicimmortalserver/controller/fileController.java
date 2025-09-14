package com.example.classicimmortalserver.controller;


import cn.hutool.core.io.FileUtil;
import com.example.classicimmortalserver.common.Result;
import com.example.classicimmortalserver.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class fileController {
//文件上传的目录路径，在你项目的根目录
    private static  final  String filePath=System.getProperty("user.dir")+"/files/";

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {//文件流的形式接受发过来的文件
    String originalFilename = file.getOriginalFilename();//拿到文件的名字
//上传文件的地址
    if (!FileUtil.isDirectory(filePath)) {//如果目录不存在
        FileUtil.mkdir(filePath);//创建目录
    }
    String fileName=System.currentTimeMillis()+"."+originalFilename;
    String realPath=filePath+fileName;
    try {
        FileUtil.writeBytes(file.getBytes(),realPath);//文件写入磁盘中
    } catch (IOException e) {
        e.printStackTrace();//错误在控制台打印
        throw new CustomException("500","文件上传失败");

    }
        String url="http://localhost:9091/files/download/"+fileName;
        return  Result.success(url);

}

    /**
     * 文件下载
     * @param fileName
     * @param response
     */
    @GetMapping("/download/{fileName}")
//通过response以流的形式写出客户端
    public void download(@PathVariable String fileName, HttpServletResponse response) {
    try {
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream");
        OutputStream os = response.getOutputStream();
        String realPath = filePath + fileName;//完整的文件路径
        //获取到文件的字节数组
        byte[] bytes = FileUtil.readBytes(realPath);
        os.write(bytes);
        os.flush();
        os.close();
    } catch (IOException e) {
        e.printStackTrace();
        throw new CustomException("500", "文件下载失败");
    }
   }

}

































