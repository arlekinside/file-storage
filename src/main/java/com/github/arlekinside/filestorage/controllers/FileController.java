package com.github.arlekinside.filestorage.controllers;

import com.github.arlekinside.filestorage.exceptions.FileNotFoundException;
import com.github.arlekinside.filestorage.exceptions.TagsException;
import com.github.arlekinside.filestorage.models.File;
import com.github.arlekinside.filestorage.models.responses.PageResponse;
import com.github.arlekinside.filestorage.models.responses.PostFileResponse;
import com.github.arlekinside.filestorage.models.responses.SuccessResponse;
import com.github.arlekinside.filestorage.services.FileNameAnalyzer;
import com.github.arlekinside.filestorage.services.FileService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService service;
    private final FileNameAnalyzer analyzer;

    public FileController(FileService service, FileNameAnalyzer analyzer) {
        this.service = service;
        this.analyzer = analyzer;
    }

    @PostMapping
    public @ResponseBody
    PostFileResponse addFile(@Valid @RequestBody File file) {
        file.setTags(Collections.singletonList(analyzer.extensionToTag(file.getName())));
        return new PostFileResponse(service.save(file).getId());
    }

    @DeleteMapping("/{ID}")
    public @ResponseBody
    SuccessResponse deleteFile(@PathVariable String ID) throws FileNotFoundException {
        service.delete(ID);
        return new SuccessResponse();
    }

    @PostMapping("/{ID}/tags")
    public @ResponseBody
    SuccessResponse assignTags(@PathVariable String ID, @RequestBody List<String> tags) throws TagsException, IOException {
        service.updateTags(ID, tags);
        return new SuccessResponse();
    }

    @DeleteMapping("/{ID}/tags")
    public @ResponseBody
    SuccessResponse deleteTags(@PathVariable String ID, @RequestBody List<String> tags) throws FileNotFoundException, TagsException, IOException {
        service.deleteTags(ID, tags);
        return new SuccessResponse();
    }

    @GetMapping
    public @ResponseBody
    PageResponse listFiles(
            @RequestParam(name = "tags", required = false)
                    List<String> tags,
            @RequestParam(name = "page", required = false, defaultValue = "0")
                    int page,
            @RequestParam(name = "size", required = false, defaultValue = "10")
                    int size) throws TagsException {
        List<File> result = tags == null || tags.isEmpty() ? service.findAll(page, size) : service.findByTags(tags, page, size);
        return new PageResponse(result, service.total());
    }
}
