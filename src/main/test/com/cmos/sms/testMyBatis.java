package com.cmos.sms;

import com.cmos.sms.beans.Staff;
import com.cmos.sms.iservice.IStaffSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class testMyBatis {


    @Autowired
    private IStaffSV staffsv;

    @Test
    public void test() {
        Staff staff = staffsv.findStaffByStaffId(1);
        System.out.println(staff.getStaff_date_of_birth());
    }
}

