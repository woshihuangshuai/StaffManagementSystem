package com.cmos.sms.controller;

import com.cmos.sms.beans.Staff;
import com.cmos.sms.iservice.IStaffSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/staff")
public class StaffController {

    @Autowired
    private IStaffSV staffservice;

    @RequestMapping(value = "/{staff_id}", method = RequestMethod.GET)
    public String findStaffByStaffId(@PathVariable(value = "staff_id") int staff_id, Model model) {
        Staff staff = staffservice.findStaffByStaffId(staff_id);
        model.addAttribute(staff);
        return "showStaff";
    }

    /**
     * @param staff_id
     * @param model
     * @return deleteResult:
     * 1 : 操作成功
     * 0 : 操作失败
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteStaffByStaffId(@RequestParam(value = "staff_id") int staff_id, Model model) {
        model.addAttribute("Operation", "delete");
        int deleteResult = staffservice.deleteStaffByStaffId(staff_id);
        model.addAttribute("Result", deleteResult);
        return "operationResult";
    }


    /**
     * 获取插入表单页
     *
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertForm() {
        return "insertForm";
    }

    /**
     * 插入员工信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertStaff(HttpServletRequest request, Model model) {
        model.addAttribute("Operation", "insert");
        Staff staff = new Staff(null,
                request.getParameter("staff_name"),
                (Date) request.getAttribute("staff_date_of_birth"),
                request.getParameter("staff_department"),
                request.getParameter("staff_post"),
                request.getParameter("staff_level"));
        int insertResult = staffservice.insertStaff(staff);
        model.addAttribute("Result", insertResult);
        return "operationResult";
    }


    /**
     * 获取更新表单页
     * @param staff_id 需要更新的员工Id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateForm(@RequestParam(value = "staff_id") int staff_id, Model model) {
        Staff staff = staffservice.findStaffByStaffId(staff_id);
        model.addAttribute(staff);
        return "updateForm";
    }

    /**
     * 更新员工信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updataStaff(HttpServletRequest request, Model model) {
        model.addAttribute("Operation", "update");
        Staff staff = new Staff(Long.parseLong(request.getParameter("staff_id")),
                request.getParameter("staff_name"),
                (Date) request.getAttribute("staff_date_of_birth"),
                request.getParameter("staff_department"),
                request.getParameter("staff_post"),
                request.getParameter("staff_level"));
        int insertResult = staffservice.updateStaffByStaffId(staff);
        model.addAttribute("Result", insertResult);
        return "operationResult";
    }
}
