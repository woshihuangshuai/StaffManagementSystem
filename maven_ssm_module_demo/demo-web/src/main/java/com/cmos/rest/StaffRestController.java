package com.cmos.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmos.beans.Staff;
import com.cmos.beans.dto.StaffDTO;
import com.cmos.iservice.IStaffSV;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/staff")
public class StaffRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private IStaffSV iStaffSV;
    private DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    @ApiOperation(value = "获取员工", notes = "根据staff_id获取员工")
    @ApiImplicitParam(name = "staff_id", value = "员工Id", required = true, dataType = "String")
    @RequestMapping(value = "/{staff_id}", method = RequestMethod.GET)
    public Map<String, Object> findStaffByStaffId(@PathVariable(value = "staff_id") int staff_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "read");
        resultMap.put("result", iStaffSV.findStaffByStaffId(staff_id));
        logger.info(String.format("获取Staff_id为%d的员工信息。", staff_id));
        return resultMap;
    }

    @ApiOperation(value = "删除员工", notes = "跟去员工Id删除员工")
    @ApiImplicitParam(name = "staff_id", value = "员工Id", required = true, dataType = "String")
    @RequestMapping(value = "/delete/{staff_id}", method = RequestMethod.GET)
    public Map<String, Object> deleteStaffByStaffId(@PathVariable(value = "staff_id") int staff_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("staff_id", staff_id);
        resultMap.put("action", "delete");
        int deleteResult = iStaffSV.deleteStaffByStaffId(staff_id);
        if (deleteResult == 1) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "failure");
        }
        return resultMap;
    }

    @ApiOperation(value = "插入员工", notes = "向数据库中添加员工信息", response = Map.class)
    @ApiImplicitParam(name = "staffDTO", value = "员工数据传输对象", required = true, dataType = "StaffDTO")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map<String, Object> insertStaff(@RequestBody StaffDTO staffDTO) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "create");

        Staff staff = new Staff();
        staff.setStaff_id(
                staffDTO.getStaff_id() == null ? null : Long.parseLong(staffDTO.getStaff_id()));
        staff.setStaff_name(staffDTO.getStaff_name());
        staff.setStaff_date_of_birth(
                staffDTO.getStaff_date_of_birth() == null ? null : dateformat.parse(staffDTO.getStaff_date_of_birth()));
        staff.setStaff_department(staffDTO.getStaff_department());
        staff.setStaff_post(staffDTO.getStaff_post());
        staff.setStaff_level(staffDTO.getStaff_level());

        Object result = iStaffSV.insertStaff(staff);
        if (result != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "failure");
        }
        return resultMap;
    }

    @ApiOperation(value = "更新员工", notes = "根据员工Id更新员工信息，员工Id不可更新")
    @ApiImplicitParam(name = "staffDTO", value = "员工数据传输对象", required = true, dataType = "StaffDTO")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map updateForm(@RequestBody StaffDTO staffDTO) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "update");
        if (staffDTO.getStaff_id() == null) {
            resultMap.put("result", "staff_id is null.");
            return resultMap;
        }
        Staff staff = iStaffSV.findStaffByStaffId(Integer.parseInt(staffDTO.getStaff_id()));
        if (staff == null) {
            resultMap.put("result", "Staff not exist.");
            return resultMap;
        }
        staff.setStaff_name(
                staffDTO.getStaff_name() == null ?
                        staff.getStaff_name() : staffDTO.getStaff_name());
        staff.setStaff_date_of_birth(
                staffDTO.getStaff_date_of_birth() == null ?
                        staff.getStaff_date_of_birth() : dateformat.parse(staffDTO.getStaff_date_of_birth()));
        staff.setStaff_department(
                staffDTO.getStaff_department() == null ?
                        staff.getStaff_department(): staffDTO.getStaff_department());
        staff.setStaff_post(
                staffDTO.getStaff_post() == null ?
                        staff.getStaff_post() : staffDTO.getStaff_post());
        staff.setStaff_level(
                staffDTO.getStaff_level() == null ?
                        staff.getStaff_level() : staffDTO.getStaff_level());
        Object result = iStaffSV.updateStaffByStaffId(staff);
        if (result != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "failure");
        }
        return resultMap;
    }
}
