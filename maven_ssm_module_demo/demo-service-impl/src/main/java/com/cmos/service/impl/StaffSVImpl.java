package com.cmos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cmos.beans.Staff;
import com.cmos.dao.StaffDAO;
import com.cmos.iservice.IStaffSV;
import com.cmos.utils.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class StaffSVImpl implements IStaffSV {

    private final Logger logger = LoggerFactory.getLogger(StaffSVImpl.class);

    @Autowired
    private StaffDAO staffdao;

    /**
     * 根据staff_id查找员工信息
     *
     * @param staff_id
     * @return
     */
    @Log(logStr = "根据staff_id查找用户信息")
    @Override
    @Transactional(rollbackFor = {RuntimeException.class}, isolation = Isolation.REPEATABLE_READ)
    @Cacheable(value = "redisCache", key = "'redis_user_'+#staff_id")
    public Staff findStaffByStaffId(int staff_id) {
        return staffdao.selectByPrimaryKey(staff_id);
    }

    /**
     * 根据staff_id更新员工信息
     *
     * @param staff
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    @CachePut(value = "redisCache", condition = "#result != 'null'", key = "'redis_user_'+#result.staff_id")
    public Staff updateStaffByStaffId(Staff staff) {

        int resultCode = staffdao.updateByPrimaryKey(staff);
        if (resultCode == 1) {
            return staff;
        }
        return null;
    }

    /**
     * 根据staff_id删除员工信息
     *
     * @param staff_id
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#staff_id", beforeInvocation = false)
    public int deleteStaffByStaffId(int staff_id) {
        return staffdao.deleteByPrimaryKey(staff_id);
    }

    /**
     * 插入员工信息
     *
     * @param staff
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    @CachePut(value = "redisCache", condition = "#result != 0", key = "'redis_user_'+#result.staff_id")
    public Staff insertStaff(Staff staff) {
        // 如果插入Id为null时，添加到缓存中的员工信息key为redis_user_null
        int resultCode = staffdao.insert(staff);
        if (resultCode == 1) {
            return staff;
        }
        return null;
    }
}
