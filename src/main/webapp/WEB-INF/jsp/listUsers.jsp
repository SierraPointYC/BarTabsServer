<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <h2>User List</h2>
    <h3>Number of users is ${users.size()}</h3>    
    <body>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
					    	<thead>
					    		<tr class="row100 head">
					    			<th class="cell100 column25">Id</th>
					    			<th class="cell100 column25">Name</th>
					    			<th class="cell100 column25">Pin</th>
					    			<th class="cell100 column25">Tag</th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">    
					    <table>
						    <tbody>
							<c:forEach var="user" items="${users}">
						    	<tr class="row100 body">
						        	 <td class="cell100 column25">
						         	${user.getId()}
						    		</td>
						        	 <td class="cell100 column25">
						         	${user.getName()}
						    		</td>
						        	 <td class="cell100 column25">
										 <c:if test="${not empty user.getPin()}">
						         			<a href="/admin/updatePin?userId=${user.getId()}">${user.getPin()}</a>
										</c:if>	
										 <c:if test="${empty user.getPin()}">
						         			<a href="/admin/updatePin?userId=${user.getId()}">add</a>
										</c:if>						        	 
						    		</td>
						        	 <td class="cell100 column25">
										 <c:if test="${not empty user.getTag()}">
						         			<a href="/admin/updateTag?userId=${user.getId()}">${user.getTag()}</a>
										</c:if>	
										 <c:if test="${empty user.getTag()}">
						         			<a href="/admin/updateTag?userId=${user.getId()}">add</a>
										</c:if>						        	 
						    		</td>    		
						    	</tr>
							</c:forEach>
								<tr  class="row100 head">
						        	 <td class="cell100 column25">
						         	 <a href="/admin/addUser">Add New User</a>
						    		</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jspf" %>	
    </body>
</html>
