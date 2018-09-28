package com.cmos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cmos.beans.AttendanceRecordDO;
import com.cmos.dao.AttendanceRecordDAO;
import com.cmos.iservice.IAttendanceRecordSV;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class AttendanceRecordSVImpl implements IAttendanceRecordSV {


    @Autowired
    private AttendanceRecordDAO attendanceRecordDAO;
    /**
     * 插入员工打卡记录
     * @param attendanceRecordDO
     * @return
     */
    @Override
    public int insert(AttendanceRecordDO attendanceRecordDO) {
        return attendanceRecordDAO.insert(attendanceRecordDO);
    }

    /**
     * 根据Id查询员工打卡记录
     * @param recordId
     * @return
     */
    @Override
    public AttendanceRecordDO getAttendanceRecordByRecordId(Long recordId) {
        return attendanceRecordDAO.selectByPrimaryKey(recordId.intValue());
    }

    /**
     * 根据recordId删除打卡记录
     * @param recordId
     * @return
     */
    @Override
    public int deleteByRecordId(Integer recordId) {
        return attendanceRecordDAO.deleteByPrimaryKey(recordId);
    }

    /**
     * update
     * @param attendanceRecordDO
     * @return
     */
    @Override
    public int update(AttendanceRecordDO attendanceRecordDO) {
        return attendanceRecordDAO.updateByPrimaryKey(attendanceRecordDO);
    }

    /**
     * 根据staffId查询员工打卡记录
     *
     * @param staffId
     * @return
     */
    @Override
    public List<AttendanceRecordDO> getRecordByStaffId(Integer staffId) {
        return attendanceRecordDAO.getRecordByStaffId(staffId);
    }

    /**
     * 获取staff_info和t_attendance_record联合查询的结果
     *
     * @param staffId
     * @return
     */
    @Override
    public List<Map<String, String>> getRecordList(Integer staffId) {
        return attendanceRecordDAO.getRecordList(staffId);
    }
}
