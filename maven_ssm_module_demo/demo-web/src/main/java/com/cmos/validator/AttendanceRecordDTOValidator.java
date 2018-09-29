package com.cmos.validator;

import com.cmos.beans.dto.AttendanceRecordDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author HS
 */
public class AttendanceRecordDTOValidator implements Validator {
    /**
     * Can this {@link Validator} {@link #validate(Object, Errors) validate}
     * instances of the supplied {@code clazz}?
     * <p>This method is <i>typically</i> implemented like so:
     * <pre class="code">return Foo.class.isAssignableFrom(clazz);</pre>
     * (Where {@code Foo} is the class (or superclass) of the actual
     * object instance that is to be {@link #validate(Object, Errors) validated}.)
     *
     * @param clazz the {@link Class} that this {@link Validator} is
     *              being asked if it can {@link #validate(Object, Errors) validate}
     * @return {@code true} if this {@link Validator} can indeed
     * {@link #validate(Object, Errors) validate} instances of the
     * supplied {@code clazz}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AttendanceRecordDTO.class);
    }

    /**
     * Validate the supplied {@code target} object, which must be
     * of a {@link Class} for which the {@link #supports(Class)} method
     * typically has (or would) return {@code true}.
     * <p>The supplied {@link Errors errors} instance can be used to report
     * any resulting validation errors.
     *
     * @param target the object that is to be validated (can be {@code null})
     * @param errors contextual state about the validation process (never {@code null})
     * @see ValidationUtils
     */
    @Override
    public void validate(Object target, Errors errors) {
        if (target == null) {
            errors.rejectValue("", null, "Spring Validator: 对象不能为null");
            return;
        }

        String pattern = "((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|" +
                "1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2" +
                "000))-0?2-29))$";

        AttendanceRecordDTO attendanceRecordDTO = (AttendanceRecordDTO) target;
        boolean isMatch = Pattern.matches(pattern, attendanceRecordDTO.getDate());
        if (StringUtils.isBlank(attendanceRecordDTO.getDate())) {
            errors.rejectValue("Date", null, "Spring Validator: 日期不能为空");
        } else {
            if (!isMatch) {
                errors.rejectValue("Date", null, "Spring Validator: 日期格式不正确");
            }
        }
    }
}
