<%-- 
    Document   : index
    Created on : 13 jun 2022, 11:33:17
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" href="styles/index.css"/>
<link rel="stylesheet" href="styles/global.css"/>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Manager</title>
    </head>
    <body>
        
        
        <div class="index-container">
            <div class="login-container">
              <h1>Bienvenido a Project Manager</h1>
                <p> La aplicación para administrar tus proyectos personales </p>
                <img src="media/login.png"/>
            </div>
            
            
            <div class="login-container">
                <button onclick="goToLogin()">Iniciar sesión</button>
                <p>No tienes una cuenta? <a href="./Register">Crea una</a> </p>
            </div>

        </div>
    </body>
    
    <script>
        
        function goToLogin() {
            window.location.href = "./Login"
        }
        
        
    </script>
</html>
