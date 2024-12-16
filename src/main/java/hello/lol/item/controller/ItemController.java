package hello.lol.item.controller;

import hello.lol.item.repository.ItemDto;
import hello.lol.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{type}")
    public List<ItemDto> getItemList(@PathVariable String type){
        return itemService.findAllItemList(type);
    }
}