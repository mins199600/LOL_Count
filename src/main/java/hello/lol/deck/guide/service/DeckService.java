package hello.lol.deck.guide.service;

import hello.lol.deck.guide.repository.DeckDao;
import hello.lol.deck.guide.repository.DeckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final DeckDao deckDao;

    public List<DeckDto> deckGuide() {
        return deckDao.deckGuide();
    }
}
