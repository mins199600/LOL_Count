package hello.lol.member.service;

import hello.lol.member.dao.MemberDao;
import hello.lol.member.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    @Transactional
    public boolean signup(MemberVo memberVo) throws Exception {

        // 아이디 중복 체크
        int memberIdCheck = memberDao.MemberIdCheck(memberVo.getMember_id());
        if (memberIdCheck > 0) {
            return false; // 중복된 아이디 존재
        }

        // 회원가입 처리
        memberDao.insertMember(memberVo);
        return true;
    }
}
