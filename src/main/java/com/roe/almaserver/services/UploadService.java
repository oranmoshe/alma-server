package com.roe.almaserver.services;

import com.roe.almaserver.configuration.FileStorageProperties;
import com.roe.almaserver.exceptions.FileStorageException;
import com.roe.almaserver.exceptions.model.Portfolio;
import com.roe.almaserver.exceptions.model.Syndicator;
import com.roe.almaserver.exceptions.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public UploadService(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    public File getFile(UploadedFile uploadedFile) {
        return getFileStorageLocation(uploadedFile.getPath()).toFile();
    }
    public String storeFile(MultipartFile file, Syndicator syndicator, Portfolio portfolio) {
        String path = syndicator.getId()+"/"+portfolio.getId();
        createFolder(path);

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = getFileStorageLocation(path).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return path+"/"+fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private void createFolder(String path){
        try {
            File dir = getFileStorageLocation(path).toFile();
            if(!dir.exists())
                dir.mkdirs();
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Path getFileStorageLocation(String path){
        return Paths.get(fileStorageProperties.getUploadDir()+"/"+path)
                .toAbsolutePath().normalize();
    }

}
