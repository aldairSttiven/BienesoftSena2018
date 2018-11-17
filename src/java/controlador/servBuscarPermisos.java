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
import modelo.consultas;
import modelo.permisoSG;

@WebServlet(name = "servBuscarPermisos", urlPatterns = {"/servBuscarPermisos"})
public class servBuscarPermisos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String tipoPermiso = request.getParameter("tipo");
        String buscar = request.getParameter("ventana");
        
        if(buscar != null && buscar.equals("ventana")) {
            JOptionPane.showMessageDialog(null,"JELOU");
            
        } else { 
            out.print("<thead id='thead' class='thead>"+
                "<th></th>"+
                "<th id='th_thead'>ID</th>"+
                "<th id='th_thead'>ID</th>"+
                "<th id='th_thead'>TIPO DE PERMISO</th>"+
                "<th id='th_thead'>MOTIVO</th>"+
                "<th id='th_thead'>FECHA DE SALIDA</th>"+
                "<th id='th_thead'>FECHA DE INGRESO</th>"+
                "<th id='th_thead'>ACCIONES</th>"+

            "</thead>");

            ArrayList<permisoSG> lisdat = new ArrayList<>();
            consultas con = new consultas();
            lisdat = con.consultarPermiso(tipoPermiso, "");
            permisoSG x = new permisoSG();
            int i=0;
            for(i=0; i<lisdat.size(); i++){
                x = lisdat.get(i);

        out.print(  
                    "<tr>"+
                        "<form action='ServletPermiso' enctype='multipart/form-data' method='post'>"+
                            "<td><input id=id"+i+" class='browser-default' readonly type='number' name='t_numerodocumento' value="+x.getPer_ID()+"></td>"+
                            "<td><input class='browser-default' readonly type='text' name='t_tipo' value="+x.getPer_tipo()+"></td>"+
                            "<td><input class='browser-default' readonly type='text' name='t_moti' value="+x.getPer_motivo()+"></td>"+
                            "<td><input class='browser-default' readonly type='date' name='t_fechsal' value="+x.getPer_fecha_salida()+"></td>"+
                            "<td><input class='browser-default' readonly type='date' name='t_fechingre' value="+x.getPer_fecha_ingreso()+"></td>"+
                            "<td>"+ 
                            "<div  class='btn-ver-permiso-coordinador'>"+                
                            "<img id=p"+i+" class='ver' src='icon_acciones/ver.png' style='padding-left: 15px'/>"+     
                            "</div>"+
                            "</td>"+        
                    "</tr>");
            }


            out.print("<script src='js/modal/modal.js'></script>");
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