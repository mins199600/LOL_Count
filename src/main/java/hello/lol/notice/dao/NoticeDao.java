package hello.lol.notice.dao;

import hello.lol.notice.vo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeDao {
    //조회
    List<Notice> NoticeList();
    //단일조회
    Notice findNoticeById(int noticeId);
    //글쓰기
    void setWriteNotice(Notice notice);
    //공지사항 수정하기
    void update(Notice notice);
    //전체삭제
    int deleteAllNotices(String dataId);
    //검색기능
    List<Notice> searchNotices(@Param("keyword") String keyword);

}