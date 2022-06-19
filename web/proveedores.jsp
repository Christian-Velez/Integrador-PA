
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Proveedor" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Proveedores </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>)request.getAttribute("Proveedores");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Proveedores</h1>
            
            <form action="Proveedores" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Instrumentos Lic" required style="min-width: 350px"/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Dirección*</p>
                        <input name="direccion"  placeholder="Av. Vallarta #32" required style="min-width: 400px"/>
                    </div>
                    
                        
                    <button type="submit">Agregar</button>
                </div>
            </form>


            <table>
                <tr>
                  <th>Nombre</th>
                  <th>Dirección</th>
                  <th>Actions</th>
                </tr>


                <%
                    for(int i = 0; i < proveedores.size(); i++) {
                %>
                    <tr>
                        <td><%= proveedores.get(i).nombre %></td>
                        <td><%= proveedores.get(i).direccion %></td>
                        
                        <td>
                            <a href="?action=EDIT&id=<c:out value='<%= proveedores.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE&id=<c:out value='<%= proveedores.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>
            </table>
        </div>
    </body>
</html>