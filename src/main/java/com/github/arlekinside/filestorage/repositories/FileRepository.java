package com.github.arlekinside.filestorage.repositories;

import com.github.arlekinside.filestorage.models.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@org.springframework.stereotype.Repository
public interface FileRepository extends ElasticsearchRepository<File, String> {

}
