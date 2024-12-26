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
    Notice findNoticeById(String noticeId);
    //글쓰기
    void setWriteNotice(Notice notice);
    //공지사항 수정하기
    void update(Notice notice);
    //전체삭제
    void deleteAll();
    //개별삭제
    void deleteByIds(@Param("ids") List<String> ids);
    //검색기능
    List<Notice> searchNotices(@Param("keyword") String keyword);
}