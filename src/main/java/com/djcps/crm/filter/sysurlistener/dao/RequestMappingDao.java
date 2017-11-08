package com.djcps.crm.filter.sysurlistener.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.djcps.crm.filter.sysurlistener.model.Furl;

@Repository
public interface RequestMappingDao {

	/**
	 * 插入数据
	 * @param list
	 */
	int insertFurl(List<Furl> list);

	int deleteFurl(List<String> list);

	List<Furl> selectAllFurl();

}
