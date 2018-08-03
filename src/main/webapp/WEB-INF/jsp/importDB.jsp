<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <h2>Import Database</h2>
    <body>
		<form id="uploadbanner" enctype="multipart/form-data" method="post" action="#">
		   <input id="fileupload" name="file" type="file" />
		   <input type="submit" value="submit" id="submit" />
		</form>
    </body>
</html>
