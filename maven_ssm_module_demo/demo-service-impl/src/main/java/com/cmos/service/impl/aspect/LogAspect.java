package com.cmos.service.impl.aspect;

import com.cmos.utils.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author HS
 */
@Component
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.cmos.service.impl..*(..))")
    private void pointcut() {
    }

    @After(value = "pointcut()")
    public void After(JoinPoint joinPoint) throws ClassNotFoundException {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        Class<?> targetClass = Class.forName(className);
        Method[] methods = targetClass.getMethods();

        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    Log logAnnotation = method.getAnnotation(Log.class);
                    if (logAnnotation != null) {
                        String logStr = logAnnotation.logStr();
                        logger.info("获取日志" + logStr);
                        break;
                    }
                }
            }
        }

    }
}
