package hello.lol.deck.recommend.service;

import hello.lol.deck.recommend.repository.DeckDao;
import hello.lol.deck.recommend.repository.DeckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {
    private final DeckDao deckDao;

    public List<DeckDto> deckGuide(String deckName) {
        return deckDao.deckGuide(deckName);
    }
}
