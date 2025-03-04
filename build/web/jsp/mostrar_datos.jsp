<%-- 
    Document   : mostrar_datos
    Created on : 06-nov-2024, 19:07:49
    Author     : fredi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<html>
    <head>
        <title>JSTL x:parse Tags</title>
    </head>
    <body>
        <h3>Books Info:</h3>
        <c:import var="bookInfo" url="http://localhost:8089/xml_con_jsp/jsp/books.xml" />
        <x:parse xml="${bookInfo}" var="output" />
        <b>The title of the first book is</b>:
        <x:out select="$output/books/book[1]/name" />
        <br>
        <b>The price of the second book</b>:
        <x:out select="$output/books/book[2]/price" />
    </body>
</html>
