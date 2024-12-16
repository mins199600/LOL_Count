package hello.lol.item.service;

import hello.lol.item.repository.ItemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDao itemDao;

    public List<Map<String, Object>> findAllItemList(String type) {
        return itemDao.findAllItemList(type);
    }
}