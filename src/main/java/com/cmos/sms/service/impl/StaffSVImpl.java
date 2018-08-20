package com.cmos.sms.service.impl;

import com.cmos.sms.beans.Staff;
import com.cmos.sms.dao.StaffDAO;
import com.cmos.sms.iservice.IStaffSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffSVImpl implements IStaffSV {

    @Autowired
    private StaffDAO staffdao;

    public StaffSVImpl() {
        super();
    }

    /**
     * 根据staff_id查找员工信息
     * @param staff_id
     * @return
     */
    @Override
    public Staff findStaffByStaffId(int staff_id) {
        return staffdao.selectByPrimaryKey(staff_id);
    }

    /**
     * 根据staff_id更新员工信息
     *
     * @param staff
     * @return
     */
    @Override
    public int updateStaffByStaffId(Staff staff) {
        return staffdao.updateByPrimaryKey(staff);
    }

    /**
     * 根据staff_id删除员工信息
     *
     * @param staff_id
     * @return
     */
    @Override
    public int deleteStaffByStaffId(int staff_id) {
        return staffdao.deleteByPrimaryKey(staff_id);
    }

    /**
     * 插入员工信息
     *
     * @param staff
     * @return
     */
    @Override
    public int insertStaff(Staff staff) {
        return staffdao.insert(staff);
    }
}
