<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>插入员工信息</title>
  </head>

  <body>
    <h1>插入员工信息</h1>
    <form method="POST">
        姓名：<input type="text" name="staff_name"/><br/>
        出生日期：<input type="date" name="staff_date_of_birth"/><br/>
        部门：<input type="text" name="staff_department"/><br/>
        职位：<input type="text" name="staff_post"/><br/>
        等级：<input type="text" name="staff_level"/><br/>
        <input type="submit" value="提交"/><br/>
    </form>
  </body>
</html>