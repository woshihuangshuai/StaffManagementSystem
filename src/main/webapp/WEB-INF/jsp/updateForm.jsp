<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>插入员工信息</title>
  </head>

  <body>
    <h1>更新员工信息</h1>
    <form action="update" method="POST">
        ID： <input type="text" name="staff_id" value=${staff.staff_id}><br/>
        姓名：<input type="text" name="staff_name" value=${staff.staff_name}><br/>
        出生日期：<input type="date" name="staff_date_of_birth" value=${staff.staff_date_of_birth}><br/>
        部门：<input type="text" name="staff_department" value=${staff.staff_department} /><br/>
        职位：<input type="text" name="staff_post" value=${staff.staff_post}><br/>
        等级：<input type="text" name="staff_level" value=${staff.staff_level}><br/>
        <input type="submit" value="提交"><br/>
    </form>
  </body>
</html>