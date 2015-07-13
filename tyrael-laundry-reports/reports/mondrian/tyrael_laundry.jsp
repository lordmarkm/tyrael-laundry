<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://localhost/tyrael_laundry?user=root&password=root" catalogUri="/WEB-INF/queries/JobOrder.xml">	
	select {[Measures].[Amount Paid], [Measures].[Amount Due]} ON columns,
	NON EMPTY {[Date Received].[All Dates].[2015].Children} ON ROWS
	FROM [JobOrder]
</jp:mondrianQuery>

<c:set var="title01" scope="session">Job Orders</c:set>
