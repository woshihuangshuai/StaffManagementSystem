package com.cmos.iservice;

import com.cmos.beans.Staff;

public interface IStaffSV {

    /**
     * 根据staff_id查找员工信息
     * @param staff_id
     * @return
     */
    Staff findStaffByStaffId(int staff_id);

    /**
     * 根据staff_id更新员工信息
     * @param staff
     * @return
     */
    int updateStaffByStaffId(Staff staff);

    /**
     * 根据staff_id删除员工信息
     * @param staff_id
     * @return
     */
    int deleteStaffByStaffId(int staff_id);

    /**
     * 插入员工信息
     * @param staff
     * @return
     */
    int insertStaff(Staff staff);

}
