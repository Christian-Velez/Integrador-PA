<%-- 
    Document   : navbar
    Created on : 13 jun 2022, 15:08:20
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<link rel="stylesheet" href="styles/global.css"/>
<link rel="stylesheet" href="styles/navbar.css"/>


<div class="navbar">
    <h2>Veterinarias abc </h2>
    
    
    <div class="profile">
        <img class="profile-img" src="media/dog-bowl.png" />
        
        <div>
            <p> <%= user.nombre %> </p>
            <p class="email"> <%= user.email %> </p>
        </div>
    </div>
    
    
    
    <!--<p> <%= user.id %> </p>-->
    
    
    <div class="nav-items">
                
        <a href="/Integrador/Clientes">
            <div class="navbar-item">
                Clientes
            </div>
        </a> 

        <a href="/Integrador/Perros">
            <div class="navbar-item">
                Perros
            </div>
        </a>

 <!--        <a href="/Integrador/Teams">
            <div class="navbar-item">
                Gatos
            </div>
        </a>
        
        <a href="/Integrador/Teams">
            <div class="navbar-item">
                Aves
            </div>
        </a>
        
        <a href="/Integrador/Teams">
            <div class="navbar-item">
                Otro animal
            </div>
        </a>-->
        
        
        <a href="/Integrador/CloseSession">
            <div class="navbar-item">
                Cerrar sesión
            </div>
        </a>
    </div>


</div>
