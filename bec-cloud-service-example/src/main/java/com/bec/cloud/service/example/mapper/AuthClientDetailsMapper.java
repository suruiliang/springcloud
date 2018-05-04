package com.bec.cloud.service.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bec.cloud.service.example.model.AuthClientDetails;

@Mapper
public interface AuthClientDetailsMapper {
    int deleteByPrimaryKey(String clientId);

    int insert(AuthClientDetails record);

    int insertSelective(AuthClientDetails record);

    AuthClientDetails selectByPrimaryKey(String clientId);

    int updateByPrimaryKeySelective(AuthClientDetails record);

    int updateByPrimaryKey(AuthClientDetails record);
}