<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<c:set var="title01" scope="session">Job Orders</c:set>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://localhost/tyrael_laundry?user=root&password=root" catalogUri="/WEB-INF/queries/tyrael_laundry_schema.xml">	
	SELECT {[Measures].[Amount Paid], [Measures].[Amount Due]} ON columns,
	NON EMPTY {[Date Received].[All Dates].[2000]:[Date Received].[All Dates].[2027]} ON ROWS
	FROM [JobOrder]
</jp:mondrianQuery>
