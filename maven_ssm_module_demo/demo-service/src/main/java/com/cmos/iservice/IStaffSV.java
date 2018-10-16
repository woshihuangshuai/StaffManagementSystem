package com.cmos.iservice;

import com.cmos.beans.Staff;

import java.util.List;

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
    Staff updateStaffByStaffId(Staff staff);

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
    Staff insertStaff(Staff staff);

    /**
     * 根据多个属性查询员工信息
     * @param staff
     * @return
     */
    Staff selectByObject(Staff staff);

    /**
     * 根据列表查询员工信息
     * @param staffIdList
     * @return
     */
    List<Staff> selectByList(List<Integer> staffIdList);

    /**
     * 根据员工id数组查询员工信息
     * @param staffIdArray
     * @return
     */
    List<Staff> selectByArray(Integer[] staffIdArray);

    /**
     * 批量插入员工信息，返回影响的数据库行数
     * @param staffList
     * @return
     */
    int insertByList(List<Staff> staffList);

    Staff selectStaffWithAttendanceRecordByStaffId(Staff staff);
}
