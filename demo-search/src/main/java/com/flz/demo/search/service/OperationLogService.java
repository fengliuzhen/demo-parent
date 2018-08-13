package com.flz.demo.search.service;

import com.flz.demo.search.entity.OperationLog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OperationLogService {
    Page<OperationLog> getList(String keyword);
}
