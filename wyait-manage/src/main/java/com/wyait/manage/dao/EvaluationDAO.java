package com.wyait.manage.dao;

import com.wyait.manage.pojo.Evaluation;
import org.springframework.stereotype.Repository;

/**
 * EvaluationDAO继承基类
 */
@Repository
public interface EvaluationDAO extends MyBatisBaseDao<Evaluation, String> {
}