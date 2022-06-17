
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Cliente" %>
<%@page import="dbmodels.Gato" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Gatos </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("Clientes");
            ArrayList<Gato> gatos = (ArrayList<Gato>)request.getAttribute("Gatos");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Gatos</h1>
            
            <form action="Gatos" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Dueño*</p>
                        <select name="idCliente" style="min-width: 150px">
                            <%
                                for(int i = 0; i < clientes.size(); i++) {
                            %>
                                <option value="<%= clientes.get(i).id %>"> <%= clientes.get(i).nombre %></option>
                            <%
                                }  
                            %>
                        </select>
                    </div>
                    
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Garfield" required/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Condición/Enfermedad*</p>
                        <input name="enfermedad" placeholder="Sida" required style="min-width: 200px"/>
                    </div>
                        
                    <button type="submit">Agregar</button>
                </div>
            </form>


            <table>
                <tr>
                  <th>Nombre</th>
                  <th>Enfermedad</th>
                  <th>Dueño</th>
                  <th>Atendido</th>
                  <th>Actions</th>
                </tr>


                <%
                    for(int i = 0; i < gatos.size(); i++) {
                %>
                    <tr>
                        <td><%= gatos.get(i).nombre %></td>
                        <td><%= gatos.get(i).enfermedad %></td>
                        <td><%= gatos.get(i).nombreCliente %></td>
                        <td><%= gatos.get(i).atendido ? "Si" : "No" %></td>

                        
                        <td>
                            <a href="?action=EDIT&id=<c:out value='<%= gatos.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE&id=<c:out value='<%= gatos.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>
            </table>
        </div>
    </body>
</html>