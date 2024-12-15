package hello.lol.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    //아이템 창
    @GetMapping("/items/items")
    public String items(){
        return "/items/items";
    }

    //신규 아이템
    @GetMapping("/items/newItems")
    public String newItems(){
        return "/items/newItems";
    }

    //베스트 아이템
    @GetMapping("/items/bestItem")
    public String bestItem(){
        return "/items/bestItem";
    }

    //카운터 아이템
    @GetMapping("/items/counterItem")
    public String counterItem(){
        return "/items/counterItem";
    }

}
