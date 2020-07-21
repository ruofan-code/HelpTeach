package com.example.demo;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

//    @Test
//    void contextLoads() throws FileNotFoundException {
//        File file = new File("C:\\Users\\ruofan\\Desktop\\test.jpg");
//
//        String filename = file.getName();
//        String extName  = filename.substring(filename.lastIndexOf(".")+1);
//
//        FileInputStream fileInputStream = new FileInputStream(file);
//        StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream, file.length(), extName, null);
//
//        System.out.println(storePath.getGroup());
//        System.out.println(storePath.getPath());
//        System.out.println(storePath.getFullPath());
//
//    }




}
