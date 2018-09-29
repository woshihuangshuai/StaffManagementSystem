package com.cmos.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmos.beans.AttendanceRecordDO;
import com.cmos.beans.Staff;
import com.cmos.beans.dto.AttendanceRecordDTO;
import com.cmos.iservice.IAttendanceRecordSV;
import com.cmos.iservice.IStaffSV;
import com.cmos.validator.AttendanceRecordDTOValidator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HS
 */
@RestController
@RequestMapping("/attendanceRecord")
public class AttendanceRecordController {

    private Logger logger = LoggerFactory.getLogger(AttendanceRecordController.class);

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private IAttendanceRecordSV iAttendanceRecordSV;

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private IStaffSV iStaffSV;

    private DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    @ApiOperation(value = "插入记录", notes = "插入员工打卡记录")
    @PostMapping("/insert")
    public Map insertAttendanceRecord(@Valid @RequestBody AttendanceRecordDTO attendanceRecordDTO, @ApiIgnore Errors errors) throws ParseException {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "insert");
        resultMap.put("table", "t_attendance_record");

        Map<String, Object> errorMap = new HashMap<>();
        if (errors.getErrorCount() > 0) {
            List<ObjectError> objectErrorList = errors.getAllErrors();
            for (ObjectError objectError : objectErrorList) {
                String key;
                String errorMessage;
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    key = fieldError.getField();
                } else {
                    key = objectError.getObjectName();
                }
                errorMessage = objectError.getDefaultMessage();
                errorMap.put(key, errorMessage);
            }

            resultMap.put("errors", errorMap);
            return resultMap;
        }

        AttendanceRecordDO attendanceRecordDO = new AttendanceRecordDO();
        if (attendanceRecordDTO.getRecordId() != null) {
            attendanceRecordDO.setRecordId(attendanceRecordDTO.getRecordId());
        }
        Long destStaffId = attendanceRecordDTO.getStaffId().longValue();
        Staff staff = iStaffSV.findStaffByStaffId(destStaffId.intValue());

        if (ObjectUtils.isEmpty(staff)) {
            resultMap.put("result", "员工信息不存在");
            return resultMap;
        }

        attendanceRecordDO.setStaffId(attendanceRecordDTO.getStaffId());
        attendanceRecordDO.setDate(dateformat.parse(attendanceRecordDTO.getDate()));
        int resultCode = iAttendanceRecordSV.insert(attendanceRecordDO);
        if (resultCode == 1) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "failure");
        }
        return resultMap;
    }

    @ApiOperation(value = "获取打卡记录", notes = "根据打卡记录Id获取打卡记录")
    @ApiImplicitParam(name = "recordId", value = "打卡记录Id", dataTypeClass = Long.class)
    @GetMapping("/{recordId}")
    public Map getAttendanceRecordByRecordId(@PathVariable Long recordId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "read");
        resultMap.put("table", "t_attendance_record");
        AttendanceRecordDO attendanceRecordDO = iAttendanceRecordSV.getAttendanceRecordByRecordId(recordId);
        resultMap.put("result", attendanceRecordDO);
        return resultMap;
    }

    @ApiOperation(value = "查询员工打卡记录", notes = "根据员工Id查询打卡记录")
    @ApiImplicitParam(name = "staffId", dataTypeClass = Integer.class, value = "员工Id")
    @GetMapping("/staff/{staffId}")
    public Map getAttendanceRecordByStaffId(@PathVariable Integer staffId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "read");
        resultMap.put("table", "t_attendance_record");
        List<AttendanceRecordDO> resultList = iAttendanceRecordSV.getRecordByStaffId(staffId);
        resultMap.put("result", resultList);
        return resultMap;
    }

    @ApiOperation(value = "修改员工打卡记录")
    @PostMapping("/update")
    public ModelAndView updateRecord(@RequestBody AttendanceRecordDTO attendanceRecordDTO) throws ParseException {
        AttendanceRecordDO attendanceRecordDO = new AttendanceRecordDO();
        attendanceRecordDO.setRecordId(attendanceRecordDTO.getRecordId());
        attendanceRecordDO.setStaffId(attendanceRecordDTO.getStaffId());
        attendanceRecordDO.setDate(dateformat.parse(attendanceRecordDTO.getDate()));
        iAttendanceRecordSV.updateSelective(attendanceRecordDO);

        int staffId = attendanceRecordDTO.getStaffId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/attendanceRecord/staff/" + staffId);
        return modelAndView;
    }

    @ApiOperation(value = "查询员工打卡记录", notes = "staff_info和t_attendance_record联合查询的结果")
    @ApiImplicitParam(name = "staffId", required = false, defaultValue = "1", dataTypeClass = Integer.class)
    @GetMapping("/recordList/{staffId}")
    public Map getRecordList(@PathVariable Integer staffId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("action", "read");
        resultMap.put("table", "t_attendance_record");
        List<Map<String, String>> resultList = iAttendanceRecordSV.getRecordList(staffId);
        resultMap.put("result", resultList);
        return resultMap;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new AttendanceRecordDTOValidator());
    }

    @ApiOperation(value = "测试spring数据校验")
    @PostMapping("/validation")
    public Map testDataValidation(@Valid @RequestBody AttendanceRecordDTO attendanceRecordDTO, @ApiIgnore Errors errors) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("errorCount", errors.getErrorCount());
        List<ObjectError> objectErrorList = errors.getAllErrors();
        for (ObjectError objectError : objectErrorList) {
            String key;
            String errorMessage;
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                key = fieldError.getField();
            } else {
                key = objectError.getObjectName();
            }
            errorMessage = objectError.getDefaultMessage();
            resultMap.put(key, errorMessage);
        }
        return resultMap;
    }
}
