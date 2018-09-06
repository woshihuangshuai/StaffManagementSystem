package com.cmos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cmos.beans.Staff;
import com.cmos.dao.StaffDAO;
import com.cmos.iservice.IStaffSV;
import org.springframework.beans.factory.annotation.Autowired;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class StaffSVImpl implements IStaffSV {

    @Autowired
    private StaffDAO staffdao;

    /**
     * 根据staff_id查找员工信息
     * @param staff_id
     * @return
     */
    public Staff findStaffByStaffId(int staff_id) {
        return staffdao.selectByPrimaryKey(staff_id);
    }

    /**
     * 根据staff_id更新员工信息
     *
     * @param staff
     * @return
     */
    public int updateStaffByStaffId(Staff staff) {
        return staffdao.updateByPrimaryKey(staff);
    }

    /**
     * 根据staff_id删除员工信息
     *
     * @param staff_id
     * @return
     */
    public int deleteStaffByStaffId(int staff_id) {
        return staffdao.deleteByPrimaryKey(staff_id);
    }

    /**
     * 插入员工信息
     *
     * @param staff
     * @return
     */
    public int insertStaff(Staff staff) {
        return staffdao.insert(staff);
    }
}
