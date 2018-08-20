package com.cmos.sms.dao;

import com.cmos.sms.beans.Staff;

public interface StaffDAO {

    Staff selectByPrimaryKey(Integer staff_id);

    int updateByPrimaryKey(Staff record);

    int deleteByPrimaryKey(Integer staff_id);

    int insert(Staff record);

    int updateByPrimaryKeySelective(Staff record);

    int insertSelective(Staff record);

}