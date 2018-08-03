<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
	<body>
    <h2>List of Open Bar Tabs</h2>
    <h3>Number of open tabs is ${bartabs.size()}</h3>    
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
					    	<thead>
					    		<tr class="row100 head">
					    			<th class="cell100 column25">Name</th>
					    			<th class="cell100 column25">Opened</th>
					    			<th class="cell100 column25">Last</th>
					    			<th class="cell100 column10">Entries</th>
					    			<th class="cell100 column15">Amount</th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">
					 	<table>
					    	<tbody>
								<c:forEach var="bartab" items="${bartabs}">
								   	<tr class="row100 body">
								       	<td class="cell100 column25">
								        	<a href="/admin/showBarTab?userId=${bartab.getUser().getId()}">${bartab.getUser().getName()}</a>
								   		</td>
								       	<td class="cell100 column25">
								        	${bartab.getOpenTime()}
								   		</td>
								       	 <td class="cell100 column25">
								        	${bartab.getLastTime()}
								   		</td>
								      	 	<td class="cell100 column10">
								        	${bartab.getTotalTransactions()}
								   		</td>    		
								       	 <td class="cell100 column15">
								        	$${bartab.getTotalAmount()}.00
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
