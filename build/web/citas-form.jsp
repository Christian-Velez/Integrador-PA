<%-- 
    Created on : 16 jun 2022, 13:09:18
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dbmodels.Cita" %>
<%@page import="dbmodels.Cliente" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cita</title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            Cita cita = (Cita)request.getAttribute("Cita");
            ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("Clientes");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Editar cita</h1>
            
            <form action="Citas" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 500px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idCita" type="hidden" value='<%=cita.id%>'  />
                    <input name="action" type="hidden" value="UPDATE" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Garfield" required value="<%=cita.nombre%>" />
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Cliente* (actual: <%=cita.nombreCliente%>)</p>
                        <select name="idCliente" style="min-width: 150px" value="<%=cita.idCliente%>">
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
                        <p>Fecha*</p>
                        <input name="fecha" type="date" required  value="<%=cita.fecha%>"/>
                    </div>
                    
                    
                    
                  
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
    
</html>
