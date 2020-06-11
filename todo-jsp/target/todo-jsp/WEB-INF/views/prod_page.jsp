<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.geekbrains.Product" %>
<%@ page import="java.util.List" %>


<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <title>Product page</title>
</head>

<body>

<jsp:include page="navigation.jsp"/>
<h2>Product page</h2>

<div class="col-12">
    <table class="table table-bordered my-2">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Category</th>
            <th scope="col">Title</th>
            <th scope="col">Price</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>

        <tbody>

        <% List<Product> list=(List<Product>) request.getAttribute("products");
            String ps=request.getQueryString();
            String[] str=ps.split("");
            String id="";
            for (int i =3; i<str.length; i++) id+=str[i];
            Product p=list.get(Integer.parseInt(id)-1);%>
            <tr>
                <th scope="row">
                    <%= p.getId()%>
                </th>
                <td>
                    <%= p.getCategory()%>
                </td>
                <td>
                    <%= p.getTitle()%>
                </td>
                <td>
                    <%= p.getPrice()%>
                </td>
                <td>
                    <c:url value="/edit" var="todoEditUrl">
                        <c:param name="id" value="${prod.id}"/>
                    </c:url>
                    <a class="btn btn-success" href="${todoEditUrl}"><i class="fas fa-edit"></i></a>
                    <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                    <c:url value="/cart" var="addToCartUrl">
                        <c:param name="id" value="${prod.id}"/>
                    </c:url>
                    <a class="btn btn-primary" href="${addToCartUrl}"><i class="fa fa-cart-plus"></i></a>

                </td>
            </tr>
        </tbody>
    </table>
</div>



</body>
</html>
