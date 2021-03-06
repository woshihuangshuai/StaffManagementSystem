package com.cmos.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HS
 */
public class Staff implements Serializable {

    private static final long serialVersionUID = 4425822318009583790L;

    public Staff() {

    }

    public Staff(java.lang.Long staff_id, java.lang.String staff_name, java.util.Date staff_date_of_birth, java.lang.String staff_department, java.lang.String staff_post, java.lang.String staff_level) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.staff_date_of_birth = staff_date_of_birth;
        this.staff_department = staff_department;
        this.staff_post = staff_post;
        this.staff_level = staff_level;
        this.attendanceRecordDO = null;
        this.attendanceRecordDOList = new ArrayList<>();
    }

    private Long staff_id;

    private String staff_name;

    private java.util.Date staff_date_of_birth;

    private String staff_department;

    private String staff_post;

    private String staff_level;

    private AttendanceRecordDO attendanceRecordDO;

    private List<AttendanceRecordDO> attendanceRecordDOList;

    public Long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name == null ? null : staff_name.trim();
    }

    public java.util.Date getStaff_date_of_birth() {
        return staff_date_of_birth;
    }

    public void setStaff_date_of_birth(java.util.Date staff_date_of_birth) {
        if (staff_date_of_birth != null) {
            this.staff_date_of_birth = new java.sql.Date(staff_date_of_birth.getTime());
        } else {
            this.staff_date_of_birth = null;
        }
    }

    public String getStaff_department() {
        return staff_department;
    }

    public void setStaff_department(String staff_department) {
        this.staff_department = staff_department == null ? null : staff_department.trim();
    }

    public String getStaff_post() {
        return staff_post;
    }

    public void setStaff_post(String staff_post) {
        this.staff_post = staff_post == null ? null : staff_post.trim();
    }

    public String getStaff_level() {
        return staff_level;
    }

    public void setStaff_level(String staff_level) {
        this.staff_level = staff_level == null ? null : staff_level.trim();
    }

    public AttendanceRecordDO getAttendanceRecordDO() {
        return attendanceRecordDO;
    }

    public void setAttendanceRecordDO(AttendanceRecordDO attendanceRecordDO) {
        this.attendanceRecordDO = attendanceRecordDO;
    }

    public List<AttendanceRecordDO> getAttendanceRecordDOList() {
        return attendanceRecordDOList;
    }

    public void setAttendanceRecordDOList(List<AttendanceRecordDO> attendanceRecordDOList) {
        this.attendanceRecordDOList = attendanceRecordDOList;
    }
}
