<%-- 
    Created on : 16 jun 2022, 13:09:18
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dbmodels.Proveedor" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Proveedor</title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            Proveedor proveedor = (Proveedor)request.getAttribute("Proveedor");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Editar proveedor</h1>
            
            <form action="Proveedores" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 500px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idProveedor" type="hidden" value='<%=proveedor.id%>'  />
                    <input name="action" type="hidden" value="UPDATE" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Laboratorios Pfizer" required value="<%=proveedor.nombre%>" />
                    </div>
                   
                    <div style="display: flex; flex-direction: column;">
                        <p>Dirección*</p>
                        <input name="direccion" placeholder="Av. Vallarta #33" required value="<%=proveedor.direccion%>" />
                    </div>
                    
                  
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
</html>
