package hello.lol.notice.service;

import hello.lol.notice.dao.NoticeDao;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Notice getNotice(int noticeId) {
        Notice notice = noticeDao.findNoticeById(noticeId);
        log.info("조회된 공지사항 단일조회 데이터 : {}", notice);
        return notice;
    }
    //검색기능
    public List<Notice> searchNotices(String keyword) {
        List<Notice> results = noticeDao.searchNotices(keyword);
        if (results == null) {
            results = new ArrayList<>(); // 비어 있는 리스트 반환
        }
        return results;
    }
    //글쓰기
    public void writeNotice(Notice notice) {
        noticeDao.writeNotice(notice);
        log.info("글쓰기 성공");
    }

    // 공지사항 수정
    public void updateNotice(Notice notice) {
        noticeDao.update(notice); // DAO 호출
        log.info("공지사항 수정 완료: {}", notice);
    }
    //전체 삭제
    public boolean deleteAllNotices() {
        int result = noticeDao.deleteAllNotices(); // DAO 호출
        if (result > 0) {
            log.info("모든 공지사항 삭제 완료");
            return true;
        } else {
            log.error("모든 공지사항 삭제 실패");
            return false;
        }
    }

    //단일 삭제
    public boolean deleteNotice(int id) {
        int result = noticeDao.deleteNotice(id); // DAO 호출
        if (result > 0) {
            log.info("공지사항 삭제 완료: ID={}", id);
            return true;
        } else {
            log.error("공지사항 삭제 실패: ID={}", id);
            return false;
        }
    }

    // 다중 삭제 로직 추가
    public void deleteSelectedNotices(List<Integer> ids) {
        int deletedCount = noticeDao.deleteSelectedNotices(ids);
        log.info("삭제된 공지사항 수: {}", deletedCount);
    }


}