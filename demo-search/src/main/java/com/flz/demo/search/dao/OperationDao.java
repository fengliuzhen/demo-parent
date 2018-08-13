package com.flz.demo.search.dao;

import com.flz.demo.search.entity.OperationLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OperationDao extends ElasticsearchRepository<OperationLog,Integer> {
}
