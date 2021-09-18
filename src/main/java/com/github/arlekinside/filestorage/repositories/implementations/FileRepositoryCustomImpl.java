package com.github.arlekinside.filestorage.repositories.implementations;

import com.github.arlekinside.filestorage.models.File;
import com.github.arlekinside.filestorage.repositories.FileRepositoryCustom;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class FileRepositoryCustomImpl implements FileRepositoryCustom<File,String> {

    private final RestHighLevelClient client;

    public FileRepositoryCustomImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public void updateTags(String id, List<String> tags) throws IOException {
        String index = Arrays.stream(File.class.getAnnotationsByType(Document.class)).findFirst().orElseThrow().indexName();
        UpdateRequest updateRequest = new UpdateRequest()
                .id(id)
                .index(index)
                .doc(XContentFactory.jsonBuilder().startObject()
                        .field("tags", tags)
                        .endObject());
        client.update(updateRequest, RequestOptions.DEFAULT);
    }
}
