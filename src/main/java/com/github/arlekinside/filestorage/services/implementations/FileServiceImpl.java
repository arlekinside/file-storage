package com.github.arlekinside.filestorage.services.implementations;

import com.github.arlekinside.filestorage.exceptions.FileNotFoundException;
import com.github.arlekinside.filestorage.exceptions.TagsException;
import com.github.arlekinside.filestorage.models.File;
import com.github.arlekinside.filestorage.repositories.FileRepository;
import com.github.arlekinside.filestorage.services.FileService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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

    @Override
    public File get(String id) throws FileNotFoundException {
        return repository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found"));
    }

    @Override
    public void updateTags(String id, List<String> tags) throws IOException, TagsException {
        if (tags == null || tags.isEmpty()) throw new TagsException("Tags cannot be empty");
        repository.updateTags(id, tags);
    }

    @Override
    public void deleteTags(String id, List<String> tags) throws FileNotFoundException, TagsException, IOException {
        List<String> t = get(id).getTags();
        if (tags == null || tags.isEmpty() || t == null || !t.containsAll(tags)) throw new TagsException("Impossible to delete the tag");
        t.removeAll(tags);
        repository.updateTags(id, t);
    }

    @Override
    public void delete(String id) throws FileNotFoundException {
        if (!repository.existsById(id)) throw new FileNotFoundException("File not found");
        repository.deleteById(id);
    }

    @Override
    public long total() {
        return repository.count();
    }

    public List<File> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<File> findByTags(List<String> tags, int page, int size) {
        return repository.findByTagsEquals(PageRequest.of(page, size), tags);
    }
}