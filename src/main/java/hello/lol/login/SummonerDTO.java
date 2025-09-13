package hello.lol.login;

import lombok.*;

@Data
public class SummonerDTO {
    private String puuid;
    private String gameName;
    private String tagLine;

    // 소환사 상세 정보 필드 (소환사 API 응답용)
    private String id;
    private String accountId;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;
}
