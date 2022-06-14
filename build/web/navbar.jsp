<%-- 
    Document   : navbar
    Created on : 13 jun 2022, 15:08:20
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<link rel="stylesheet" href="styles/global.css"/>
<link rel="stylesheet" href="styles/navbar.css"/>


<div class="navbar">
    <h2>Project Manager </h2>
    
    
    <div class="profile">
        <img class="profile-img" src="media/profile.jpg" />
        
        <div>
            <p> <%= user.name %> </p>
            <p class="email"> <%= user.email %> </p>
        </div>
    </div>
    
    
    
    <!--<p> <%= user.id %> </p>-->
    
    
    <div class="nav-items">
                
        <a href="/Integrador/Projects">
            <div class="navbar-item">
                Proyectos
            </div>
        </a>

        <a href="/Integrador/Friends">
            <div class="navbar-item">
                Amigos
            </div>
        </a>

        <a href="/Integrador/Teams">
            <div class="navbar-item">
                Equipos de trabajo
            </div>
        </a>
        
        
        <a href="/Integrador/CloseSession">
            <div class="navbar-item">
                Cerrar sesión
            </div>
        </a>
    </div>


</div>
