package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/files")
public class FileController {

    private final UserService userService;
    private final FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping()
    public String createFile(
            Authentication authentication,
            @ModelAttribute("newFile") FileForm newFile,
            Model model) throws IOException {

        String userName = authentication.getName();
        User user = userService.getUser(userName);
        MultipartFile multipartFile = newFile.getFile();

        fileService.createNewFile(new File(
                null,
                newFile.getFile().getOriginalFilename(),
                newFile.getFile().getContentType(),
                newFile.getFile().getSize(),
                user.getUserId(),
                multipartFile.getBytes()
        ));
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping( "/{id}/download")
    public void view(
            @PathVariable int id,
            HttpServletResponse response,
            Model model
    ) throws IOException {
        File file = fileService.getFileById(id);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getFileName();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(file.getFileData());
        outputStream.close();

    }

    @GetMapping("/{id}/delete")
    public String delete(
            @PathVariable int id,
            Model model){

        fileService.deleteFile(id);
        model.addAttribute("result", "success");
        return "result";
    }
}
