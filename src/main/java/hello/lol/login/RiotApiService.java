package hello.lol.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiService {

    @Autowired
    private RestTemplate riotKrRestTemplate;

    @Autowired
    private RestTemplate riotAsiaRestTemplate;

    public SummonerDTO getSummonerByRiotId(String gameName, String tagLine) {
        try {
            // Riot API 엔드포인트 URL 구성
            String url = String.format("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s",
                    gameName, tagLine);

            // API 호출
            SummonerDTO accountInfo = riotAsiaRestTemplate.getForObject(url, SummonerDTO.class);

            if (accountInfo != null && accountInfo.getPuuid() != null) {
                // PUUID를 사용하여 추가 소환사 정보 조회
                String summonerUrl = String.format("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/%s",
                        accountInfo.getPuuid());

                SummonerDTO summonerInfo = riotKrRestTemplate.getForObject(summonerUrl, SummonerDTO.class);

                // 두 정보 병합
                if (summonerInfo != null) {
                    accountInfo.setId(summonerInfo.getId());
                    accountInfo.setAccountId(summonerInfo.getAccountId());
                    accountInfo.setName(summonerInfo.getName());
                    accountInfo.setProfileIconId(summonerInfo.getProfileIconId());
                    accountInfo.setRevisionDate(summonerInfo.getRevisionDate());
                    accountInfo.setSummonerLevel(summonerInfo.getSummonerLevel());
                }
            }

            return accountInfo;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }
}
