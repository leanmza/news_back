//package com.lean.news.controller;
//
//import com.lean.news.model.entity.User;
//import com.lean.news.model.entity.Publication;
//import com.lean.news.service.NewsService;
//
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// *
// * @author Lean
// */
//@Controller
//@RequestMapping("/image")
//public class ImageController {
//
//    @Autowired
//    NewsService newsService;
//
//    @Autowired
//    CustomUserService customUserService;
//
//    @Transactional
//    @GetMapping("/news/{id}")
//    public ResponseEntity<byte[]> imageNews(@PathVariable String id) {
//        Publication publication = newsService.getOne(id);
//
//        byte[] image = publication.getImage().getContent();
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.IMAGE_JPEG);
//
//        return new ResponseEntity<>(image, headers, HttpStatus.OK);
//    }
//
//    @Transactional
//    @GetMapping("/profile/{id}")
//    public ResponseEntity<byte[]> profileUserNews(@PathVariable String id, HttpSession session) {
//
//        byte[] image = null;
//
//        if ((session.getAttribute("userSession") != null)) {
//
//           User user = customUserService.getOne(id); /// CustomUser ES LA CLASE PADRE, FUNCIONA CON READER Y WRITER
//
//            image = user.getProfileImage().getContent();
//
//            System.out.println("user reader");
//
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.IMAGE_JPEG);
//
//        return new ResponseEntity<>(image, headers, HttpStatus.OK);
//    }
//
//}
