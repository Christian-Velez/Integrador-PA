<%-- 
    Created on : 16 jun 2022, 13:09:18
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dbmodels.Gato" %>
<%@page import="dbmodels.Cliente" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Gato</title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            Gato gato = (Gato)request.getAttribute("Gato");
            ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("Clientes");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Editar gato</h1>
            
            <form action="Gatos" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 500px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idGato" type="hidden" value='<%=gato.id%>'  />
                    <input name="action" type="hidden" value="UPDATE" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Garfield" required value="<%=gato.nombre%>" />
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Enfermedad*</p>
                        <input name="enfermedad"  placeholder="Sida" required  value="<%=gato.enfermedad%>"/>
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Dueño* (actual: <%=gato.nombreCliente%>)</p>
                        <select name="idCliente" style="min-width: 150px" value="<%=gato.idCliente%>">
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
                        <p>Atendido* (actual: <%=gato.atendido ? "Si" : "No"%>)</p>
                        
                        <select name="atendido" >
                            <option value="Si">Si</option> 
                            <option value="No">No</option>
                        </select>
                    </div>
                    
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
    
</html>
