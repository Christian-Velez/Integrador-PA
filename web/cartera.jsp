<%-- 
    Document   : cartera
    Created on : 16 jun 2022, 14:13:59
    Author     : C
--%>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Movimiento" %>
<%@page import="java.util.ArrayList" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>        
        <link rel="stylesheet" href="styles/cartera.css"/>

        <title>Mi Cartera</title>
    </head>
   
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>)request.getAttribute("Movimientos");
            int balanceGeneral = (Integer)request.getAttribute("BalanceGeneral");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Movimientos</h1>
            
            <form action="Cartera" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Concepto*</p>
                        <input name="concepto" placeholder="Cita médica" required/>
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
                        <input name="cantidad" placeholder="500" type="number" required/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Fecha*</p>
                        <input name="fecha" type="date"  required style="min-width: 200px"/>
                    </div>
                    
                    
                    
                    <button type="submit">Agregar</button>
                </div>
            </form>


            <div class="balance-general">
                <h3>Balance general</h3>

                <div 
                    class="dot-balance" 
                    style="<%= (balanceGeneral >= 0) ? "border: 9px solid green" : "border: 9px solid red"  %>"
                >
                    $ <%=balanceGeneral%>
                
                
                </div>
            </div>
            
                    
            <table>
                <tr>
                  <th>Concepto</th>
                  <th>Cantidad</th>
                  <th>Fecha</th>
                  <th>Actions</th>
                </tr>

                
                <%
                    for(int i = 0; i < movimientos.size(); i++) {
                %>
                    <tr>
                        <td><%= movimientos.get(i).concepto %></td>
                        <td>$ <%= movimientos.get(i).cantidad %></td>
                        <td><%= movimientos.get(i).fecha %></td>

                        
                        <td>
                            <a href="?action=EDIT&id=<c:out value='<%= movimientos.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE&id=<c:out value='<%= movimientos.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>

            </table>
        </div>
    </body>
</html>
