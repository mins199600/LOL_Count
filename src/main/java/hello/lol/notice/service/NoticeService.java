package hello.lol.notice.service;

import hello.lol.notice.dao.NoticeDao;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;

    //전체조회
    public List<Notice> noticeList() {
        List<Notice> findAll = noticeDao.NoticeList();
        log.info("조회된 공지사항 데이터: {}", findAll); // 로그 추가
        return findAll;
    }

    //단일조회
    public Notice getNotice(String noticeId) {
        Notice notice = noticeDao.findNoticeById(noticeId);
        log.info("조회된 공지사항 단일조회 데이터 : {}", notice);
        return notice;
    }
    //검색기능
    public List<Notice> searchNotices(String keyword) {
        List<Notice> results = noticeDao.searchNotices(keyword);
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }
    //글쓰기
    public void setWriteNotice(Notice notice) {
        noticeDao.setWriteNotice(notice);
        log.info("글쓰기 성공");
    }

    // 공지사항 수정
    public void updateNotice(Notice notice) {
        noticeDao.update(notice); // DAO 호출
        log.info("공지사항 수정 완료: {}", notice);
    }

    //공지사항 전체 삭제
    public void deleteAllNotices() {
        noticeDao.deleteAll();
    }

    //공지사항 개별 삭제
    public void deleteNoticesByIds(List<String> ids) {
        noticeDao.deleteByIds(ids);
    }

}