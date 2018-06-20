package com.bec.cloud.service.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.bec.cloud.service.example.model.Auth2Role;

@Component("_authority2RoleMapper")
@Mapper
public interface Auth2RoleMapper {

	List<Auth2Role> selectAuth2Roles();
    
}