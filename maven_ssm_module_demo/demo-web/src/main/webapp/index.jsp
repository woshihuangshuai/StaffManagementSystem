<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p>Get your greeting <a href="/greeting">here</a></p>
<form action="/upload" method="POST" enctype="multipart/form-data">
    文件：<input type="file" name="test"/>
    <input type="submit" />
</form>
<a href="/download">下载test</a>
<p>多文件上传</p>
<form method="POST" enctype="multipart/form-data" action="/batch/upload">
    <p>文件1：<input type="file" name="file" /></p>
    <p>文件2：<input type="file" name="file" /></p>
    <p><input type="submit" value="上传" /></p>
</form>
</body>
</html>