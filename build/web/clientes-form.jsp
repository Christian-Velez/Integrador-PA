<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Cliente" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Editar cliente </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            Cliente cliente = (Cliente)request.getAttribute("Cliente");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Editar cliente</h1>
            
            <form action="Clientes" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 300px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idCliente" type="hidden" value='<%=cliente.id%>'  />
                    <input name="action" type="hidden" value="UPDATE" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Oscar Martinez" required value="<%=cliente.nombre%>" />
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Telefono*</p>
                        <input name="telefono" type="number" placeholder="3323345672" required  value="<%=cliente.telefono%>"/>
                    </div>
                    
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
</html>
