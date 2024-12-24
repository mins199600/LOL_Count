package hello.lol.notice.controller;

import hello.lol.notice.service.NoticeService;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    //전체조회 + 검색기능
    @GetMapping("/board/notice")
    public String searchNotices(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        log.info("게시판 조회 시작 - 검색어: {}", keyword);
        List<Notice> notice;
        if (keyword == null || keyword.trim().isEmpty()) {
            notice = noticeService.noticeList(); // 전체 조회
        } else {
            notice = noticeService.searchNotices(keyword); // 키워드 검색
        }
        model.addAttribute("notices", notice != null ? notice : new ArrayList<>());
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "/board/notice";
    }

    //단일조회
    @GetMapping("/noticeDetail")
    public String noticeOneList(@RequestParam("id") int noticeId, Model model) {
        log.info("게시판 단일조회 시작. 게시판 id: {}", noticeId);
        Notice notice = noticeService.getNotice(noticeId);
        model.addAttribute("notice", notice);
        return "/board/noticeDetail";
    }

    //글쓰는 화면으로 이동
    @GetMapping("/board/noticeWrite")
    public String noticeWrite(Model model) {
        model.addAttribute("notice", new Notice());
        return "/board/noticeWrite";
    }

    //글 저장
    @PostMapping("/board/noticeWrite")
    public String saveNotice(@RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("contents") String contents
    ) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setAuthor(author);
        notice.setContents(contents);
        noticeService.setWriteNotice(notice);
        return "redirect:/board/notice";
    }


    //수정데이터 저장
    @PostMapping("/noticeUpdate/{id}")
    public String noticeUpdate(@PathVariable("id") int id, @ModelAttribute Notice notice) {
        log.info("수정 요청 ID: {}", id);
        log.info("수정할 데이터: {}", notice);

        notice.setId(id);
        noticeService.updateNotice(notice);

        return "redirect:/board/notice";
    }

    // 삭제
    @PostMapping("/deleteAllNotices")
    public String deleteAllNotices(@RequestBody String checkBoxData) {
        String data[] = checkBoxData.split(",");
        String dataId = String.valueOf(data.length);
        noticeService.deleteAllNotices(dataId);

        return "redirect:/board/notice";
    }


}
