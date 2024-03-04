/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *//*

package com.lean.news.controller;

import com.lean.news.model.entity.Publication;
import com.lean.news.enums.Category;
import com.lean.news.exception.MyException;
import com.lean.news.service.NewsService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

*/
/**
 *
 * @author Lean
 *//*

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @GetMapping("/createNews")
    public String createNews(ModelMap model) {

        model.addAttribute("categorys", Category.values());

        return "createNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @PostMapping("/postNews")
    public String createNews(@RequestParam String title, @RequestParam String body,
            @RequestParam String category, @RequestParam(required = false) boolean subscriberContent, MultipartFile imageFile, Principal principal)
            throws MyException {

        try {

            String writerEmail = principal.getName();

            newsService.createNews(title, body, imageFile, writerEmail, category, subscriberContent);

            return "redirect:/";

        } catch (Exception e) {
            System.out.println("Error en la carga de la noticia" + e.getMessage());
            return "createNews.html";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/editNews/{id}")
    public String editNews(@PathVariable String id, ModelMap model) {

        Publication publication = newsService.getOne(id);

        model.addAttribute("news", publication);

        model.addAttribute("categorys", Category.values());
        return "editNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @PostMapping("/editNews/{id}")
    public String editNews(@PathVariable String id, @RequestParam String title,
            @RequestParam String category, @RequestParam(required = false) boolean subscriberContent, @RequestParam String body,
            @RequestParam(required = false) MultipartFile imageFile, ModelMap model, Principal principal) throws MyException {

        try {

            String writerEmail = principal.getName();
            System.out.println("subs controler " + subscriberContent);
            newsService.actualizeNews(id, title, body, imageFile, writerEmail, category, subscriberContent);

            return "redirect:/";

        } catch (Exception e) {

            System.out.println("Error al actualizar la noticia");

            return "createNews.html";
        }

    }

    @GetMapping("/{id}")
    public String showNews(@PathVariable String id, Model model, Principal principal) {

        Publication publication = newsService.getOne(id); // traigo la noticia

        if (publication.isSubscriberContent()) { // si subscriberContent es TRUE

            if (principal != null) { //Verifica si hay usuario logueado

                model.addAttribute("news", publication);

                return "news.html";

            } else {

                model.addAttribute("errorSubscriber", "Contenido exclusivo para suscriptores");

                return "redirect:/login?errorSubscriber";
            }

        } else {

            model.addAttribute("news", publication);

            return "news.html";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/deleteNews/{id}")
    public String deleteNews(@PathVariable String id) throws MyException {
        System.out.println("controlador " + id);
        newsService.deleteNews(id);
        return "redirect:/manage";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage")
    public String dashboardNews(Model model) {

        List<Publication> publicationList = newsService.newsList();

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/dateAsc")
    public String dashboardSortDateAsc(Model model) {

        List<Publication> publicationList = newsService.newsListAsc();

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/writerAZ")
    public String dashboardSortWriterAZ(Model model) {

        List<Publication> publicationList = newsService.newsListWriterAZ();

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/writerZA")
    public String dashboardSortWriterZA(Model model) {

        List<Publication> publicationList = newsService.newsListWriterZA();

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/{category}")
    public String dashboardCategory(@PathVariable String category, Model model) {

        List<Publication> publicationList = newsService.categoryList(category);

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/titleAZ")
    public String dashboardSortTitleAZ(Model model) {

        List<Publication> publicationList = newsService.newsListTitleAsc();

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_WRITER', 'ROLE_EDITOR')")
    @Transactional
    @GetMapping("/manage/titleZA")
    public String dashboardSortTitleZA(Model model) {

        List<Publication> publicationList = newsService.newsListTitleDesc();

        model.addAttribute("news", publicationList);

        model.addAttribute("categorys", Category.values());
        return "manageNews.html";
    }

}
*/
