<%-- 
    Document   : login.jsp
    Created on : 13 jun 2022, 13:30:45
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <link rel="stylesheet" href="styles/global.css"/>
        <link rel="stylesheet" href="styles/login.css"/>
    </head>
    
    <body>
        <div class="login">

            <form action="Login" method="POST">
                <div class="form-container">
                    <h1>Iniciar sesión</h1>
                    <p>Ingrese todos los datos para iniciar sesión.</p>
                    <hr>
                  
                    <label for="email"><b>Email</b></label>
                    <input type="text" placeholder="example@email.com" name="email" id="email" required>

                    <label for="psw"><b>Contraseña</b></label>
                    <input type="password" placeholder="*****" name="password" id="psw" required>
    
                    <p class="error">${error}</p>
                    
                    <button type="submit" class="registerbtn">Iniciar sesión</button>
                  
                    <div class="container signin">
                        <p>No tienes una cuenta? <a href="/Integrador/Register">Regístrate</a>.</p>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
