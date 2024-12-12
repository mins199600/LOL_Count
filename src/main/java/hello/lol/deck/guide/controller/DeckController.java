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

    //애기야 이 메소드에서 하려는게 뭐야? 기능이 메인 화면에서 추천덱 보면 거기에다가 추천 덱 데이터 뿌려주고 싶어서...지금
    //이것저것 시도하다가 메인페이지 아예 망가뜨려서 너무 속상해
    //어이궁...ㅠㅠ 특정덱 추천이 아니라 모든 덱 추천이야?
    //웅웅 약간 반군 일라오이 투사 바이올렛 이런것처럼 이것저것 시너즈 맞춰서 화면 보여주는거 하고 싶어서
    // 그래서 로그인 하는 사람마다 추천덱 다 다르게 보여주고 승률도 보여주고 싶어서
    //데이터 넘기는거니까 restapi 쓴건데...내가 너무 역부족인가봐..
    // 너무 어렵다... 모든 사람이 다 똑같은 추천덱 보는게 좋을거같은데. op.gg도 다 똑같은거 보여주자나
    // 그래서 덱이 겹치면 한명이 아예 죽어버리니깐 다양한거 하려구 했는데...지금 깃도 꼬여서 이전거 다운받아도 안되네...pull 로 땡겼는데도 파일자체가 다 망가졌나바ㅗ
    //내 메인페이지...날라가떠...내가 처음으로 설계하고 만든건데..나름...ㅎ흐규

    // 애기야 어차피 다시 만들어야 할건데 여러번 만들어 보면 실력 더 늘고 좋지! 럭키비키니로 생각해보자구
    //그리고 처음만드는거니까 최대한 쉽게 만들고 다 만든다음에 좀 더 기능 추가하는 식으로 하자 그게 더 편해
    // 처음부터 복잡하게 하려면 어려워
    //덱 가이드 출력
    @GetMapping("/api/decks/recommend")
    public List<DeckDto> deckGuide() {
        return deckService.deckGuide();
    }

}