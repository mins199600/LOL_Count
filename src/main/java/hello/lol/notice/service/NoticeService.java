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
}
