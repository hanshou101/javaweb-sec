<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sun.rowset.JdbcRowSetImpl" %>
<%
    // J  java
    // db  database
    // c  Connection
    // RowSet          其实，数据库都是一行一行的。  所以是row。   set代表  结果集。
    JdbcRowSetImpl jdbcRowSet = new JdbcRowSetImpl();
    jdbcRowSet.setDataSourceName(request.getParameter("url"));
    jdbcRowSet.setAutoCommit(true);
%>