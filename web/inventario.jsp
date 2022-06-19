


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dbmodels.Veterinario" %>
<%@page import="dbmodels.Producto" %>
<%@page import="dbmodels.Proveedor" %>
<%@page import="dbmodels.TipoProducto" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/global.css"/>
        <link rel="stylesheet" href="styles/inventario.css"/>
        
        <title> Inventario </title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<Producto> productos = (ArrayList<Producto>)request.getAttribute("Productos");
            ArrayList<TipoProducto> tipos = (ArrayList<TipoProducto>)request.getAttribute("Tipos");
            ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>)request.getAttribute("Proveedores");
        %>
        
        
        <%@include file="navbar.jsp" %>

    
        <div class="page-content">
            <h1>Inventario</h1>
            
            <!-- TIPOS DE PRODUCTO -->
            <a href="#tipos">
                <button style="width: 200px">Tipos de producto</button>
            </a>
            
            <div id="tipos" class="modal">
              <div class="modal-contenido">
                    <div class="title-modal">
                        <h2>Tipos de producto</h2>
                        <a href="#" class="close-modal">X</a>
                    </div>
                  
                    <form action="Inventario">
                        <!-- INFORMACION EXTRA  -->
                            <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                            <input name="action" type="hidden" value="ADD_TIPO" />
                            
                        <!-- FORMULARIO -->
                        <div style="display: flex; width: 100%; align-items: center; gap: 20px; flex-wrap: wrap;">
                            <div style="display: flex; flex-direction: column; width: 300px">
                                <p>Nombre*</p>
                                <input name="nombre" placeholder="Analgésico" required/>
                            </div>

                            <button type="submit">Agregar</button>
                        </div>
                    </form>
                     
                    <table>
                        <tr>
                          <th>Nombre</th>
                          <th>Actions</th>
                        </tr>


                        <%
                            for(int i = 0; i < tipos.size(); i++) {
                        %>
                            <tr>
                                <td>
                                    <form action="Inventario">
                                        <!-- INFORMACION EXTRA  -->
                                        <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                                        <input name="idTipo" type="hidden" value='<%=tipos.get(i).id%>'  />
                                        <input name="action" type="hidden" value="UPDATE_TIPO" />
                                        
                                        <input name="nombre" value="<%= tipos.get(i).nombre %>"/>
                                    </form>
                                
                                </td>

                                <td>
                                    <a href="?action=DELETE_TIPO&id=<c:out value='<%= tipos.get(i).id%>' />">Delete</a>
                                </td>
                            </tr>
                        <%
                            }  
                        %>
                    </table>
                </div>  
            </div>
            
            
            
            
            
            
            <!-- PRODUCTOS -->
            <form action="Inventario" method="POST">
                <div style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap;">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="action" type="hidden" value="ADD_PRODUCTO" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Paracetamol" required/>
                    </div>

                    <div style="display: flex; flex-direction: column;">
                        <p>Tipo*</p>
                        <select name="idTipo" style="min-width: 150px">
                            <%
                                for(int i = 0; i < tipos.size(); i++) {
                            %>
                                <option value="<%= tipos.get(i).id %>"> <%= tipos.get(i).nombre %></option>
                            <%
                                }  
                            %>
                        </select>
                    </div>
                        
                    <div style="display: flex; flex-direction: column;">
                        <p>Proveedor*</p>
                        <select name="idProveedor" style="min-width: 150px">
                            <%
                                for(int i = 0; i < proveedores.size(); i++) {
                            %>
                                <option value="<%= proveedores.get(i).id %>"> <%= proveedores.get(i).nombre %></option>
                            <%
                                }  
                            %>
                        </select>
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Cantidad*</p>
                        <input name="cantidad" type="number" placeholder="300" required/>
                    </div>
                        
                    <button type="submit">Agregar producto</button>
                </div>
            </form>
                        
            <table>
                <tr>
                  <th>Nombre</th>
                  <th>Tipo</th>
                  <th>Proveedor</th>
                  <th>Cantidad</th>
                  <th>Actions</th>
                </tr>


                <%
                    for(int i = 0; i < productos.size(); i++) {
                %>
                    <tr>
                        <td><%= productos.get(i).nombre %></td>
                        <td><%= productos.get(i).tipoNombre %></td>
                        <td><%= productos.get(i).proveedorNombre %></td>
                        <td><%= productos.get(i).cantidad  %></td>


                        <td>
                            <a href="?action=EDIT_PRODUCTO&id=<c:out value='<%= productos.get(i).id%>' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                            <a href="?action=DELETE_PRODUCTO&id=<c:out value='<%= productos.get(i).id%>' />">Delete</a>
                        </td>
                    </tr>
                <%
                    }  
                %>
            </table>
        </div>
            
            
    </body>
</html>