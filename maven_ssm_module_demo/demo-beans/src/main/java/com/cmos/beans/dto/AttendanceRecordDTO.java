package com.cmos.beans.dto;


public class AttendanceRecordDTO {

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
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
