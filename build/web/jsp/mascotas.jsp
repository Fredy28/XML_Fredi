<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<String[]> mascotas = (List<String[]>) session.getAttribute("mascotas");
    Integer idCounter = (Integer) session.getAttribute("idCounter");

    if (mascotas == null) {
        mascotas = new ArrayList<>();
        session.setAttribute("mascotas", mascotas);
    }

    if (idCounter == null) {
        idCounter = 1;
        session.setAttribute("idCounter", idCounter);
    }

    // Validar y agregar nueva mascota si hay datos
    String nombre = request.getParameter("nombre");
    String edad = request.getParameter("edad");
    String tipo = request.getParameter("tipo");

    if (nombre != null && !nombre.isEmpty() && edad != null && !edad.isEmpty() && tipo != null && !tipo.isEmpty()) {
        String[] nuevaMascota = {idCounter.toString(), nombre, edad, tipo};
        mascotas.add(nuevaMascota);
        idCounter++;
        session.setAttribute("mascotas", mascotas);
        session.setAttribute("idCounter", idCounter);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Registro de Mascotas</title>
        <style>
            table {
                width: 50%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h2>Ingresa una mascota</h2>
        <form action="mascotas.jsp" method="post">
            Nombre de la mascota: <input type="text" name="nombre" required/><br/>
            Edad: <input type="number" name="edad" required/><br/>
            Tipo de mascota: <input type="text" name="tipo" required/><br/>
            <button type="submit">Guardar Datos</button>
        </form>

        <h2>Mostrar mascotas ingresadas</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre de la mascota</th>
                <th>Edad</th>
                <th>Tipo de mascota</th>
                <th>Acciones</th>
            </tr>
            <% for (String[] mascota : mascotas) {%>
            <tr>
                <td><%= mascota[0]%></td>
                <td><%= mascota[1]%></td>
                <td><%= mascota[2]%></td>
                <td><%= mascota[3]%></td>
                <td>
                    <form action="<%= request.getContextPath()%>/mascotasxml" method="POST">
                        <input type="hidden" name="id" value="<%= mascota[0]%>"/>
                        <input type="hidden" name="nombre" value="<%= mascota[1]%>"/>
                        <input type="hidden" name="edad" value="<%= mascota[2]%>"/>
                        <input type="hidden" name="tipo" value="<%= mascota[3]%>"/>
                        <button type="submit">Generar XML</button>
                    </form>

                    <form action="<%= request.getContextPath()%>/vermascotaservlet" method="GET">
                        <input type="hidden" name="id" value="<%= mascota[0]%>"/>
                        <button type="submit">Ver directo</button>
                    </form>
                </td>
            </tr>
            <% }%>
        </table>
    </body>
</html>
