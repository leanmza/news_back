/*
package com.lean.news.controller;

import com.lean.news.model.entity.Publication;
import com.lean.news.model.entity.User;

import com.lean.news.service.NewsService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

*/
/**
 *
 * @author Lean
 *//*

@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    private NewsService newsService;

    @Transactional
    @GetMapping("/")
    public String index(Model model) {

        Publication latestPublication = newsService.newsList().get(0); //Tomo la noticia del primer lugar de la List

        model.addAttribute("latestNews", latestPublication);

        List<Publication> publicationList = newsService.newsList();

        publicationList.remove(0); //remuevo la noticia del primer lugar de la List

        model.addAttribute("news", publicationList);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String errorSubscriber, ModelMap model,
            @RequestParam(required = false) String success) throws UsernameNotFoundException {

        if ("registerSuccess".equals(success)) {
            model.put("registerSucces", "¡Gracias por registrarte en nuestra aplicación! "
                    + "Ahora puedes comenzar a utilizar nuestros servicios");
        }

        if (error != null) {

            model.put("error", "Usuario y/o Contraseña incorrecto, intente nuevamente");
        }

        if (errorSubscriber != null) {
            model.put("error", "Contenido exclusivo para suscriptores");
        }

        return "login.html";
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_READER', 'ROLE_WRITER')")
    @GetMapping("/home")
    public String home(HttpSession session, ModelMap model) {

        Publication latestPublication = newsService.newsList().get(0); //Tomo la noticia del primer lugar de la List

        model.addAttribute("latestNews", latestPublication);

        List<Publication> publicationList = newsService.newsList();

        publicationList.remove(0); //remuevo la noticia del primer lugar de la List

        model.addAttribute("news", publicationList);

        if ((session.getAttribute("userSession") != null)) {

            User logged = (User) session.getAttribute("userSession"); /// CustomUser ES LA CLASE PADRE

            model.put("userSession", logged);

        }

        return "index.html";
    }

    @Transactional
    @GetMapping("/category/{category}")
    public String category(@PathVariable String category, Model model) {

        List<Publication> publicationList = newsService.categoryList(category);

        model.addAttribute("news", publicationList);

        return "category.html";
    }

    @Transactional
    @GetMapping("/search")
    public String searchNewsByTitle(@RequestParam("word") String word, Model model) {

        List<Publication> publicationList = newsService.findNewsByTitle(word);

        model.addAttribute("news", publicationList);

        return "category.html";
    }

    @Transactional
    @GetMapping("/writer/{id}")
    public String filterByWriter(@PathVariable String id, Model model) {

        List<Publication> publicationList = newsService.findNewsByWriter(id);

        model.addAttribute("news", publicationList);

        return "category.html";
    }

}
*/
