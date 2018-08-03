<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<!-- base href="/"-->
	<%@ include file="header.jspf" %>
<body>
   <h2>Bar Admin Login</h2>
	<div class="limiter">
		<form name='f' action="login" method='POST'>
		<div class="container-table100">
			<div class="wrap-table50">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
					    	<thead>
					    		<tr class="row100 head">
					    			<th class="cell100 column25">Log In</th>
					    			<th class="cell100 column25"></th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">
				      <table>
							<tbody>
						         <tr  class="row100 body">
						            <td class="cell100 column25">User:</td>
						            <td class="cell100 column25"><input type='text' name='username' value=''></td>
						         </tr>
						         <tr  class="row100 body">
						            <td class="cell100 column25">Password:</td>
						            <td class="cell100 column25"><input type='password' name='password' /></td>
						         </tr>
						         <tr  class="row100 body">
						            <td class="cell100 column25"><input name="Submit" type="submit" value="submit" /></td>
						         </tr>
						      </tbody>    
					      </table>
					 </div>
					 <c:if test="${not empty param.error}">
    					<div class="error">
        				Your login attempt was not successful, try again.<br />
        				Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
    					</div>
					</c:if>
				</div>
			</div>
		</div>
		</form>
	</div>
	<%@ include file="footer.jspf" %>				
</body>
</html>
