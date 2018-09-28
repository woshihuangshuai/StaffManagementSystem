package com.cmos.dao;

import com.cmos.beans.AttendanceRecordDO;

import java.util.List;
import java.util.Map;

public interface AttendanceRecordDAO {

    int deleteByPrimaryKey(Integer recordId);

    int insert(AttendanceRecordDO record);

    int insertSelective(AttendanceRecordDO record);

    AttendanceRecordDO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(AttendanceRecordDO record);

    int updateByPrimaryKey(AttendanceRecordDO record);

    List<AttendanceRecordDO> getRecordByStaffId(Integer staffId);

    /**
     * 获取staff_info和t_attendance_record联合查询的结果
     * @param staffId
     * @return
     */
    List<Map<String, String>> getRecordList(Integer staffId);
}