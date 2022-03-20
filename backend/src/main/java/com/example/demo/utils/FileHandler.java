package com.example.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileHandler {

    public File createDirectory(String user){
        Path url = Paths.get("resources", "images");
        String absolutePath = url.toFile().getAbsolutePath();
        File myDir = new File( absolutePath + "/" + user);
        if (!myDir.exists()){
            boolean isCreated =  myDir.mkdirs();
            System.out.println(isCreated ? "It was created" : "It was not created");
        }
        return myDir;
    }

    public boolean saveFile(MultipartFile file, String user){
        Path url = Paths.get("resources", "images", user);
        String absolutePath = url.toFile().getAbsolutePath();
        File fileNew = new File(absolutePath + "/" + file.getOriginalFilename());
        System.out.println(absolutePath);
        try{
            file.transferTo(fileNew);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public File getFile(String fileName, String user){
        Path url = Paths.get("resources", "images", user, fileName);
        String absolutePath = url.toFile().getAbsolutePath();
        System.out.println(absolutePath);
        File file = new File(absolutePath);
        System.out.println(file.exists());
        return file;
    }
}
