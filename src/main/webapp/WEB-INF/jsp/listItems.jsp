<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <body>
    <h2>Tab Items List</h2>
    <h3>Number of items is ${items.size()}</h3>    
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
						    		<th class="cell100 column25">Dept</th>
						    		<th class="cell100 column25">Cost</th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">
					 	<table>
					    	<tbody>
								<c:forEach var="item" items="${items}">
							    	<tr class="row100 body">
							        	<td class="cell100 column25">
							         	${item.getId()}
							    		</td>
							        	<td class="cell100 column25">
							         	${item.getType()}
							    		</td>
							        	 <td class="cell100 column25">
							         	${item.getDepartment()}
							    		</td>
							        	 <td class="cell100 column25">
							         	$${item.getCost()}.00
							    		</td>
							    	</tr>
								</c:forEach>
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
