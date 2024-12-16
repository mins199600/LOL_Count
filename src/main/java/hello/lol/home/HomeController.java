package hello.lol.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "/login";
    }

    @PostMapping("/main")
    public String main(){
        return "/main";
    }

    @GetMapping("/items/items")
    public String items(){
        return "/items/items";
    }
}
