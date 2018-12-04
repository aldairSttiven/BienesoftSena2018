/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import modelo.aprendizSG;
import modelo.consultas;
import modelo.fichaSG;
import modelo.permisoSG;
import modelo.permiso_historialSG;
import modulo_permisos.Autorizacion;

/**
 *
 * @author Stefany
 */
@WebServlet(name = "servBuscarPermisos_Historial", urlPatterns = {"/servBuscarPermisos_Historial"})
public class servBuscarPermisos_Historial extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //VARIABLES
        String tipObservacion = request.getParameter("observacion");
        String perId = request.getParameter("perId");
        String ventana = request.getParameter("ventana");
        
        
        
        if(ventana != null && ventana.equals("abrir")) {
            //BUSCAR DATOS DEL PERMISO SELECCIONADO
            Autorizacion con = new Autorizacion();
            ArrayList<permiso_historialSG> permiso = new ArrayList<>();
            
            //GuargarID EN LOS GETTER Y SETTER
            permiso_historialSG id = new permiso_historialSG(Integer.parseInt(perId));
            //JOptionPane.showMessageDialog(null,id);
            
            //llamar consulta
            permiso = con.consultaperID(id);
            
            
            permiso_historialSG per = new permiso_historialSG();
            //GUARDAR DATOS QUE TRAJO EL ARREGLO EN LOS GETTER Y SETTER
            per = permiso.get(0);
           
            
            //CONSULTAR NOMBRE DEL APRENDIZ
            consultas con_ap = new consultas();
            ArrayList<aprendizSG> aprendiz = new ArrayList<>();
            
            //Consulta de datos de aprendiz (ID)
            aprendiz = con_ap.consultarAprendizID(per.getPer_Aprendiz_Apr_documento());

            aprendizSG ap = new aprendizSG();
            ap = aprendiz.get(0);
            String aprendizNom = ap.getApr_nombres();
            //JOptionPane.showMessageDialog(null,aprendizNom);

            /*----------------------------------------------------------*/
            //FICHA
            ArrayList<fichaSG> ficha = new ArrayList<>();
            int fichaApr = ap.getApr_ficha_fic_numero();
            ficha= con_ap.consultarFichaApr(fichaApr);
            fichaSG fc= new fichaSG();
            fc = ficha.get(0);
            String fichaNom = fc.getFic_nombrePrograma();
  
            //Impresion de datos del permiso
            out.print(
                    per.getPer_ID()+ //0
                "|"+aprendizNom+ //1
                "|"+per.getPer_Aprendiz_Apr_documento()+ //2
                "|"+per.getPer_tipo()+ //3
                "|"+per.getPer_fecha_salida()+ //4
                "|"+per.getPer_fecha_ingreso()+ //5
                "|"+per.getPer_hora_Salida()+ //6
                "|"+per.getPer_hora_ingreso()+ //7
                "|"+per.getPer_motivo()+ //8
                "|"+per.getPer_estado()+ //9
                "|"+per.getPer_autoriza()+ //10
                "|"+per.getPer_evidenciaAdjunta()+ //11
                "|"+fichaApr+//12
                "|"+fichaNom+//13
                "|"+per.getPer_fecha_salidaReal()+//14
                "|"+per.getPer_hora_salidaReal()+//15
                "|"+per.getPer_fecha_ingresoReal()+//16
                "|"+per.getPer_hora_ingresoReal()+//17
                "|"+per.getPer_observacion_permiso_llegada()//18
            );

        } else { 
            int doc=Integer.parseInt(request.getParameter("documento")); //Documento aprendiz
            out.print(
                "<thead class='thead_t'>"+                    
                    "<tr>"+
                    "<th id='thead_opt'>ID</th>"+
                    "<th id='thead_opt'>DOCUMENTO APRENDIZ</th>"+
                    "<th id='thead_opt'>TIPO DE PERMISO</th>"+
                    "<th id='thead_opt' class='hide-on-med-and-down'>MOTIVO</th>"+
                    "<th id='thead_opt' class='hide-on-med-and-down'>ESTADO</th>"+                
                    "</tr>"+
                "</thead>");

            ArrayList<permiso_historialSG> lisdat = new ArrayList<>();
            consultas con = new consultas();
            lisdat = con.consultarPermiso_Historial(tipObservacion, doc);
            permiso_historialSG x = new permiso_historialSG();
            int i=0;
            for(i=0; i<lisdat.size(); i++){
                x = lisdat.get(i);

                out.print(
                        "<tr>"+
                            "<form action='ServletPermiso' enctype='multipart/form-data' method='post'>"+
                                "<td><input id=id"+i+" class='browser-default input_t' id='' readonly type='number' name='t_numerodocumento' value="+x.getPer_ID()+"></td>"+
                                "<td><input class='hide-on-med-and-down input_t' readonly type='text' name='t_tipo' value="+x.getPer_Aprendiz_Apr_documento()+"></td>"+
                                "<td><input class='hide-on-med-and-down input_t' readonly type='text' name='t_tipo' value="+x.getPer_tipo()+"></td>"+
                                "<td><input class='hide-on-med-and-down input_t' readonly type='text' name='t_moti' value="+x.getPer_motivo()+"></td>"+
                                "<td><input class='hide-on-med-and-down input_t' readonly type='text' name='t_fechsal' value="+x.getPer_estado()+"></td>"+
                                "<td>"+ 
                                "<div  class='btn-ver-permiso-coordinador'>"+                
                                "<img id=p"+i+" class='ver' src='icon_acciones/ver.png' style='padding-left: 15px'/>"+     
                                "</div>"+
                                "</td>"+        
                        "</tr>"
                );
            }
            
            out.print("<script src='js/modal/modalPerHistorial.js'></script>");
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
