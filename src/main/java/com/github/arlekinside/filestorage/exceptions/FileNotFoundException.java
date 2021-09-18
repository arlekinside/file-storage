package com.github.arlekinside.filestorage.exceptions;

import javax.management.InstanceNotFoundException;

/**
 * @see InstanceNotFoundException
 */
public class FileNotFoundException extends InstanceNotFoundException {

    public FileNotFoundException(String message) {
        super(message);
    }

}
