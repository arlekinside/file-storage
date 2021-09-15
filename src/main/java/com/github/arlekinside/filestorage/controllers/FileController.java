package com.github.arlekinside.filestorage.controllers;

import com.github.arlekinside.filestorage.models.File;
import com.github.arlekinside.filestorage.models.responses.PostFileResponse;
import com.github.arlekinside.filestorage.services.FileService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService service;
    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody PostFileResponse addFile(@Valid @RequestBody File file){

        return new PostFileResponse(service.save(file).getId());
    }



}
