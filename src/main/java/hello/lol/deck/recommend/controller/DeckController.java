package hello.lol.deck.recommend.controller;

import hello.lol.deck.recommend.repository.DeckDto;
import hello.lol.deck.recommend.service.DeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeckController {

    private final DeckService deckService;

    //덱 가이드 출력
    @GetMapping("/api/decks/recommend")
    public List<DeckDto> deckGuide(@RequestParam String deckName) {
        log.info("덱 정보 : " + deckName);
        return deckService.deckGuide(deckName);
    }



}
