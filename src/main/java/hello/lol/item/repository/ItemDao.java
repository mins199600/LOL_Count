package hello.lol.item.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemDao {
    //모든 아이템 리스트 출력문
    List<Map<String, Object>> findAllItemList(@Param("type") String type);
}