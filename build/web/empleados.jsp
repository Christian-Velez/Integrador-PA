
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Empleado" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Empleados </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Empleado> empleados = (ArrayList<Empleado>)request.getAttribute("Empleados");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Empleados</h1>
            
            <form action="Empleados" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Daniel Martinez" required/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Edad*</p>
                        <input name="edad" type="number" placeholder="18" required style="min-width: 200px"/>
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Pago*</p>
                        <input name="pago" type="number" placeholder="2000" required style="min-width: 200px"/>
                    </div>
                        
                    <button type="submit">Agregar</button>
                </div>
            </form>


            <table>
                <tr>
                  <th>Nombre</th>
                  <th>Edad</th>
                  <th>Pago</th>
                  <th>Actions</th>
                </tr>


                <%
                    for(int i = 0; i < empleados.size(); i++) {
                %>
                    <tr>
                        <td><%= empleados.get(i).nombre %></td>
                        <td><%= empleados.get(i).edad %></td>
                        <td><%= empleados.get(i).pago %></td>

                        
                        <td>
                            <a href="?action=EDIT&id=<c:out value='<%= empleados.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE&id=<c:out value='<%= empleados.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>
            </table>
        </div>
    </body>
</html>