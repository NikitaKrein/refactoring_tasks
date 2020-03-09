package com.epam.engx.cleancode.naming.task5;


import com.epam.engx.cleancode.naming.task5.thirdpartyjar.Predicate;

public class FileExtensionPredicate implements Predicate<String> {

    private String[] fileExtensions;

    FileExtensionPredicate(String[] extensions) {
        this.fileExtensions = extensions;
    }

    @Override
    public boolean findExtension(String fileName) {
        for (String extension : fileExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
