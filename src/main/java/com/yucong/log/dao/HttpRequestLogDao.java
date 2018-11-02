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
import com.java.common.log.model.HttpRequestLog;
import com.yucong.log.dto.ListHttpRequestLogDTO;
import com.yucong.log.vo.common.DataTableVO;

@Component
public class HttpRequestLogDao extends BaseDao<HttpRequestLog> {

private static final String COLLECTION_NAME = "httpRequestLog";
	
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
     * @date 2018-09-13
     */
    public DataTableVO<HttpRequestLog> listAll(ListHttpRequestLogDTO dto) {
    	
    	int currentPage = dto.getPage();
        int size = dto.getSize();
        int skip = (currentPage - 1) * size;
        
    	Query query = new Query();
    	
    	//过滤IP
    	if(StringUtil.isNotEmpty(dto.getClientIP())) {
    		query.addCriteria(Criteria.where("clientIP").regex("^.*" + dto.getClientIP() + ".*$"));
    	}
    	
    	//过滤URL
    	if(StringUtil.isNotEmpty(dto.getRequestUrl())) {
    		query.addCriteria(Criteria.where("requestUrl").regex("^.*" + dto.getRequestUrl() + ".*$"));
    	}
    	
    	//过滤请求方法
    	if(StringUtil.isNotEmpty(dto.getMethod())) {
    		query.addCriteria(Criteria.where("method").is(dto.getMethod()));
    	}
    	
    	//过滤平台
    	if(StringUtil.isNotEmpty(dto.getPlatform())) {
    		query.addCriteria(Criteria.where("platform").is(dto.getPlatform()));
    	} 
    	
    	//查询deviceType不为空的字段
    	if(StringUtil.isNotEmpty(dto.getDeviceType())) {
    		query.addCriteria(Criteria.where("deviceType").is(dto.getDeviceType()));
    	} else {
    		query.addCriteria(Criteria.where("deviceType").ne(null));
    	}
    	
    	//过滤用户ID
    	if(StringUtil.isNotEmpty(dto.getUserId())) {
    		query.addCriteria(Criteria.where("userId").is(dto.getUserId()));
    	}
    	
    	//开始时间
    	if(StringUtil.isNotEmpty(dto.getBeginTime())) {
    		query.addCriteria(Criteria.where("createTime").gt(dto.getBeginTime() + " 00:00:00"));
    	}
    	//结束时间
    	if(StringUtil.isNotEmpty(dto.getEndTime())) {
    		query.addCriteria(Criteria.where("userId").lt(dto.getEndTime() + " 23:59:59"));
    	}
    	
    	int code = dto.getCode();
    	if(code != 2) {
    		query.addCriteria(Criteria.where("responseData.code").is(dto.getCode()));
    	}
    	int allCount = new Long(mongoTemplate.count(query, COLLECTION_NAME)).intValue();//获取所有的count
    	
    	query.skip(skip);
     	query.limit(dto.getSize());
     	query.with(new Sort(new Order(Direction.DESC,"autoId")));
    	int allPage = allCount % size == 0 ? allCount / size : allCount / size + 1;
        List<HttpRequestLog> list = mongoTemplate.find(query, HttpRequestLog.class, COLLECTION_NAME);
    	return new DataTableVO<HttpRequestLog>(size, allCount, allPage, currentPage, list);
    }
 
    
}
