

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
       
        <link type="text/css" rel="stylesheet" href="styles/global.css"/>
        <link type="text/css" rel="stylesheet" href="styles/register.css"/>

    </head>
    
    
    <body>
        <div class="register">

            <form action="Register" method="POST">
                <div class="form-container">
                    <h1>Registro</h1>
                    <p>Ingrese todos los datos para crear una cuenta.</p>
                    <hr>

                    <label for="name"><b>Nombre</b></label>
                    <input type="text" placeholder="e.g: Daniel" name="name" id="name" required>
                  
                  
                    <label for="email"><b>Email</b></label>
                    <input type="text" placeholder="example@email.com" name="email" id="email" required>

                    <label for="psw"><b>Contraseña</b></label>
                    <input type="password" placeholder="*****" name="password" id="psw" required>

                  
                    <button type="submit" class="registerbtn">Registrarse</button>
                  
                    <div class="container signin">
                        <p>Ya tiene una cuenta? <a href="/Integrador/Login">Inicie sesión</a>.</p>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
