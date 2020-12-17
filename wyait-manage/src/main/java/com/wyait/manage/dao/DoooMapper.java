package com.wyait.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wyait.manage.pojo.DataDooo;
import com.wyait.manage.pojo.Dooo;
import com.wyait.manage.pojo.ProgramWindow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author h_baojian
 * @version 1.0
 * @date 2020/6/23 14:06
 */
public interface DoooMapper extends BaseMapper<Dooo> {
    List<Dooo> getDooos(@Param("dooo")Dooo dooo,@Param("approvalType") Integer approvalType);

    Dooo getDoooByName(@Param("doooName")String doooName);

    int updateDooo(Dooo dooo);

    int insertDooo(Dooo dooo);

    Dooo getDoooByNameAndId(@Param("doooName")String doooName,@Param("doooId")Integer doooId);

    int delDooo(Integer doooId);

    List<Dooo> getDoooList(Integer departmentId);

    Dooo getDoooById(Integer doooId);

    int addDoooAndData(@Param("dataId")Integer dataId, @Param("doooId")Integer doooId);

    DataDooo getDataDooo(@Param("dataId")Integer dataId, @Param("doooId")Integer doooId);

    List<Dooo> fetchByName(Dooo dooo);

    int putApprovalType(@Param("doooId") Integer doooId,@Param("approvalType") Integer approvalType,@Param("approvalText") String approvalText);

    int sendBackApprovalType(@Param("doooId")Integer doooId,@Param("approvalType") Integer approvalType);

    int passApprovalType(@Param("doooId") Integer doooId,@Param("approvalType") Integer approvalType,@Param("approvalText") String approvalText);

    List<Dooo> APIDoooList(@Param("page") Integer page,@Param("pageSize") Integer size,@Param("keyWord") String keyWord,
                           @Param("type")String type,@Param("departmentId")Integer departmentId);

    Integer APIDoooListCount(@Param("keyWord")String keyWord, @Param("type")String type,@Param("departmentId")Integer departmentId);

    int getDoooCount();

    List<Dooo> getHotDooo(Integer number);

    List<ProgramWindow> getProgramWindowList(String doooId);

    int updateProgramWindow(@Param("programWindow") ProgramWindow programWindow);

    int insertProgramWindow(@Param("programWindow") ProgramWindow programWindow);
}
