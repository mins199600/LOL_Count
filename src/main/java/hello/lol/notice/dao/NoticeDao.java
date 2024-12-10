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
    //글쓰기
    void writeNotice(Notice notice);
    //공지사항 조회
    Notice findById(int id);
    //공지사항 저장
    void update(Notice notice);
}
