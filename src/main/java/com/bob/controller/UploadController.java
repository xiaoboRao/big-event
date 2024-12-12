package com.bob.controller;


import com.bob.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("D:\\" + fileName));
        return Result.success("url 访问地址...");
    }
}

