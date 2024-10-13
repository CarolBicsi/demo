package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 用户 Mapper 接口
 * @author Y7993
 */
@Mapper // 保留 @Mapper 注解，确保 Mapper 被正确识别
public interface UserMapper {
    int insert(User user);
    int deleteById(Long id);
    int update(User user);
    User selectById(Long id);
    List<User> selectAll();

    void deleteAll();

    void resetAutoIncrement();
}
