
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>demo</title>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <%@include file="public/publiccss.jsp" %>

    <style type="text/css">
    <%--内部样式--%>
        .demo{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col">
        <div class="col-sm-3"></div>
        <div class="col-sm-6 demo">demo</div>
        <div class="col-sm-3"></div>
    </div>

</div>
<%--引入文件路径--%>
<%@include file="public/publicjs.jsp" %>
<%--js 路径--%>
<script src="/web/js/index.js"></script>
<script>
    <%--内部js--%>
</script>
</body>
</html>
