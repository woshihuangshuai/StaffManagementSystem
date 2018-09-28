package com.cmos.iservice;

import com.cmos.beans.AttendanceRecordDO;

import java.util.List;
import java.util.Map;

/**
 * @author HS
 */
public interface IAttendanceRecordSV {

    /**
     * 插入员工打卡记录
     * @param attendanceRecordDO
     * @return
     */
    int insert(AttendanceRecordDO attendanceRecordDO);

    /**
     * 根据Id查询员工打卡记录
     * @param recordId
     * @return
     */
    AttendanceRecordDO getAttendanceRecordByRecordId(Long recordId);

    /**
     * 根据recordId删除打卡记录
     * @param recordId
     * @return
     */
    int deleteByRecordId(Integer recordId);

    /**
     * update
     * @param attendanceRecordDO
     * @return
     */
    int update(AttendanceRecordDO attendanceRecordDO);

    /**
     * 根据staffId查询员工打卡记录
     * @param staffId
     * @return
     */
    List<AttendanceRecordDO> getRecordByStaffId(Integer staffId);

    /**
     * 获取staff_info和t_attendance_record联合查询的结果
     * @param staffId
     * @return
     */
    List<Map<String, String>> getRecordList(Integer staffId);
}
