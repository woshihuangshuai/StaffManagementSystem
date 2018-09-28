package com.cmos.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * t_attendance_record
 *
 * @author
 */
public class AttendanceRecordDO implements Serializable {

    private static final long serialVersionUID = -1048279398974224476L;
    /**
     * 考勤记录ID
     */
    private Integer recordId;

    /**
     * 员工ID
     */
    private Integer staffId;

    /**
     * 考勤时间
     */
    private Date date;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}