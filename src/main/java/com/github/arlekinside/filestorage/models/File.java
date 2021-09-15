package com.github.arlekinside.filestorage.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Objects;

@Document(indexName = "files")
public class File {

    @Id
    private int id;
    private String name;
    private int size;
    private List<String> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return id == file.id && size == file.size && name.equals(file.name) && Objects.equals(tags, file.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, size, tags);
    }
}
