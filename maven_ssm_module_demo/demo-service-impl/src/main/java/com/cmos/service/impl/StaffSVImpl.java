package com.cmos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.cmos.beans.Staff;
import com.cmos.dao.StaffDAO;
import com.cmos.iservice.IStaffSV;
import com.cmos.utils.annotation.Log;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        logger.info(JSONArray.toJSON(staff).toString());
        int resultCode = staffdao.insert(staff);
        logger.info(JSONArray.toJSON(staff).toString());
        if (resultCode == 1) {
            return staff;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public Staff selectByObject(Staff staff) {
        return staffdao.selectByObject(staff);
    }

    /**
     * 根据列表查询员工信息
     *
     * @param staffIdList
     * @return
     */
    @Override
    public List<Staff> selectByList(List<Integer> staffIdList) {
        return staffdao.selectByList(staffIdList);
    }

    /**
     * 根据员工id数组查询员工信息
     *
     * @param staffIdArray
     * @return
     */
    @Override
    public List<Staff> selectByArray(Integer[] staffIdArray) {
        return staffdao.selectByArray(staffIdArray);
    }

    /**
     * 批量插入员工信息，返回影响的数据库行数
     *
     * @param staffList
     * @return
     */
    @Override
    public int insertByList(List<Staff> staffList) {
        for (Staff staff : staffList) {
            logger.info("插入之前：" + JSONArray.toJSON(staff).toString());
        }
        int result = staffdao.insertByList(staffList);
        for (Staff staff : staffList) {
            logger.info("插入之后：" + JSONArray.toJSON(staff).toString());
        }
        return result;
    }
}
