package hello.lol.notice.controller;

import hello.lol.notice.service.NoticeService;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
    private final NoticeService noticeService;

    //조회
    @GetMapping("/board/notice")
    public String getNoticeBoard(Model model){
        List<Notice> noticeList = noticeService.NoticeList();
        log.info("test");
        model.addAttribute("noticeList", noticeList);
        return "/board/notice";
    }
}


