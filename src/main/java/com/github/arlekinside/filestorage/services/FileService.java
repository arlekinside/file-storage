package com.github.arlekinside.filestorage.services;

import com.github.arlekinside.filestorage.exceptions.FileNotFoundException;
import com.github.arlekinside.filestorage.exceptions.TagsException;
import com.github.arlekinside.filestorage.models.File;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {

    File save(File file);

    File get(String id) throws InstanceNotFoundException;

    void updateTags(String id, List<String> tags) throws IOException, TagsException;

    void deleteTags(String id, List<String> tags) throws FileNotFoundException, TagsException, IOException;

    void delete(String id) throws FileNotFoundException;

    long total();

    List<File> findAll(int page, int size);

    List<File> findByTags(List<String> tags, int page, int size) throws TagsException;
}
