package com.github.arlekinside.filestorage.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Saved in ElasticSearch file's model
 */
@Document(indexName = "files")
public class File {

    @Id
    private String id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @DecimalMin(value = "1", message = "Size cannot be 0")
    private int size;
    private List<String> tags;

    public File() {
    }

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public File(String name, int size, List<String> tags) {
        this.name = name;
        this.size = size;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id.equals(file.id) && size == file.size && name.equals(file.name) && Objects.equals(tags, file.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, size, tags);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", tags=" + tags +
                '}';
    }
}
