package com.github.arlekinside.filestorage.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Api response model to POST /file
 */
public class PostFileResponse {

    @JsonProperty("ID")
    private final String id;

    public PostFileResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostFileResponse that = (PostFileResponse) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PostFileResponse{" +
                "id=" + id +
                '}';
    }
}
