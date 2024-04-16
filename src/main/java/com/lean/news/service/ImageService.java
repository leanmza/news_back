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

  /*  @Transactional
    public Image saveImage(MultipartFile imageFile) throws MyException {

        if (imageFile != null) {
            try {
                Image image = new Image();

                image.setName(imageFile.getName());
                image.setMime(imageFile.getContentType());
                image.setContent(imageFile.getBytes());
                return imageRepository.save(image);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return null;
    }

    public Image actualizeImage(String idImage, MultipartFile imageFile) throws MyException {

        if (imageFile != null) {

            try {

                Image image = new Image();

                if (idImage != null) {
                    Optional<Image> optionalImage = imageRepository.findById(idImage);

                    if (optionalImage.isPresent()) {
                        image = optionalImage.get();
                    }
                }

                image.setName(imageFile.getName());
                image.setMime(imageFile.getContentType());
                image.setContent(imageFile.getBytes());
                return imageRepository.save(image);

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return null;
    }

    @Transactional(readOnly = true)
    public List<Image> listarTodos() {
        return imageRepository.findAll();
    }
    
    
    @Transactional
    public Image getOne(String id) {
        return imageRepository.getOne(id);
    }*/
}
