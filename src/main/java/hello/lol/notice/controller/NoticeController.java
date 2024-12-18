package hello.lol.notice.controller;

import hello.lol.notice.service.NoticeService;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    //조회
    @GetMapping("/board/notice")
    public String getNoticeBoard(Model model) {
        List<Notice> noticeList = noticeService.NoticeList();
        log.info("test");
        model.addAttribute("noticeList", noticeList);
        return "/board/notice";
    }

    //단일조회
    @GetMapping("/noticeDetail")
    public String noticeOneList(Model model, @RequestParam("id") int noticeId) {
        Notice notice = noticeService.getNotice(noticeId);
        model.addAttribute("notice", notice);
        return "/board/noticeDetail";
    }

    //글쓰기
    @GetMapping("/board/noticeWrite")
    public String noticeWrite(Model model){
        model.addAttribute("notice", new Notice());
        return "/board/noticeWrite";
    }
    //글 저장
    @PostMapping("/notices")
    public String noticeWrite(@ModelAttribute Notice notice) {
        noticeService.writeNotice(notice);
        return "redirect:/board/notice";
    }

    //수정하기
    @GetMapping("/noticeUpdate/{id}")
    public String noticeUpdate(@PathVariable("id") int id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "/board/noticeUpdate";
    }
    //수정데이터 저장
    @PostMapping("/noticeUpdate/{id}")
    public String noticeUpdate(@PathVariable("id") int id, @ModelAttribute Notice notice) {
        notice.setId(id);
        noticeService.updateNotice(notice);
        return "redirect:/board/notice";
    }
    //삭제하기
/*    @GetMapping("/noticeDelete/{id}")
    public String noticeDelete(@PathVariable("id") int id) {
        noticeService.deleteNotice(id);
        return "redirect:/board/notice";
    }*/
}



