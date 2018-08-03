<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
	<%@ include file="header.jspf" %>
    <body>
    <div class="container">    
	    <div id="header">
	    	<h2>Bar Admin Menu</h2>
	    </div>
		<div class="content">
			<ul class="links">
		    	<li><a href="/admin/listBarTabs">List Open Bar Tabs</a></li>
		    	<li><a href="/admin/listUsers">Manage Users</a></li>
		    	<li><a href="/admin/listTransactions">List Transactions</a></li>
		    	<li><a href="/admin/listPayments">List Payments</a></li>
		    	<li><a href="/admin/listItems">List Items</a></li>
		    	<li><a href="/admin/initDB">Initialize Database</a></li>
		    	<li><a href="/admin/importDB">Import Database</a></li>
		    	<li><a href="/admin/exportDB" download="bartabs.csv">Export Database</a></li>
		    	<li><a href="/admin/logout">Log Out</a></li>
			</ul>
		</div>
	</div>
	<%@ include file="footer.jspf" %>				
	</body>
</html>
