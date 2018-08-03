<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <body>
    <h2>Payment List</h2>
    <h3>Number of payments is ${payments.size()}</h3>    
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
					    	<thead>
					    		<tr class="row100 head">
					    			<th class="cell100 column10">Id</th>
					    			<th class="cell100 column25">Date</th>
					    			<th class="cell100 column15">Name</th>
					    			<th class="cell100 column15">Dept</th>
					    			<th class="cell100 column20">Method</th>
					    			<th class="cell100 column15">Amount</th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">
					 	<table>
					    	<tbody>
								<c:forEach var="payment" items="${payments}">
							    	<tr class="row100 body">
							        	 <td class="cell100 column10">
							         	${payment.getId()}
							    		</td>
							        	 <td class="cell100 column25">
							        	 <fmt:formatDate type = "both" 
							         dateStyle = "short" timeStyle = "long" value = "${payment.getDate()}" />
							    		</td>
							        	 <td class="cell100 column15">
							         	${payment.getUser().getName()}
							    		</td>
							        	 <td class="cell100 column15">
							         	${payment.getDepartment()}
							    		</td>
							        	 <td class="cell100 column20">
							         	${payment.getMethod()}
							    		</td>
							        	 <td class="cell100 column15">
							         	$${payment.getAmount()}.00
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
