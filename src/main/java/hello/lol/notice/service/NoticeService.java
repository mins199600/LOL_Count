package hello.lol.notice.service;

import hello.lol.notice.dao.NoticeDao;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;

    //전체조회
    public List<Notice> NoticeList() {
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
    //글쓰기
    public void writeNotice(Notice notice) {
        noticeDao.writeNotice(notice);
        log.info("글쓰기 성공");
    }

    // 공지사항 조회
    public Notice getNoticeById(int id) {
        return noticeDao.findById(id);
    }

    // 공지사항 수정
    public void updateNotice(Notice notice) {
        noticeDao.update(notice); // DAO 호출
        log.info("공지사항 수정 완료: {}", notice);
    }

/*    public void deleteNotice(int id) {
        noticeDao.delete(id);
    }*/
}

