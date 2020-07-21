package com.example.demo;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    void contextLoads() throws FileNotFoundException {
        File file = new File("C:\\Users\\ruofan\\Desktop\\test.jpg");

        String filename = file.getName();
        String extName  = filename.substring(filename.lastIndexOf(".")+1);

        FileInputStream fileInputStream = new FileInputStream(file);
        StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream, file.length(), extName, null);

        System.out.println(storePath.getGroup());
        System.out.println(storePath.getPath());
        System.out.println(storePath.getFullPath());

    }




}
