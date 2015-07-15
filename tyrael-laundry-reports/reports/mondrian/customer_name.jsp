<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<c:set var="title01" scope="session">By Customer Name</c:set>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://localhost/tyrael_laundry?user=root&password=root" catalogUri="/WEB-INF/queries/JobOrder.xml">	
	SELECT NON EMPTY {[Measures].[Amount Paid], [Measures].[Amount Due], [Measures].[Job Order Count]} ON columns,
	NON EMPTY {[Customer Names].[All Customer Names]} ON ROWS
	FROM [JobOrder]
</jp:mondrianQuery>  
