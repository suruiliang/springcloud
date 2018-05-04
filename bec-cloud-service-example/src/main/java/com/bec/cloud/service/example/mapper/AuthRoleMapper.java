package com.bec.cloud.service.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bec.cloud.service.example.model.AuthRole;

@Mapper
public interface AuthRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(AuthRole record);

    int insertSelective(AuthRole record);

    AuthRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(AuthRole record);

    int updateByPrimaryKey(AuthRole record);

	List<AuthRole> selectAuthRole(String username);
}