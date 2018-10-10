package com.yucong.log.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
import com.java.common.log.model.ServerExceptionLog;
import com.yucong.log.dto.ListServerExceptionLogDTO;
import com.yucong.log.vo.common.DataTableVO;


@Component
public class ServerExceptionLogDao extends BaseDao<ServerExceptionLog> {

private static final String COLLECTION_NAME = "serverExceptionLog";
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	protected MongoTemplate  getMongoTemplate() {
		return mongoTemplate;
	}
	
	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}

	
	 /**
     * 查询所有日志:模糊查询，分页，排序
     * 
     * @date 2018-03-19
     */
    public DataTableVO<ServerExceptionLog> listAll(ListServerExceptionLogDTO dto) {
    	
    	int currentPage = dto.getPage();
        int size = dto.getSize();
        int skip = (currentPage - 1) * size;
        
    	Query query = new Query();
     	
    	
    	if(!StringUtil.isEmpty(dto.getClientIP())) {
    		query.addCriteria(Criteria.where("clientIP").regex("^.*" + dto.getClientIP() + ".*$"));
    	}
    	if(!StringUtil.isEmpty(dto.getRequestUrl())) {
    		query.addCriteria(Criteria.where("requestUrl").regex("^.*" + dto.getRequestUrl() + ".*$"));
    	}
    	if(!StringUtil.isEmpty(dto.getDeviceType())) {
    		query.addCriteria(Criteria.where("deviceType").is(dto.getDeviceType()));
    	}
    	if(!StringUtil.isEmpty(dto.getUserId())) {
    		query.addCriteria(Criteria.where("userId").is(dto.getUserId()));
    	}
    	
    	int allCount = new Long(mongoTemplate.count(query, COLLECTION_NAME)).intValue();//获取所有的count
        int allPage = allCount % size == 0 ? allCount / size : allCount / size + 1;
       
        query.skip(skip);
     	query.limit(dto.getSize());
     	query.with(new Sort(new Order(Direction.DESC,"_id")));
        List<ServerExceptionLog> list = mongoTemplate.find(query, ServerExceptionLog.class,COLLECTION_NAME);
    	return new DataTableVO<ServerExceptionLog>(size, allCount, allPage, currentPage, list);
    }
    
}
