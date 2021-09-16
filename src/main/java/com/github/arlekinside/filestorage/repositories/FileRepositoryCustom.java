package com.github.arlekinside.filestorage.repositories;

import java.io.IOException;
import java.util.List;

/**
 * Custom repository to update file's tags
 * @param <T> Type of object storable in the database
 * @param <ID> Type of object's ID field
 */
public interface FileRepositoryCustom<T,ID> {

    void updateTags(ID id, List<String> tags) throws IOException;
}
