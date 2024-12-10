package hello.lol.notice.vo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Notice {
    private int id;
    private String title;
    private String author;
    private String contents;
    private int views;
    private LocalDate createDate;
    private LocalDateTime modifiedDate;

}
