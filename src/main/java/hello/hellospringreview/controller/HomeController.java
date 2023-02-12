package hello.hellospringreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")    //정적 리소스(index.html)는 무시됨 우선순위가 controller 가 더 높기 떄문에
    public String home() {
        return "home";
    }
}
