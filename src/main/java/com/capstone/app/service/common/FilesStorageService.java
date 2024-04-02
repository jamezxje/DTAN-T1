package com.capstone.app.service.common;

import com.capstone.app.entity.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesStorageService {
    Image save(MultipartFile file);
    List<Image> saveAll(MultipartFile[] file);
    void deleteAll(List<Image> images);
    void delete(Image image);
}
