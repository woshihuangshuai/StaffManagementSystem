package com.cmos.dao.test;

import com.cmos.dao.DaoApplication;
import com.cmos.dao.StaffDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@Import(value = {DaoApplication.class})
public class MyBaitsTest {

    @Autowired
    private StaffDAO staffDAO;

    @Test
    public void testDataSourceConnection() {
        System.out.println(staffDAO.selectByPrimaryKey(1).getStaff_name());
    }
}
