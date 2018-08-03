<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <body>
    <h2>Add new user</h2>
	<div class="limiter">    
		<form id="addUser" method="post" action="#">
			<div class="container-table100">
			<div class="wrap-table50">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
					    	<thead>
					    		<tr class="row100 head">
					    			<th class="cell100 column25">User:</th>
					    			<th class="cell100 column25">${user.getName()}</th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">
				      <table>
							<tbody>
						         <tr  class="row100 body">
						            <td class="cell100 column25">Name: </td>
						            <td class="cell100 column25"><input name="userName" type="text"></td>
						         </tr>		
						         <tr  class="row100 body">
						            <td class="cell100 column25">Pin: </td>
						            <td class="cell100 column25"><input name="pin" type="text"></td>
						         </tr>		
						         <tr  class="row100 body">
						            <td class="cell100 column25">Tag Id: </td>
						            <td class="cell100 column25"><input name="tagId" type="text"/></td>
						         </tr>	
						         <tr  class="row100 body">
						            <td class="cell100 column25"><input name="Submit" type="submit" value="submit" /></td>
						         </tr>						         
						      </tbody>    
					      </table>
					 </div>
				</div>
			</div>
		</div>
		</form>
	</div>
	<%@ include file="footer.jspf" %>				
    </body>
</html>
