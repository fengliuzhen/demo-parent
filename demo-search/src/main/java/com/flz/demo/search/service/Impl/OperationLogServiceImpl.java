package com.flz.demo.search.service.Impl;

import com.flz.demo.search.dao.OperationDao;
import com.flz.demo.search.entity.OperationLog;
import com.flz.demo.search.service.OperationLogService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public Page<OperationLog> getList(String keyword) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("opercontent", keyword));
                //.must(QueryBuilders.multiMatchQuery(keyword, "opercontent", "mark"));
        Pageable pageable = new PageRequest(0, 10);
        Page<OperationLog> list = operationDao.search(queryBuilder, pageable);
        return list;
    }
}
