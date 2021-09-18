package com.github.arlekinside.filestorage.repositories;

import com.github.arlekinside.filestorage.models.File;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface FileRepository extends ElasticsearchRepository<File, String>, FileRepositoryCustom<File, String> {

    List<File> findByTagsEquals(Pageable pageable, List<String> tags);
}
