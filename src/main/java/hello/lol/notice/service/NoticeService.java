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

    public List<Notice> NoticeList() {
        List<Notice> findAll = noticeDao.NoticeList();
        log.info("조회된 공지사항 데이터: {}", findAll); // 로그 추가
        return findAll;
    }
}
