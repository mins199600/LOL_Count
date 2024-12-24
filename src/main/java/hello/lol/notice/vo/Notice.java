package hello.lol.notice.vo;

import lombok.Data;
import java.time.LocalDate;


@Data
public class Notice {
    private int id;
    private String title;
    private String author;
    private String contents;
    private int views;
    private LocalDate createDate;
    private LocalDate modifiedDate;
}
