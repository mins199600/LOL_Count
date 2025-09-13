package hello.lol.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private RiotApiService riotApiService;

    // 루트 경로에 대한 GET 요청 처리
    @GetMapping("/")
    public String home() {
        return "login/main";  // main.html 템플릿을 반환
    }

    // 로그인 페이지 요청 처리
    @GetMapping("/login")
    public String loginPage() {
        return "login/main";
    }

    // 루트 경로에 대한 POST 요청 처리
    @PostMapping("/")
    public String handleRootPost(
            @RequestParam String gameName,
            @RequestParam String tagLine,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        log.info("Root POST request received for gameName: {}, tagLine: {}", gameName, tagLine);
        return processLogin(gameName, tagLine, session, redirectAttributes);
    }

    // 로그인 요청 처리
    @PostMapping("/login")
    public String login(
            @RequestParam String gameName,
            @RequestParam String tagLine,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        log.info("Login POST request received for gameName: {}, tagLine: {}", gameName, tagLine);
        return processLogin(gameName, tagLine, session, redirectAttributes);
    }

    // 로그인 처리 로직을 분리하여 중복 코드 제거
    private String processLogin(String gameName, String tagLine, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // 태그라인에서 '#' 문자 제거
            if (tagLine.startsWith("#")) {
                tagLine = tagLine.substring(1);
            }

            SummonerDTO summonerDTO = riotApiService.getSummonerByRiotId(gameName, tagLine);
            if (summonerDTO != null) {
                session.setAttribute("summonerDTO", summonerDTO);
                return "redirect:/main";  // 성공 시 /main으로 리다이렉트
            } else {
                log.warn("Login failed: Account not found");
                redirectAttributes.addFlashAttribute("errorMessage", "계정을 찾을 수 없습니다. 게임 이름과 태그라인을 확인해주세요.");
                return "redirect:/";  // 실패 시 루트 경로로 리다이렉트
            }
        } catch (Exception e) {
            log.error("Login error", e);
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 처리 중 오류가 발생했습니다.");
            return "redirect:/";
        }
    }

    // 메인 페이지 요청 처리
    @GetMapping("/main")
    public String mainPage() {
        return "login/login_success";
    }

    // API 엔드포인트가 필요한 경우 @ResponseBody 사용
    @GetMapping("/api/summoner")
    @ResponseBody
    public SummonerDTO getSummonerInfo(HttpSession session) {
        return (SummonerDTO) session.getAttribute("summonerDTO");
    }
}
