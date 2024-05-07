package com.lean.news.controller;


import com.lean.news.model.entity.Image;
import com.lean.news.service.CloudinaryService;
import com.lean.news.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list() {
        List<Image> list = imageService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@RequestParam String id) {
        System.out.println(id);

        return ResponseEntity.ok().body(imageService.getOne(id));
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<?> getImage(@PathVariable String fileId) throws IOException {
        Image imageData = imageService.getOne(fileId).get();
        return ResponseEntity.status(HttpStatus.OK).body(imageData);

    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity("Imágen no válida", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image();
        image.setName((String) result.get("original_filename"));
        image.setImageUrl((String) result.get("url"));
        image.setCloudinaryId((String) result.get("public_id"));

        imageService.save(image);
        return new ResponseEntity(image, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException {
        if (!imageService.exists(id)) {
            return new ResponseEntity("No existe la imagen", HttpStatus.NOT_FOUND);
        }
        Image image = imageService.getOne(id).get();
        Map result = cloudinaryService.delete(image.getCloudinaryId());
        imageService.delete(id);
        return new ResponseEntity("Imagen eliminada", HttpStatus.OK);
    }
}