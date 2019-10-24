package cn.javafroum.mybatis.springboot2.mapper;

        import org.apache.ibatis.annotations.Select;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Map;

@Repository
public interface LogMapper {

    @Select("select * from logs")
    List<Map<String,Object>> selectLogList();

    List<Map<String,Object>> selectLogList1();
}
