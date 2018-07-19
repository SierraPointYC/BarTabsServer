<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head></head>
    <h2>${msg}</h2>
    <h3>Number of users is ${nameList.size()}></h3>    
    <body>
    <table>
	<c:forEach var="user" items="${nameList}">
    	<tr>
        	 <td align="right">
         	${user}
    		</td>
    	</tr>
	</c:forEach>
	</table>
    </body>
</html>
