-- 1. 덱 테이블 생성
CREATE TABLE decks
(
    decks_id   INT AUTO_INCREMENT PRIMARY KEY,
    decks_name VARCHAR(50) UNIQUE NOT NULL,
    image_url  VARCHAR(255)  NULL -- 외부 이미지 링크 넣는 곳
);

-- 2. 덱 설명 컬럼 추가
ALTER TABLE decks ADD COLUMN description TEXT;

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

-- 재료템
CREATE TABLE material_item(
    material_id INT AUTO_INCREMENT NOT NULL,
    material_name VARCHAR(50) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    CONSTRAINT pk_material PRIMARY KEY(material_id)
);

-- 재료템 한국이름
CREATE TABLE material_item_name_ko
(
    material_id      INT AUTO_INCREMENT NOT NULL,
    material_name_ko VARCHAR(50) NOT NULL,
    CONSTRAINT pk_material PRIMARY KEY (material_id)
);

-- 완성아이템
CREATE TABLE crafted_item
(
    crafted_id      INT AUTO_INCREMENT PRIMARY KEY,
    crafted_name    VARCHAR(50) NOT NULL,
    image_url       VARCHAR(255) NOT NULL
);

-- 완성아이템 한국이름
CREATE TABLE crafted_item_name_ko
(
    crafted_id_name_ko      INT AUTO_INCREMENT NOT NULL,
    crafted_name_ko VARCHAR(50) NOT NULL,
    CONSTRAINT pk_material PRIMARY KEY (crafted_id_name_ko)
);

-- 재료 + 완성아이템
CREATE TABLE crafted_material_mapping(
    crafted_id INT NOT NULL,
    material_id INT NOT NULL,
    crafted_id_name_ko INT NOT NULL,
        CONSTRAINT pk_material PRIMARY KEY(crafted_id, material_id, crafted_id_name_ko)
);

-- 재료템
CREATE TABLE material_item(
                              material_id INT AUTO_INCREMENT NOT NULL,
                              material_name VARCHAR(50) NOT NULL,
                              image_url VARCHAR(255) NOT NULL,
                              CONSTRAINT pk_material PRIMARY KEY(material_id)
);

-- 재료템 한국이름
CREATE TABLE material_item_name_ko
(
    material_id      INT AUTO_INCREMENT NOT NULL,
    material_name_ko VARCHAR(50) NOT NULL,
    CONSTRAINT pk_material PRIMARY KEY (material_id)
);

-- 완성아이템
CREATE TABLE crafted_item
(
    crafted_id      INT AUTO_INCREMENT PRIMARY KEY,
    crafted_name    VARCHAR(50) NOT NULL,
    image_url       VARCHAR(255) NOT NULL
);

-- 완성아이템 한국이름
CREATE TABLE crafted_item_name_ko
(
    crafted_id_name_ko      INT AUTO_INCREMENT NOT NULL,
    crafted_name_ko VARCHAR(50) NOT NULL,
    CONSTRAINT pk_material PRIMARY KEY (crafted_id_name_ko)
);

-- 재료 + 완성아이템
CREATE TABLE crafted_material_mapping (
                                          id INT AUTO_INCREMENT NOT NULL,
                                          crafted_id INT NOT NULL, -- crafted_item의 ID
                                          material_id_1 INT NOT NULL, -- 첫 번째 material_item의 ID
                                          material_id_2 INT NOT NULL, -- 두 번째 material_item의 ID
                                          PRIMARY KEY (id),
                                          CONSTRAINT fk_crafted FOREIGN KEY (crafted_id) REFERENCES crafted_item(crafted_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                          CONSTRAINT fk_material_1 FOREIGN KEY (material_id_1) REFERENCES material_item(material_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                          CONSTRAINT fk_material_2 FOREIGN KEY (material_id_2) REFERENCES material_item(material_id) ON DELETE CASCADE ON UPDATE CASCADE
);
