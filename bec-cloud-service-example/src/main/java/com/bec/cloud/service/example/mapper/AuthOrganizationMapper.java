package com.bec.cloud.service.example.mapper;

import java.util.List;

import com.bec.cloud.service.example.model.AuthOrganization;

public interface AuthOrganizationMapper {
    int deleteByPrimaryKey(Long orgId);

    int insert(AuthOrganization record);

    int insertSelective(AuthOrganization record);

    AuthOrganization selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(AuthOrganization record);

    int updateByPrimaryKey(AuthOrganization record);

	List<AuthOrganization> selectAuthOrganization(String userName);
}