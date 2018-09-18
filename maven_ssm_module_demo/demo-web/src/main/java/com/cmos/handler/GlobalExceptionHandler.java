package com.cmos.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HS
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Map defaultExceptionHandler(HttpServletRequest request, Exception e) {
        Map<String, Object> errorMap = new HashMap<>(2);
        errorMap.put("url", request.getRequestURL());
        errorMap.put("error", e);
        return errorMap;
    }
}
