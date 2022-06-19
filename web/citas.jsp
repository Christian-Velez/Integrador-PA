
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Cliente" %>
<%@page import="dbmodels.Cita" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Citas </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("Clientes");
            ArrayList<Cita> citas = (ArrayList<Cita>)request.getAttribute("Citas");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Citas</h1>
            
            <form action="Citas" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Cliente*</p>
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
                        <input name="nombre" placeholder="Cita para revisión" required/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Fecha*</p>
                        <input name="fecha" type="date" required style="min-width: 200px"/>
                    </div>
                        
                    <button type="submit">Agregar</button>
                </div>
            </form>


            <table>
                <tr>
                  <th>Nombre</th>
                  <th>Cliente</th>
                  <th>Fecha</th>
                  <th>Actions</th>
                </tr>


                <%
                    for(int i = 0; i < citas.size(); i++) {
                %>
                    <tr>
                        <td><%= citas.get(i).nombre %></td>
                        <td><%= citas.get(i).nombreCliente %></td>
                        <td><%= citas.get(i).fecha %></td>

                        
                        <td>
                            <a href="?action=EDIT&id=<c:out value='<%= citas.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE&id=<c:out value='<%= citas.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>
            </table>
        </div>
    </body>
</html>