package hello.lol.notice.vo;

import lombok.Data;

@Data
public class Notice {
    private int id;
    private String title;
    private String author;
    private String contents;
    private int views;
    private String createDate;
    private String modifiedDate;
}
