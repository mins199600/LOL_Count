package hello.lol.member.dao;

import hello.lol.member.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    //회원가입 처리
    void insertMember(MemberVo memberVo);
    //아이디 중복 체크
    int MemberIdCheck(String memberId);
}
