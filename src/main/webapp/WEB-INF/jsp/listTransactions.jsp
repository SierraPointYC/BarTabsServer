<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <body>
    <h2>Transactions List</h2>
    <h3>Number of transactions is ${transactions.size()}</h3>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
						    <thead>
					    		<tr class="row100 head">
					    			<th class="cell100 column10">Id</th>
					    			<th class="cell100 column15">Date</th>
					    			<th class="cell100 column15">Name</th>
					    			<th class="cell100 column10">Dept</th>
					    			<th class="cell100 column10">Item</th>
					    			<th class="cell100 column5">Qty</th>
					    			<th class="cell100 column10">Amount</th>
					    			<th class="cell100 column10">Status</th>
					    			<th class="cell100 column15">Closed</th>
					    		</tr>
					    	</thead>
					    </table>
					</div>
					
					<div class="table100-body js-pscroll">
						<table>				 	
			    			<tbody>			    				    	
							<c:forEach var="transaction" items="${transactions}">
						    	<tr class="row100 body">
						        	 <td class="cell100 column10">
						         	${transaction.getId()}
						    		</td>
						        	 <td class="cell100 column15">
						        	 <fmt:formatDate type = "both" 
						         dateStyle = "short" timeStyle = "medium" value = "${transaction.getOpenDate()}" />
						    		</td>
						        	 <td class="cell100 column15">
						         	${transaction.getUser().getName()}
						    		</td>
						        	 <td class="cell100 column10">
						         	${transaction.getDepartment()}
						    		</td>
						        	 <td class="cell100 column10">
						         	${transaction.getItem()}
						    		</td>
						        	 <td class="cell100 column5">
						         	${transaction.getItems()}
						    		</td>
						        	 <td class="cell100 column10">
						         	$${transaction.getAmount()}.00
						    		</td>
						        	 <td class="cell100 column10">
						         	${transaction.getStatus()}
						    		</td>
						        	 <td class="cell100 column15">
						        	 <fmt:formatDate type = "both" 
						         dateStyle = "short" timeStyle = "medium" value = "${transaction.getCloseDate()}" />
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
