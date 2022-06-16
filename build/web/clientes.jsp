
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Cliente" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Clientes </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("Clientes");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Clientes</h1>
            
            <form action="Clientes" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Oscar Martinez" required/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Telefono*</p>
                        <input name="telefono" type="number" placeholder="3323345672" required/>
                    </div>
                    <button type="submit">Agregar</button>
                </div>
            </form>


            <table>
                <tr>
                  <th>Nombre</th>
                  <th>Teléfono</th>
                  <th>Actions</th>
                </tr>


                <%
                    for(int i = 0; i < clientes.size(); i++) {
                %>
                    <tr>
                        <td><%= clientes.get(i).nombre %></td>
                        <td><%= clientes.get(i).telefono %></td>
                        
                        <td>
                            <a href="?action=EDIT&id=<c:out value='<%= clientes.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE&id=<c:out value='<%= clientes.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>
            </table>
        </div>


        
    </body>
</html>