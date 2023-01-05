package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getUserFiles(String username){
        return fileMapper.getUserFiles(username);
    }

    public List<File> getUserFilesById(Integer userId){
        return fileMapper.getUserFilesById(userId);
    }

    public File getFile(String fileName){
        return fileMapper.getFile(fileName);
    }

    public File getFileById(Integer fileId){
        return fileMapper.getFileById(fileId);
    }

    public int createNewFile(File file) {
        return fileMapper.addFile(file);
    }

    public int deleteFile(Integer fileId){
        return fileMapper.deleteFileById(fileId);
    }
}
