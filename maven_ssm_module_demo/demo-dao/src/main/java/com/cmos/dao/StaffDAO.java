package com.cmos.dao;

import com.cmos.beans.Staff;

public interface StaffDAO {

    Staff selectByPrimaryKey(Integer staff_id);

    int updateByPrimaryKey(Staff record);

    int deleteByPrimaryKey(Integer staff_id);

    int insert(Staff record);

    int updateByPrimaryKeySelective(Staff record);

    int insertSelective(Staff record);

    Staff selectByObject(Staff staff);
}
