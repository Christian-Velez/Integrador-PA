<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Movimiento" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Editar movimiento </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            Movimiento movimiento = (Movimiento)request.getAttribute("Movimiento");
        %>
        
        
        <%@include file="navbar.jsp" %>
        
        <div class="page-content">
            <h1>Editar movimiento</h1>
            
            <form action="Cartera" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 500px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idMovimiento" type="hidden" value='<%=movimiento.id%>'  />
                    <input name="action" type="hidden" value="UPDATE" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Concepto*</p>
                        <input name="concepto" placeholder="Pago de jaulas" required value="<%=movimiento.concepto%>" />
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Tipo*</p>
                        <select name="tipo" style="min-width:200px">
                            <option value="INGRESO">Ingreso</option>
                            <option value="GASTO">Gasto</option>
                        </select>
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Cantidad*</p>
                        <input name="cantidad" placeholder="500" type="number" required value="<%= Math.abs(movimiento.cantidad) %>"/>
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Fecha*</p>
                        <input name="fecha" type="date"  required style="min-width: 200px" value="<%=movimiento.fecha%>"/>
                    </div>
                    
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
</html>
