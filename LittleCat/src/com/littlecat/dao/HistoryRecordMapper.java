package com.littlecat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.littlecat.entity.HistoryRecord;

@Repository("hrDao")
public interface HistoryRecordMapper {
	int deleteByPrimaryKey(String hId);

	int insert(HistoryRecord record);

	HistoryRecord selectByPrimaryKey(String hId);

	int updateByPrimaryKeySelective(HistoryRecord record);//选择性更新

	int updateByPrimaryKey(HistoryRecord record);//根据主键更新
	
	int insertSelective(HistoryRecord record);// 选择性插入

	int deleteByuid(String hId);//根据用户id删除

	HistoryRecord findHRByPidUid(String pid, String uid);//根据商品id，用户id查询历史记录

	List<HistoryRecord> findByUid(String uid, int offset, int rows);//根据用户id查询历史记录

	int hrCount(String uid);//查询历史记录时计算总数
}