<%-- 
    Created on : 16 jun 2022, 13:09:18
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dbmodels.Empleado" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Empleado</title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            Empleado empleado = (Empleado)request.getAttribute("Empleado");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Editar empleado</h1>
            
            <form action="Empleados" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 500px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idEmpleado" type="hidden" value='<%=empleado.id%>'  />
                    <input name="action" type="hidden" value="UPDATE" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Adrian Marcelo" required value="<%=empleado.nombre%>" />
                    </div>
                   
                    <div style="display: flex; flex-direction: column;">
                        <p>Edad*</p>
                        <input name="edad" type="number" placeholder="18" required value="<%=empleado.edad%>" />
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Pago*</p>
                        <input name="pago" type="number" placeholder="2500" required value="<%=empleado.pago%>" />
                    </div>
                    
                    
                  
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
    
</html>
