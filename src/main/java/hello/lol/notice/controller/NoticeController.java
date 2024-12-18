package hello.lol.notice.controller;

import hello.lol.notice.service.NoticeService;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String getNoticeBoard(@RequestParam(value = "keyword", required = false) String keyword,
                                 Model model) {
        log.info("게시판 조회 시작 - 검색어: {}", keyword);

        List<Notice> noticeList;
        if (keyword != null && !keyword.isEmpty()) {
            // 검색어가 있는 경우
            noticeList = noticeService.searchNotices(keyword);
        } else {
            // 검색어가 없는 경우 전체 목록 조회
            noticeList = noticeService.noticeList();
        }
        model.addAttribute("noticeList", noticeList);
        return "/board/notice";
    }

    //단일조회
    @GetMapping("/noticeDetail")
    public String noticeOneList(Model model, @RequestParam("id") int noticeId) {
        log.info("게시판 단일조회 시작. 게시판 id: {}", noticeId);
        Notice notice = noticeService.getNotice(noticeId);
        model.addAttribute("notice", notice);
        return "/board/noticeDetail";
    }

    //글쓰는 화면으로 이동
    @GetMapping("/board/noticeWrite")
    public String noticeWrite(Model model){
        model.addAttribute("notice", new Notice());
        return "/board/noticeWrite";
    }

    //글 저장
    @PostMapping("/board/noticeWrite")
    public String saveNotice(@RequestParam("title")String title,
                             @RequestParam("author")String author,
                             @RequestParam("contents")String contents
                             ) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setAuthor(author);
        notice.setContents(contents);

        noticeService.writeNotice(notice);
        return"redirect:/notice";
    }

    //수정하기
    @GetMapping("/noticeUpdate/{id}")
    public String noticeUpdate(@PathVariable("id") int id, Model model) {
        log.info("수정할 게시판 내용: {}", model);
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

    // 전체삭제
    @PostMapping("/deleteAllNotices")
    public String deleteAllNotices() {
        boolean isDeleted = noticeService.deleteAllNotices(); // 서비스 호출
        if (isDeleted) {
            log.info("모든 공지사항 삭제 성공");
            return "redirect:/board/notice"; // 성공 시 목록 페이지로 리다이렉트
        } else {
            log.error("모든 공지사항 삭제 실패");
            return "redirect:/board/notice"; // 실패 시에도 목록 페이지로 리다이렉트
        }
    }
    // 단일삭제
    @PostMapping("/deleteNotice")
    public String deleteNotice(@RequestParam("id") int id) {
        boolean deleted = noticeService.deleteNotice(id); // 서비스 호출
        if (deleted) {
            log.info("공지사항 삭제 성공: ID={}", id);
        } else {
            log.error("공지사항 삭제 실패: ID={}", id);
        }
        return "redirect:/board/notice"; // 삭제 후 목록 페이지로 리다이렉트
    }
    // 다중 삭제 API 추가
    @PostMapping("/deleteSelectedNotices")
    public String deleteSelectedNotices(@RequestParam("ids") List<Integer> ids) {
        log.info("선택된 공지사항 삭제 요청: {}", ids);
        noticeService.deleteSelectedNotices(ids);
        return "redirect:/board/notice";
    }

}