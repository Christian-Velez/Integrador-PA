<%-- 
    Document   : perfil
    Created on : 17 jun 2022, 12:16:00
    Author     : C
--%>

<%@page import="dbmodels.Veterinario" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        <link rel="stylesheet" href="styles/profile.css"/>


        <title>Perfil</title>
    </head>
    
    
    
    
     <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            String nombre = user.nombre;
        %>
        
        
        <%@include file="navbar.jsp" %>

        
        <div class="page-content align-center">
            <div>
                <form action="Perfil">
                    <div class="perfil-form align-center">
                        <img src="https://avatars.dicebear.com/api/avataaars/<%=nombre%>.svg" alt="Foto de perfil" class="profile-photo"/>

                        <!-- INFORMACION EXTRA  -->
                        <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                        <input name="action" type="hidden" value="UPDATE" />

                        <!-- FORMULARIO  -->
                        <p>Nombre</p>
                        <input type="text" value="<%=user.nombre%>" name="nombre"/>

                        <p>Contraseña</p>
                        <input type="text" value="<%=user.password%>" name="password"/>

                        <button type="submit" class="save-button">Guardar</button>
                    <div>
                </form>
            </div>
        </div>
    </body>
</html>
