<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light col-8">

    <div class="col-2">
        <c:url value="/" var="newUrl"/>
        <a class="btn btn-primary" href="${newUrl}">Catalog</a>
    </div>
    <div class="col-2">
        <c:url value="/about" var="aboutUrl"/>
        <a class="btn btn-dark" href="${aboutUrl}">About us</a>
    </div>
    <div class="col-2">
        <c:url value="/cart" var="cartUrl"/>
        <a class="btn btn-warning" href="${cartUrl}"><i class="fa fa-cart-plus"></i></a>
    </div>
    <div class="col-2">
        <c:url value="/order" var="orderUrl"/>
        <a class="btn btn-danger" href="${orderUrl}">Order</a>
    </div>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#"></a>
            </li>
        </ul>
    </div>
</nav>
