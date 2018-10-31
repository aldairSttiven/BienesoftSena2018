
<%@page import="modulo_permisos.semana"%>
<%@page import="modelo.aprendizSG"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <!--Let browser know website is optimized for mobile-->
            <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
            
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

            <!--importando imagen y texto en pestaña-->
            <link rel="shortcut icon" type="image/x-icon" href="materialize/icons/black/ver_lista.png" />
            <title>BieneSoft | Permiso</title>

            <!--estilo body-->
            <link type="text/css" rel="stylesheet" href="css/body.css" media="screen,projection"/>
            
            <!--estilo encabezado-->
            <link type="text/css" rel="stylesheet" href="css/encabezado.css"  media="screen,projection"/>

            <!--estilo modulo busqueda-->
            <link type="text/css" rel="stylesheet" href="css/modulo_busqueda.css" media="screen,projection"/>
            
            <!--estilo de la tabla--> 
            <link type="text/css" rel="stylesheet" href="css/tabla.css"  media="screen,projection"/>
            
            <!--estilo de la botones--> 
            <link type="text/css" rel="stylesheet" href="css/buttons.css"  media="screen,projection"/>
            
            <!--estilo de la registro--> 
            <link type="text/css" rel="stylesheet" href="css/formulario.css"  media="screen,projection"/>
            
            <!--estilo footer-->
            <link type="text/css" rel="stylesheet" href="css/footer.css"  media="screen,projection"/>

            <!--estilo materialize.css-->
            <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css"  media="screen,projection"/>    
            
            <!--importando--->
            <script src="js/jquery.js"></script>
</head>


    <body>
        
       <!-- 
        <div>
            <h1>formulario Permiso aprendiz</h1>
           
            <form action="ServletPermiso" enctype="multipart/form-data" method="post">
                <td><input type="number" name="f_numerodocumento" placeholder="documento del aprendiz"></td>
                
                
                <select name="select_tipo">  
                    <option>elige el tipo de permiso</option>
                    <option>semana</option>
                    <option>fsemana</option>
                </select>
                
                <td><input type="date" name="f_fechsal" placeholder="Fecha salida"></td>
                <td><input type="date" name="f_fechingre" placeholder="Fecha ingreso"><td>
                <td><input type="time" name="f_horasal" placeholder="Hora salida"></td>
                <td><input type="time" name="f_horaingre" placeholder="Hora ingreso"></td>
                <td><input type="text" name="f_moti" placeholder="Motivo"></td>
                <td><input type="file" name="f_evidenciaAdjunta" accept="imagen/*"><br><br></td>
                
                
                <input type="submit" name="btn-guardar" value="Guardar">
                
               
                    
                <div>
                    <input type="submit" name="btn-permiso" value="btn_permiso">
                </div>
                    
            </form>
                
           
        </div>
      -->          
                
                <!--MODULO PARA LLAMAR METODO DE UNA CLASE-->
                
                <!--<form action="ServletPrueba" method="post">
                    
                    <div>
                        <input type="submit" name="btn-permiso" value="btn_permiso">
                    </div>
                    
                </form>-->
                
                <!--*******************************OBJETO NAV RESPONSIVE***************************-->
        
        <!--nav principal--->
        
        <nav class="nav-wrapper" id="nav_principal">
            <div class="container">

                <a id="logo_encab"  href="#" class="brand-logo hide-on-small-only black-text">Biene-Soft</a>
                
                <a href="#" data-target="menu-responsive" class="sidenav-trigger"> 
                    <i>
                        <img style="width: 25px; margin-top: 10px;" src="materialize/icons/white/menu.png" />
                    </i>
                    
                        <ul class="right hide-on-med-and-down">
                        <lu id="li" title="cerrar cesion"><a href=""><img style="width: 25px; margin-top: 10px;" src="materialize/icons/white/menu.png" /></a></lu>
                        </ul>
                </a>
                
                    <a href="#" data-target="menu-responsive" class="sidenav-trigger"> 
                        <ul  class="right hide-on-med-and-down">
                            <lu id="li" title="cerrar cesion"><a href=""><img style="width: 25px; margin-top: 10px;" src="materialize/icons/white/menu.png" /></a></lu>
                        </ul>
                    </a>
                
                    <a href="#" data-target="menu-responsive" class="sidenav-trigger"> 
                        <ul  class="right hide-on-med-and-down">
                            <lu id="li" title="cerrar cesion"><a href=""><img style="width: 25px; margin-top: 10px;" src="materialize/icons/white/menu.png" /></a></lu>
                        </ul>
                    </a>
            </div>  
        </nav> 
        
        <!--menu response-->
        
        <ul class="sidenav" id="menu-responsive">
                    
            <li><a href="#"> Aprendizes</a></li>

            <li><a href="#"> Alimentacion</a></li>

            <li><a href="#"> Permisos</a></li>        
        </ul>
        
        <!--------------------------------------------------->
        <!--nav secundario (opciones)--->
        
        <nav id="nav_menu" class="hide-on-med-and-down" >
                        <a href="f agencia.jsp">
                            <input id="opc_menu_2" type="submit" value="Aprendices"/>
                        </a>
                    
                    
                        <a href="f aspirante.jsp">
                            <input id="opc_menu_2" type="submit" value="Alimentacion"/>
                        </a>
            
                        <a href="f agencia.jsp">
                            <input id="opc_menu_2" type="submit" value="Aprendices"/>
                        </a>     
        </nav>
        <!--**************************************************************************************************-->

