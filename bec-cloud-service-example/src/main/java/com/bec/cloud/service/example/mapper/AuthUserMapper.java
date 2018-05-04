package com.bec.cloud.service.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bec.cloud.service.example.model.AuthUser;

@Mapper
public interface AuthUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(AuthUser record);

    int updateByPrimaryKey(AuthUser record);

	AuthUser selectAuthUserByUsername(String username);
}