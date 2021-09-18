package com.github.arlekinside.filestorage.models.responces;

import com.github.arlekinside.filestorage.models.File;
import com.github.arlekinside.filestorage.models.responses.PageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

@SpringBootTest
public class PageResponseTests {

    @Test
    public void responsePageBuildTestOnCorrectPage() {
        File testFile1 = new File("file1", 1);
        File testFile2 = new File("file2", 2);
        PageResponse.PageResponseBuilder builder = new PageResponse.PageResponseBuilder(
                Arrays.asList(testFile1, testFile2));
        PageResponse response = builder.size(1).pageNum(1).build();
        Assertions.assertEquals(testFile2, response.getPage().get(0));
    }

    @Test
    public void responsePageBuildTestOnIncorrectPage() {
        File testFile1 = new File("file1", 1);
        PageResponse.PageResponseBuilder builder = new PageResponse.PageResponseBuilder(
                Arrays.asList(testFile1, testFile1));
        PageResponse response = builder.size(1).pageNum(2).build();
        Assertions.assertTrue(response.getPage().isEmpty());
    }
}
