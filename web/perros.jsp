
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Cliente" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        
        <title> Perros </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("Clientes");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Perros</h1>
            
            
            <form action="Perros" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">

                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  /> 

                    <div style="display: flex; flex-direction: column;">
                        <p>Dueño*</p>
                        
                        <select
                            name="idCliente"
                            style="min-width: 200px"
                        >
                            <%
                                for(int i = 0; i < clientes.size(); i++) {
                            %>
                                <option value="<%=clientes.get(i).id%>"> <%= clientes.get(i).nombre %> </option>
                            <%
                                }  
                            %>
                        </select>
                    </div>
                    
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Max" required/>
                    </div>

                    


                    <button type="submit">Agregar</button>
                </div>
            </form>

        </div>

        
    </body>
</html>