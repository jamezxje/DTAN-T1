package com.capstone.app.service.common.impl;

import com.capstone.app.entity.Image;
import com.capstone.app.entity.type.ImageType;
import com.capstone.app.repository.ImageRepo;
import com.capstone.app.service.common.FilesStorageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements FilesStorageService {

    private final ImageRepo imageRepo;
    private final Path root = Paths.get("uploads");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Image save(MultipartFile file) {

        Image image = parseInfo(file);

        if (image == null) return null;

        try {
            Files.copy(file.getInputStream(), Path.of(image.getImageUrl()) );

            imageRepo.save(image);

            if (image.getImageId() == null){
                return null;
            }

            return image;

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<Image> saveAll(MultipartFile[] files) {

        List<Image> images = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                Image image = parseInfo(file);
                if (image == null) continue; 
                Files.copy(file.getInputStream(), Path.of(image.getImageUrl()) );
                images.add(image);
            }

            List<Image> savedImages = imageRepo.saveAll(images);

            if (savedImages.size() == images.size())
                return savedImages;
            else {
                for (Image image : images) {
                    Files.deleteIfExists(Path.of(image.getImageUrl()));
                }

                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();

            if ( ! images.isEmpty() ) {

                try {
                    for (Image image : images) {
                        Files.deleteIfExists(Path.of(image.getImageUrl()));
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

            return null;
        }
    }

    @Override
    public void deleteAll(List<Image> images) {
        try {
            for (Image image : images) {
                Files.deleteIfExists(Path.of(image.getImageUrl()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Image image) {
        try {
            Files.deleteIfExists(Path.of(image.getImageUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image parseInfo(MultipartFile file) {

        if(file.isEmpty()){
            return null;
        }

        String prefix = UUID.randomUUID().toString();
        String fileDestinationName = prefix.concat("-").concat( convertToSlug(file.getOriginalFilename()) );

        ImageType imageType = ImageType.JPG;

        switch (file.getContentType().toLowerCase()) {
            case "image/png":
                imageType = ImageType.PNG;
                break;
            case "image/jpeg":
                imageType = ImageType.JPEG;
                break;
            case "image/jpg":
                imageType = ImageType.JPG;
                break;
            case "image/webp":
                imageType = ImageType.WEBP;
                break;
            default:
                // not allowed
                return null;
        }

        Image image = new Image(
                file.getOriginalFilename(), imageType,
                this.root.resolve(fileDestinationName).toString().replace("uploads\\", "uploads/"),
                extractFileNameWithoutExtension(file.getOriginalFilename()));
        return image;
    }

    public static String extractFileNameWithoutExtension(String fileName) {
        if (fileName == null) {
            return null;
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName;
        }

        return fileName.substring(0, lastDotIndex);
    }

    public static String convertToSlug(String fileName) {
        if (fileName == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("^(.*?)(\\.[^.]+)?$");
        Matcher matcher = pattern.matcher(fileName);
        if (!matcher.matches()) {
            return fileName;
        }

        String name = matcher.group(1);
        String extension = matcher.group(2);

        String slug = name.toLowerCase().replaceAll("\\s", "-");

        if (extension != null) {
            slug += extension;
        }

        return slug;
    }
}
