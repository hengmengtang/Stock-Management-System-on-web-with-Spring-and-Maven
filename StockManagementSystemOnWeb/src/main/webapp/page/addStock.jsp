<%@page import="java.util.List"%>
<%@page import="dao.ProductDAOImple"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../jquery/jquery-2.2.4.min.js"></script>
<script src="../jquery/jquery-2.2.4.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<% 
		Product product=new Product();
		//List<Product> listProduct=new ArrayList<Product>();
		ProductDAOImple viewProduct=new ProductDAOImple();
	%>
	<center>
		<div class="container">
		<table class="table table-striped table-bordered table-hover table-responsive">
			<thead>
				<tr>
					<th>PID</th>
					<th>PName</th>
					<th>PQty</th>
					<th>PUnitPrice</th>
					<th>PImportDate</th>
				</tr>
			</thead>
			<tbody>
				<%for(Product list: viewProduct.getProduct(product)) { %>
					<tr>
						<td><%=list.getId() %></td>
						<td><%=list.getpName() %></td>
						<td><%=list.getpStockQty() %></td>
						<td><%=list.getpUnitPrice() %></td>
						<td><%=list.getpImportDate() %></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	</center>
</body>
</html>