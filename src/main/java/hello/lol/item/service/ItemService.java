package hello.lol.item.service;

import hello.lol.item.repository.ItemDao;
import hello.lol.item.repository.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDao itemDao;

    public List<ItemDto> findAllItemList(String type) {
        return itemDao.findAllItemList(type);
    }
}