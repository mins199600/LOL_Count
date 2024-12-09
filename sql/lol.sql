-- 1. 덱 테이블 생성
CREATE TABLE decks
(
    decks_id   INT AUTO_INCREMENT PRIMARY KEY,
    decks_name VARCHAR(50) UNIQUE NOT NULL,
    image_url  VARCHAR(255)  NULL -- 외부 이미지 링크 넣는 곳
);


-- 2. 챔피언 테이블 생성
CREATE TABLE champions_list
(
    champion_id   INT AUTO_INCREMENT PRIMARY KEY,
    champion_name VARCHAR(50) UNIQUE NOT NULL, -- 외부링크로 부터 받는 이미지 파일 이름
    image_url     VARCHAR(255) NOT NULL, -- 외부 이미지 링크 넣는 곳
    deck_id_1     INT,
    deck_id_2     INT,
    CONSTRAINT fk_deck1 FOREIGN KEY (deck_id_1) REFERENCES decks(decks_id),
    CONSTRAINT fk_deck2 FOREIGN KEY (deck_id_2) REFERENCES decks(decks_id)
);

-- 3. 덱-챔피언 관계 테이블 생성
CREATE TABLE deck_champions_mapping
(
    deck_id      INT NOT NULL,
    champion_id  INT NOT NULL,
    PRIMARY KEY (deck_id, champion_id),
    CONSTRAINT fk_deck FOREIGN KEY (deck_id) REFERENCES decks(decks_id) ON DELETE CASCADE,
    CONSTRAINT fk_champion FOREIGN KEY (champion_id) REFERENCES champions_list(champion_id) ON DELETE CASCADE
);
