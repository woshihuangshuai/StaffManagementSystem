package com.cmos.dao;

import com.cmos.beans.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffDAO {

    Staff selectByPrimaryKey(Integer staff_id);

    int updateByPrimaryKey(Staff record);

    int deleteByPrimaryKey(Integer staff_id);

    int insert(Staff record);

    int updateByPrimaryKeySelective(Staff record);

    int insertSelective(Staff record);

    Staff selectByObject(Staff staff);

    /**
     * 根据列表查询员工信息
     * @param staffIdList
     * @return
     */
    List<Staff> selectByList(List<Integer> staffIdList);

    /**
     * 根据数组查询员工信息
     * @param staffIdArray
     * @return
     */
    List<Staff> selectByArray(@Param("staffIdArray") Integer[] staffIdArray);

}
