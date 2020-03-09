package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_EXTENSIONS = {"jpg", "png"};
    private static final String[] DOCUMENT_EXTENSIONS = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String dirPath = basePath + File.separator;
        return Paths.get(dirPath, fileName).toFile();
    }

    public List<String> listAllImages() {
        return getAllFiles(basePath, IMAGE_EXTENSIONS);
    }

    public List<String> listAllDocumentFiles() {
        return getAllFiles(basePath, DOCUMENT_EXTENSIONS);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        FileExtensionPredicate imageExtensionsPredicate = new FileExtensionPredicate(IMAGE_EXTENSIONS);
        return !imageExtensionsPredicate.findExtension(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        FileExtensionPredicate documentExtensionsPredicate = new FileExtensionPredicate(DOCUMENT_EXTENSIONS);
        return !documentExtensionsPredicate.findExtension(fileName);
    }

    private List<String> getAllFiles(String directoryPath, String[] allowedExtensions) {
        final FileExtensionPredicate predicate = new FileExtensionPredicate(allowedExtensions);
        return Arrays.asList(getDirectory(directoryPath).list(getFilenameFilterByPredicate(predicate)));
    }

    private FilenameFilter getFilenameFilterByPredicate(final FileExtensionPredicate predicate) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File directory, String fileName) {
                return predicate.findExtension(fileName);
            }
        };
    }

    private File getDirectory(String directoryPath) {
        File fileDirectory = new File(directoryPath);
        validateDirectory(fileDirectory);
        return fileDirectory;
    }

    private void validateDirectory(File directory) {
        if (isNotDirectory(directory)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directory.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File directory) {
        return !directory.isDirectory();
    }

}