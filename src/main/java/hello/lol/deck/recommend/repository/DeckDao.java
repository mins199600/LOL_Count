package hello.lol.deck.recommend.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeckDao {
    List<DeckDto> deckGuide(@Param("deckName") String deckName);
}
