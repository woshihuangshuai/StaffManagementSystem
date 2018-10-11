package com.cmos.controller.test;

import com.cmos.beans.Staff;
import com.cmos.iservice.IStaffSV;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaffControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @MockBean
    private IStaffSV iStaffSV;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void insertFormTest() throws Exception {
        String viewName = "insertForm";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/staff/insert"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(viewName))
                .andDo(MockMvcResultHandlers.print());
        String resultViewName = resultActions.andReturn().getModelAndView().getViewName();
        MatcherAssert.assertThat(resultViewName, Is.is(viewName));
    }

    @Test
    public void testStaffController() {
        Staff mockStaff = new Staff();
        mockStaff.setStaff_id(10L);
        mockStaff.setStaff_name("Test");
        mockStaff.setStaff_level("10");

        BDDMockito.given(this.iStaffSV.findStaffByStaffId(10)).willReturn(mockStaff);

        Staff staff = iStaffSV.findStaffByStaffId(10);
        Assert.assertTrue(staff.getStaff_id() == 10L);
    }
}
