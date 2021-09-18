package com.github.arlekinside.filestorage.services.implementations;

import com.github.arlekinside.filestorage.services.FileNameAnalyzer;

import java.util.Map;


/**
 * Service to map file extensions to their filetypes
 */
public class FileNameAnalyzerImpl implements FileNameAnalyzer {

    private Map<String, String> map;

    @Override
    public String extensionToTag(String name) {
        String temp = name.trim().toLowerCase();
        if (!temp.contains(".")) return null;
        temp = temp.substring(temp.indexOf('.'));
        return map.get(temp);
    }

    /**
     * @param map Provide a Map implementation with following fields<br>
     *            <b>Key - Extension, Value - Tag</b><br><br>
     *            <i>Example:<br>
     *            map.put(".exe", "application")</i>
     */
    public FileNameAnalyzerImpl(Map<String, String> map) {
        this.map = map;
    }
}
