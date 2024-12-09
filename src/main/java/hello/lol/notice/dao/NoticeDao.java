package hello.lol.notice.dao;

import hello.lol.notice.vo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeDao {

    List<Notice> NoticeList();
}
