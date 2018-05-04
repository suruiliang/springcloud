package com.bec.cloud.service.example.mapper;

import com.bec.cloud.service.example.model.AuthLog;

public interface AuthLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(AuthLog record);

    int insertSelective(AuthLog record);

    AuthLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(AuthLog record);

    int updateByPrimaryKey(AuthLog record);
}