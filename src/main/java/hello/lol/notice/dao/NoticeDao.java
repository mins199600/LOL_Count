package hello.lol.notice.dao;

import hello.lol.notice.vo.Notice;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface NoticeDao {
    //조회
    List<Notice> NoticeList();
    //단일조회
    Notice findNoticeById(int noticeId);
}
