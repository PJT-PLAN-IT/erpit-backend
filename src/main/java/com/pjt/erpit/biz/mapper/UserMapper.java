package com.pjt.erpit.biz.mapper;

import com.pjt.erpit.biz.dto.Order.OrderDetailListDto;
import com.pjt.erpit.biz.dto.Order.OrderListDTO;
import com.pjt.erpit.biz.dto.user.UserListDto;
import com.pjt.erpit.biz.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 직원 관련 Mapper
 */
@SuppressWarnings("SpellCheckingInspection")
@Mapper
public interface UserMapper {

    List<UserListDto.Response> getUserList(String user);

    List<UserListDto.Response> getAllUser(String user);
}
