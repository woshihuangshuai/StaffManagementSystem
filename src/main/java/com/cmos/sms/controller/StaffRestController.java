package com.cmos.sms.controller;

import com.cmos.sms.beans.Staff;
import com.cmos.sms.iservice.IStaffSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffRestController {

    @Autowired
    private IStaffSV staffservice;

    @RequestMapping(value = "/{staff_id}")
    public Staff getStaffByStaffId(@PathVariable int staff_id) {
        return staffservice.findStaffByStaffId(staff_id);
    }
}
