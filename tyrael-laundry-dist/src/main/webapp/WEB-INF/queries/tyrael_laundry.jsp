<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="title01" scope="session">Job Orders</c:set>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://localhost/tyrael_laundry?user=root&password=root" catalogUri="/WEB-INF/queries/tyrael_laundry_schema.xml">	
  select {[Measures].[Amount Paid], [Measures].[Amount Due]} ON COLUMNS,
  NON EMPTY Hierarchize(Union(Union({[Date Received].[All Dates].[2015]}, [Date Received].[All Dates].[2015].Children), [Date Received].[All Dates].[2015].[June].Children)) ON ROWS
from [JobOrder]
</jp:mondrianQuery>

<c:out value="${msg }" />
<c:out value="${qqq }" />