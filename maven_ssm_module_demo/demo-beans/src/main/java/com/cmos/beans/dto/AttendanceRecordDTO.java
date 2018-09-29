package com.cmos.beans.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author HS
 */
public class AttendanceRecordDTO {

    /**
     * 考勤记录ID
     */
    private Integer recordId;

    /**
     * 员工ID
     */
    @NotNull(message = "员工Id不能为空")
    private Integer staffId;

    /**
     * 考勤时间
     */
    @NotBlank(message = "日期不能为空")
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