<!--**************************************************************************************************-->
        
        <!--MODULO INFORMACION USUARIO-->


        <label id="user_text" class="user_text hide-on-med-and-down">Aprendiz</label> 
            <%
                HttpSession rnombre=request.getSession();
                String nom=(String)rnombre.getAttribute("datico");
             %>
        <p id="user_text" class="user_text hide-on-med-and-down"><%=nom%></p>


 <!--*********************************************************-->    
        
        <!--------FORMULARIO------------------------------------->

        <div class="container" style="margin-bottom:15px;">       

                    <h1 id="title_container">REGISTRO PERMISO</h1>

                           <form action="ServletAlimentacion"> 
                                        <div class="row" id="container_form_large"  style="height: 420px;">
                                                           <div class="input-field col s12 m6 l6">
                                                               <p id="input_msg">Documento del aprendiz</p>
                                                               <br>
                                                               <input id="input_txt" type="number" name="f_numerodocumento" placeholder="documento del aprendiz">
                                                           </div>   

                                                                <div class="row">
                                                                    <div class="input-field col s12 l6 m6">
                                                                        <select name="select_tipo">  
                                                                             <option>elige el tipo de permiso</option>
                                                                             <option>semana</option>
                                                                             <option>fsemana</option>
                                                                         </select>
                                                                    </div>
                                                                </div>
                                                           
                                            
                                                            <div class="input-field col s12 l6 m6">
                                                               <p id="input_msg">Fecha de salida</p>
                                                               <br>
                                                               <input id="input_txt" type="date" name="f_fechsal" placeholder="Fecha salida">
                                                           </div>
                                            
                                                            <div class="input-field col s12 l6 m6">
                                                               <p id="input_msg">Fecha de ingreso</p>
                                                               <br>
                                                               <input id="input_txt" type="date" name="f_fechingre" placeholder="Fecha ingreso">
                                                           </div>
                                                           
                                                           <div class="input-field col s12 l6 m6">
                                                               <p id="input_msg">Hora de salida</p>
                                                               <br>
                                                               <input id="input_txt" type="time" name="f_horasal" placeholder="Hora salida">
                                                           </div>
                                            
                                                           <div class="input-field col s12 l6 m6">
                                                               <p id="input_msg">Hora de ingreso</p>
                                                               <br>
                                                               <input id="input_txt" type="time" name="f_horaingre" placeholder="Hora ingreso">
                                                           </div>
                                            
                                                           <div class="input-field col s12 l6 m6">
                                                               <p id="input_msg">Motivo</p>
                                                               <br>
                                                               <input id="input_txt" type="text" name="f_moti" placeholder="Motivo">
                                                           </div>
                                            
                                                           <div class="input-field col s12 l6 m6">
                                                               <p id="input_msg">Evidencia</p>
                                                               <br>
                                                               <input type="file" name="f_evidenciaAdjunta" accept="imagen/*">
                                                           </div>
                                       </div>
                               
                               
<!----------------------------***********ESTILOS DE LOS BOTONES EN (buttons.css)*******************************---------------------------------------------------------->

                                       <div id="div_buttom" class="div_buttom col  s12">
                                           
                                               <div id="btn_container" class="btn_container row ">                                               
                                                            <button type="submit" name="btn-guardar" id="btn_action_guardar" class="btn_action_guardar l12  m12 s12">
                                                                <p id="txt_buttom" class="txt_buttom">
                                                                    Guardar
                                                                    <img id="img_buttom" class="img_buttom" src="icons/actualizar.png" />
                                                                </p>   
                                                            </button>  

                                                             <button type="submit" name="" id="btn_action_eliminar" class="btn_action_eliminar l12  m12 s12">
                                                                <p id="txt_buttom" class="txt_buttom">
                                                                    Cancelar
                                                                    <img id="img_buttom" class="img_buttom" src="icons/eliminar.png" />
                                                                </p>   
                                                            </button>                                     
                                               </div>
                                           
                                         </div> 
<!----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
                           </form>
               </div>   
        <!--------------------------------------------------------------------------------------------------->
        
         <!--***************FOOTER*********************-->
        <footer class="page-footer" id="footer_form">

                    <div class="container white-text center">
                    © 2018 Biene-Soft
                    <br>
                    Todos los derechos reservados
                    </div>
                    
        </footer>
        <!------------------------------------------------------------------------------------------------------>
        
                <!--importando funciones para los objetos-->    
            <script>
                document.addEventListener('DOMContentLoaded', function() {
                    M.AutoInit();
                });
            </script>  
        
            <!--JavaScript at end of body for optimized loading-->
            <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
                
    </body>
</html>