<%-- 
    Created on : 16 jun 2022, 13:09:18
    Author     : C
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dbmodels.Producto" %>
<%@page import="dbmodels.TipoProducto" %>
<%@page import="dbmodels.Proveedor" %>

<%@page import="dbmodels.Veterinario" %>
<%@page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Producto</title>
    </head>
    
    
    <body class="body-page-container">
      
        <!-- Variables enviadas desde el Servlet  -->
        <% 
            Veterinario user = (Veterinario)request.getAttribute("Usuario");
            ArrayList<TipoProducto> tipos = (ArrayList<TipoProducto>)request.getAttribute("Tipos");
            ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>)request.getAttribute("Proveedores");

            Producto producto = (Producto)request.getAttribute("Producto");
        %>
        
        
        <%@include file="navbar.jsp" %>
       
        
    
        <div class="page-content">
            <h1>Editar producto</h1>
            
            <form action="Inventario" method="POST">
                <div style="display:flex; flex-direction: column; gap: 20px; flex-wrap: wrap; max-width: 500px">
                    <!-- INFORMACION EXTRA  -->
                    <input name="idVeterinario" type="hidden" value='<%=user.id%>'  />
                    <input name="idProducto" type="hidden" value='<%=producto.id%>'  />
                    <input name="action" type="hidden" value="UPDATE_PRODUCTO" />
                    
                    <!-- FORMULARIO --> 
                    <div style="display: flex; flex-direction: column;">
                        <p>Nombre*</p>
                        <input name="nombre" placeholder="Paracetamol" required value="<%=producto.nombre%>" />
                    </div>
                    
                    <div style="display: flex; flex-direction: column;">
                        <p>Tipo* (actual: <%=producto.tipoNombre%>)</p>
                        <select name="idTipo" style="min-width: 150px" value="<%=producto.idTipo%>">
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
                        <p>Proveedor* (actual: <%=producto.proveedorNombre%>)</p>
                        <select name="idProveedor" style="min-width: 150px" value="<%=producto.idProveedor%>">
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
                        <input name="cantidad"  placeholder="300" type="number" required  value="<%=producto.cantidad%>"/>
                    </div>
                    
                  
                    
                    <button type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </body>
    
</html>
