package hello.lol.deck.guide.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeckDao {
    List<DeckDto> deckGuide();
}