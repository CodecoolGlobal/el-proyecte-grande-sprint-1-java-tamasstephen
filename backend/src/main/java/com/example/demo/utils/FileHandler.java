package com.example.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class FileHandler {

    public File createDirectory(String user){
        Path url = Paths.get("resources", "images");
        String absolutePath = url.toFile().getAbsolutePath();
        File myDir = new File( absolutePath + "/" + user);
        if (!myDir.exists()){
            boolean isCreated =  myDir.mkdirs();
        }
        return myDir;
    }

    public Optional<String> saveFile(MultipartFile file, String user){
        Path url = Paths.get("resources", "images", user);
        String absolutePath = url.toFile().getAbsolutePath();
        File fileNew = new File(absolutePath + "/" + file.getOriginalFilename());
        try{
            file.transferTo(fileNew);
        } catch (IOException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(fileNew.getAbsolutePath());
    }
}
