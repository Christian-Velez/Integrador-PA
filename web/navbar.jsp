<%-- 
    Document   : navbar
    Created on : 13 jun 2022, 15:08:20
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<link rel="stylesheet" href="styles/global.css"/>
<link rel="stylesheet" href="styles/navbar.css"/>


<div class="navbar">
    <h2>Veterinarias ABC</h2>
    
    
    
    <a href="/Integrador/Perfil">
        <div class="profile">
            <img src="https://avatars.dicebear.com/api/avataaars/<%=user.nombre%>.svg" alt="Foto de perfil" class="profile-img"/>

            <div>
                <p> <%= user.nombre %> </p>
                <p class="email"> <%= user.email %> </p>
            </div>
        </div>
    </a>

    
    <div class="nav-items">
        <a href="/Integrador/Cartera">
            <div class="navbar-item">
                Mi cartera
            </div>
        </a>     
        
        <a href="/Integrador/Clientes">
            <div class="navbar-item">
                Clientes
            </div>
        </a>
        
        <a href="/Integrador/Citas">
            <div class="navbar-item">
                Citas
            </div>
        </a>
        
        <a href="/Integrador/Perros">
            <div class="navbar-item">
                Perros
            </div>
        </a>
        
        <a href="/Integrador/Gatos">
            <div class="navbar-item">
                Gatos
            </div>
        </a>
        
        <hr>
        
        <a href="/Integrador/Empleados">
            <div class="navbar-item">
                Empleados
            </div>
        </a>
        
        <a href="/Integrador/Inventario">
            <div class="navbar-item">
                Inventario
            </div>
        </a>
        <a href="/Integrador/Proveedores">
            <div class="navbar-item">
                Proveedores
            </div>
        </a>
        
       

        
        <hr>
        <a href="/Integrador/CloseSession">
            <div class="navbar-item">
                Cerrar sesión
            </div>
        </a>
    </div>


</div>
