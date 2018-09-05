package com.cmos.rest;

import com.cmos.beans.Staff;
import com.cmos.beans.dto.StaffDTO;
import com.cmos.iservice.IStaffSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/staff")
public class StaffRestController {

    private final IStaffSV iStaffSV;
    private DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public StaffRestController(IStaffSV iStaffSV) {
        this.iStaffSV = iStaffSV;
    }

    /**
     * REST API Read
     */
    @RequestMapping(value = "/{staff_id}", method = RequestMethod.GET)
    public Map<String, Object> findStaffByStaffId(@PathVariable(value = "staff_id") int staff_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "read");
        resultMap.put("result", iStaffSV.findStaffByStaffId(staff_id));
        return resultMap;
    }

    /**
     * REST API Delete
     */
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

    /**
     * REST API Create
     */
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

        int resultCode = iStaffSV.insertStaff(staff);
        if (resultCode == 1) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "failure");
        }
        return resultMap;
    }

    /**
     * REST API Update
     */
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
        int resultCode = iStaffSV.updateStaffByStaffId(staff);
        if (resultCode == 1) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "failure");
        }
        return resultMap;
    }
}
