package com.bec.cloud.service.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bec.cloud.service.example.model.Organization;

@Mapper
public interface OrganizationMapper {
    int deleteByPrimaryKey(Long orgId);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    List<Organization> selectOrganization(String custCode);
}