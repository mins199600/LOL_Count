package hello.lol.member.controller;

import hello.lol.member.service.MemberService;
import hello.lol.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //[로그인으로 돌아가기] 클릭시 돌아가짐
    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> insertMember(@ModelAttribute MemberVo memberVo){
        try {
            // 서버 측 유효성 검사
            if (memberVo.getMemberId() == null || memberVo.getMemberId().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("소환사명을 입력해주세요.");
            }
            if (memberVo.getMemberPwd() == null || memberVo.getMemberPwd().length() < 8) {
                return ResponseEntity.badRequest().body("비밀번호는 8자 이상이어야 합니다.");
            }
            // 회원가입 처리
            boolean SignupSuccess = memberService.signup(memberVo);
            if (SignupSuccess) {
                return ResponseEntity.ok("회원가입 성공");
            } else {
                return ResponseEntity.badRequest().body("이미 존재하는 소환사명입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("회원가입 중 오류가 발생했습니다.");
        }
    }
}


