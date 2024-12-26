package hello.lol.notice.controller;

import hello.lol.notice.service.NoticeService;
import hello.lol.notice.vo.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    public String noticeOneList(@RequestParam("id") String noticeId, Model model) {
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
    public String noticeUpdate(@PathVariable("id") String id, @ModelAttribute Notice notice) {
        log.info("수정 요청 ID: {}", id);
        log.info("수정할 데이터: {}", notice);
        notice.setId(id);
        noticeService.updateNotice(notice);
        return "redirect:/board/notice";
    }

    // 삭제
    @PostMapping("/deleteNotice")
    public ResponseEntity<String> deletePostId(@RequestBody Map<String, Object> deletePostNum) {
        Object id = deletePostNum.get("id");
        try {
            if ("all".equals(id)) {
                // 전체 삭제
                noticeService.deleteAllNotices();
                log.info("전체 공지사항 삭제 완료");
            } else if (id instanceof List) {
                // 선택 삭제
                List<String> ids = (List<String>) id;
                noticeService.deleteNoticesByIds(ids);
                log.info("선택된 공지사항 삭제 완료: {}", ids);
            } else {
                log.warn("잘못된 요청 데이터: {}", id);
                return ResponseEntity.badRequest().body("잘못된 요청 데이터입니다.");
            }
            return ResponseEntity.ok("삭제가 완료되었습니다.");
        } catch (Exception e) {
            log.error("삭제 중 오류 발생:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류가 발생했습니다.");
        }
    }

}
