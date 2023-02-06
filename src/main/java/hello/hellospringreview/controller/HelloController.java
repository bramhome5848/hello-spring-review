package hello.hellospringreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리
        // parsing -> resources:templates/ + {ViewName} + .html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name ="name", required = false) String name, Model model) {
        model.addAttribute("name", name);   // key, value
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  // http body 에 문자 내용을 직접 반환, ResponseBody 사용시 뷰 리졸버 사용하지 않음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // viewResolver 대신 HttpMessageConverter 가 동작

        // 기본 문자처리 : StringHttpMessageConverter
        // 기본 객체처리 : MappingJackson2HttpMessageConverter
        // byte 처리 등등 기타 여러 HttpMessageConverter 가 기본으로 등록되어 있음
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}