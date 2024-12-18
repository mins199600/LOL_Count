package hello.lol.notice.vo;

import lombok.Data;

import java.sql.Date;



@Data
public class Notice {
    private int id;
    private String title;
    private String author;
    private String contents;
    private int views;
    private Date createDate;
    private Date modifiedDate;

}
