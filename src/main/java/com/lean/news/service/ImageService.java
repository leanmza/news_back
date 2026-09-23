package com.lean.news.service;

import com.lean.news.model.entity.Image;
import com.lean.news.repository.IImageRepository;
import java.util.List;
import java.util.Optional;
import com.lean.news.service.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository IImageRepository;

    @Override
    public Image save(Image image) {
        return IImageRepository.save(image);
    }

    public List<Image> list(){
        return IImageRepository.findByOrderById();
    }

    public Optional<Image> getOne(Long id){
        return IImageRepository.findById(id);
    }

    public boolean exists(Long id){
        return IImageRepository.existsById(id);
    }


    @Override
    public void delete(Long id) {
        IImageRepository.deleteById(id);
    }




}
