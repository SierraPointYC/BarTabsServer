<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
 	<%@ include file="header.jspf" %>
    <body>
    <h2>Bar tab for ${bartab.getUser().getName()}</h2>    
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    <table>
					    	<thead>
					    		<tr>
					    			<th class="cell100 column25">Item</th>
					    			<th class="cell100 column25">Qty</th>
					    			<th class="cell100 column25">Amount</th>
					    		</tr>
					    	</thead>
					 	</table>
					 </div>
					 <div class="table100-body js-pscroll">
					 	<table>
					    	<tbody>
								<c:forEach var="item" items="${bartab.getItemsOnTab()}">
								   	<tr class="row100 body">
								       	<td class="cell100 column25">
								        	${item}
								   		</td>
								       	<td class="cell100 column25">
								        	${bartab.getItemCount(item)}
								   		</td>
								       	 <td class="cell100 column25">
								        	$${bartab.getItemTotal(item)}.00
								        </td>
								   	</tr>
								</c:forEach>
									<tr class="row100 body">
										<td class="cell100 column25">
											Total
										</td>
										<td class="cell100 column25">
										</td>
										<td class="cell100 column25">
											$${bartab.getTotalAmount()}.00
										</td>
									</tr>
									<tr class="row100 body">
										<td class="cell100 column25">
											Open Date
										</td>
										<td class="cell100 column25">
											${bartab.getOpenTime()}
										</td>
									</tr>
									<tr class="row100 body">
										<td class="cell100 column25">
											Last Date
										</td>
										<td class="cell100 column25">
											${bartab.getLastTime()}
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
