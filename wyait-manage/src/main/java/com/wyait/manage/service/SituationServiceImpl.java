package com.wyait.manage.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyait.manage.dao.SituationDetailsMapper;
import com.wyait.manage.dao.SituationMapper;
import com.wyait.manage.entity.SituationVO;
import com.wyait.manage.pojo.Situation;
import com.wyait.manage.pojo.SituationDetails;
import com.wyait.manage.service.db1.SituationService;
import com.wyait.manage.utils.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/28 16:01
 */
@Service
public class SituationServiceImpl extends ServiceImpl<SituationMapper, Situation> implements SituationService {
    @Autowired
    private SituationMapper situationMapper;
    @Autowired
    private SituationDetailsMapper situationDetailsMapper;

    @Override
    public PageDataResult getSituationList(Situation situation, Integer page, Integer limit) {
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<SituationVO> situationList = situationMapper.getSituationList(situation);
        // 获取分页查询后的数据
        PageInfo<SituationVO> pageInfo = new PageInfo<>(situationList);
        // 设置获取到的总记录数total：
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        for (int i=0;i<situationList.size();i++){
            List<SituationDetails> situationDetails = situationDetailsMapper.getSituationDetailsBySituationId(situationList.get(i).getSituationId());
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0;j<situationDetails.size();j++){
                stringBuffer.append(situationDetails.get(j).getDetailsDescribe());
                if (j!=situationDetails.size() || j>situationDetails.size()){
                    stringBuffer.append("/");
                }
            }
            situationList.get(i).setSituationDetailsDescribe(stringBuffer.toString());
        }
        pdr.setList(situationList);
        return pdr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class })
    public String setSituation(Situation situation) {
        if (situation.getSituationId() != null){
            //判断是否存在相同名称
            Situation situation1 = situationMapper.getSituationByNameAndId(situation.getSituationDescribe(),situation.getSituationId());
            if (null != situation1){
                return "该情形已经存在";
            }
            //修改
            situation.setUpdateTime(new Date());
            situationMapper.updateSituation(situation);

        }else{
            //判断是否存在相同名称
            Situation situation1 = situationMapper.getSituationByName(situation.getSituationDescribe(),situation.getPid());
            if (null != situation1){
                return "该情形已经存在";
            }
            situation.setNewTime(new Date());
            situation.setUpdateTime(new Date());
            situationMapper.insert(situation);
        }
        return "ok";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
            RuntimeException.class, Exception.class })
    public String delSituation(Integer situationId) {
        //查询所有以这个情形为父ID的子情形
        List<Situation> situations =  situationMapper.selectPidBySituationId(situationId);
        for (int i = 0; i< situations.size(); i++){
            //如果是最终情形就将子情形删除
            if (situations.get(i).getNodesType() == 0){
                //删除子情形
                situationDetailsMapper.delSituationDetail(situations.get(i).getSituationId());
                //删除材料管理节点
                situationMapper.delDataAndSituation(situations.get(i).getSituationId());
            }
        }
        //最后删除本身
        situationMapper.delSituation(situationId);
        return "ok";
    }

    @Override
    public Situation selectById(Integer situationId) {
        return situationMapper.getSituationId(situationId);
    }

    @Override
    public List<SituationDetails> selectSituationDetailsBySituationId(Integer situationId) {
        return situationDetailsMapper.getSituationDetailsBySituationId(situationId);
    }

    @Override
    public List<Situation> selectPidBySituationId(Integer situationId) {
        return situationMapper.selectPidBySituationId(situationId);
    }

    @Override
    @Transactional
    public String setSituationDetails(SituationDetails situationDetails) {
        if (situationDetails.getSituationDetailsId() != null){
            //判断是否存在相同名称
            List<SituationDetails> situationDetails1 = situationDetailsMapper.getsituationDetailsByNameAndId(situationDetails.getDetailsDescribe(),situationDetails.getSituationDetailsId(),situationDetails.getSituationId());
            if (situationDetails1.size() != 0){
                return "情形内已存在该选项";
            }
            //修改
            situationDetails.setUpdateTime(new Date());
            situationDetailsMapper.updateSituationDetails(situationDetails);
        }else{
            //判断是否存在相同名称
            List<SituationDetails> situationDetails1 = situationDetailsMapper.getsituationDetailsByNameAndId2(situationDetails.getDetailsDescribe(),situationDetails.getSituationId());
            if (situationDetails1.size() != 0){
                return "情形内已存在该选项";
            }
            situationDetails.setNewTime(new Date());
            situationDetails.setUpdateTime(new Date());
            situationDetailsMapper.insert(situationDetails);
        }
        return "ok";
    }

    @Override
    @Transactional
    public String delSituationDetails(Integer situationDetailsId) {
        //删除本身
        situationDetailsMapper.delSituationDetail(situationDetailsId);
        //删除关联表
        situationDetailsMapper.delSituationDetailAndData(situationDetailsId);
        //删除关联的情形
        situationDetailsMapper.delSituationByPid(situationDetailsId);
        return "ok";
    }

    @Override
    public List<SituationDetails> findByDepartmentId(Integer departmentId) {
        return situationMapper.findByDepartmentId(departmentId);
    }

    @Override
    public List<Situation> findBySituationId(Integer departmentId) {
        return situationMapper.findBySituationId(departmentId);
    }

    @Override
    public List<Map> findByDoooSituation(Integer did, String sdis) {
        return situationMapper.findByDoooSituation(did, sdis);
    }

    @Override
    public String setSituationTOW(Integer pid, Integer type, String situationDescribe,Integer situationId,Integer situationIdTOW) {
        if (situationIdTOW == null){
            //查询是否重名
            List<Situation> s = situationMapper.getSituationPid(situationId,situationDescribe);
            if (s.size() != 0){
                return "已存在同名称的情形";
            }
            Situation situation = new Situation();
            Situation situation1 = situationMapper.getSituationId(situationId);
            situation.setDepartmentId(situation1.getDepartmentId());
            situation.setDoooId(situation1.getDoooId());
            situation.setPid(pid);
            situation.setType(type);
            situation.setSituationDescribe(situationDescribe);
            situation.setNewTime(new Date());
            situationMapper.insert(situation);
        }else{
            List<Situation> s = situationMapper.getSituationPidTow(situationId,situationDescribe,situationIdTOW);
            if (s.size() != 0){
                return "已存在同名称的情形";
            }
            Situation situation = new Situation();
            situation.setUpdateTime(new Date());
            situation.setSituationDescribe(situationDescribe);
            situation.setSituationId(situationIdTOW);
            situation.setType(type);
            situationMapper.updateSituation(situation);
        }

        return "ok";
    }

    @Override
    public List<Situation> findSituationByPid(Integer situationDetailsId) {
        return situationMapper.findSituationByPid(situationDetailsId);
    }

    @Override
    public String delSituationByPidAndSituationId(Integer situationId, Integer pid) {
        situationMapper.delSituationByPidAndSituationId(situationId,pid);
        return "ok";
    }

    @Override
    public List<Situation> findchildSituation(Integer pid) {
        return situationMapper.findchildSituation(pid);
    }
}
