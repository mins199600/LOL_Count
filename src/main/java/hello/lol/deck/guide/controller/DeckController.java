package hello.lol.deck.guide.controller;

import hello.lol.deck.guide.repository.DeckDto;
import hello.lol.deck.guide.service.DeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeckController {

    private final DeckService deckService;

    @GetMapping("/api/decks/recommend")
    public List<DeckDto> deckGuide() {
        return deckService.deckGuide();
    }

}