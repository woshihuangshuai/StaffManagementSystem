package com.cmos.dao.test;

import com.cmos.beans.Staff;
import com.cmos.dao.DaoApplication;
import com.cmos.dao.StaffDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RunWith(SpringJUnit4ClassRunner.class)
@Import(value = {DaoApplication.class})
public class MyBaitsTest {

    @Autowired
    private StaffDAO staffDAO;

    private DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testDataSourceConnection() {
        System.out.println(staffDAO.selectByPrimaryKey(1).getStaff_name());
    }

    @Test
    @Transactional
    @Rollback
    public void testInsert() throws ParseException {
        Staff staff = new Staff();
        staff.setStaff_id(18L);
        staff.setStaff_name("testInsert");
        staff.setStaff_date_of_birth(dateformat.parse("1999-09-09"));
        staff.setStaff_level("99");
        staff.setStaff_department("test");
        staff.setStaff_post("test");

        staffDAO.insert(staff);
        Staff staff1 = staffDAO.selectByPrimaryKey(18);
        System.out.println(staff1.getStaff_name());
    }
}
