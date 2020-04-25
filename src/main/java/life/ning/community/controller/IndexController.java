package life.ning.community.controller;


import life.ning.community.dto.PaginationDTO;
import life.ning.community.dto.QuestionDTO;
import life.ning.community.mapper.QuestionMapper;
import life.ning.community.mapper.UserMapper;
import life.ning.community.model.Question;
import life.ning.community.model.User;
import life.ning.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size){
        PaginationDTO pagination= questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";

    }
}
