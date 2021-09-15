package com.github.arlekinside.filestorage.services.implementations;

import com.github.arlekinside.filestorage.models.File;
import com.github.arlekinside.filestorage.repositories.FileRepository;
import com.github.arlekinside.filestorage.services.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    public FileServiceImpl(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public File save(File file) {
        return repository.save(file);
    }
}