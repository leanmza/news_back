package com.lean.news.service;

import com.lean.news.model.entity.Image;
import com.lean.news.model.repository.ImageRepository;
import java.util.List;
import java.util.Optional;
import com.lean.news.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> list(){
        return imageRepository.findByOrderById();
    }

    public Optional<Image> getOne(String id){
        return imageRepository.findById(id);
    }

    public boolean exists(String id){
        return imageRepository.existsById(id);
    }


    @Override
    public void delete(String id) {
        imageRepository.deleteById(id);
    }




}
