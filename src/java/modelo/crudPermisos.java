
package modelo;

import controlador.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modulo_permisos.Autorizacion;


public class crudPermisos {
 
    conexion con=new conexion();
    Connection cnn=con.conexiondb();
    PreparedStatement ps=null;
    ResultSet rs=null;
    Statement stmt = null;
    
    //GUARDAR 
    public void guardar_permiso(permisoSG ing){
        try{          
            ps=cnn.prepareStatement("INSERT INTO permiso VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");   
            ps.setInt(1, ing.getPer_ID());
            ps.setInt(2, ing.getPer_Aprendiz_Apr_documento());
            ps.setString(3, ing.getPer_tipo());
            ps.setString(4, ing.getPer_fecha_salida());
            ps.setString(5, ing.getPer_fecha_ingreso());
            ps.setString(6, ing.getPer_hora_Salida());
            ps.setString(7, ing.getPer_hora_ingreso());
            ps.setString(8, null);
            ps.setString(9, null);
            ps.setString(10,null);
            ps.setString(11,null);
            ps.setString(12, ing.getPer_observacion_permiso_llegada());
            ps.setString(13, ing.getPer_motivo());
            ps.setString(14, ing.getPer_estado());
            ps.setString(15, ing.getPer_autoriza());
            ps.setString(16, ing.getPer_evidenciaAdjunta());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro realizado");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"no se pudo desde crud permiso " +e );
        }
    }
    
    
    //ACTUALIZAR TODOS LOS DATOS
    public int actualizar_permiso(permisoSG ing){
        try {
            ps=cnn.prepareStatement("UPDATE permiso SET per_Aprendiz_Apr_documento= '"+ing.getPer_Aprendiz_Apr_documento()+"', per_tipo= '"+ing.getPer_tipo()+"', per_fecha_salida= '"+ing.getPer_fecha_salida()+"', per_fecha_ingreso= '"+ing.getPer_fecha_ingreso()+"', per_hora_Salida= '"+ing.getPer_hora_Salida()+"', per_hora_ingreso= '"+ing.getPer_hora_ingreso()+"', per_motivo= '"+ing.getPer_motivo()+"', per_estado= '"+ing.getPer_estado()+"', per_autoriza= '"+ing.getPer_autoriza()+"', per_evidenciaAdjunta= '"+ing.getPer_evidenciaAdjunta()+"' WHERE   per_Aprendiz_Apr_documento= '"+ing.getPer_Aprendiz_Apr_documento()+"' ");
            ps.executeUpdate();           
            JOptionPane.showMessageDialog(null,"Registro actualizado");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no se pudo actualizar desde crud permiso " +e);
        }
        return 0;       
    } 
    
    
    //cambio del estado autorizado o denegado (modulo coordinador)
    public int actualizar_permisoEstado(permisoSG inge){
        try {
            ps=cnn.prepareStatement("UPDATE permiso SET per_estado='"+inge.getPer_estado()+"', per_autoriza='"+inge.getPer_autoriza()+"' "
                                    + "WHERE  per_ID='"+inge.getPer_ID()+"' ");
            ps.executeUpdate();
            
            crudPermisos crud = new crudPermisos();
            String resultadoEst = null;
            crud.procedimientos_historial(inge, resultadoEst);

            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error" +e);
        }
        return 0;        
    }
    
    //cambio del estado imcompleto (modulo seguridad)
    public int actualizar_estado_imcompleto_permiso(int DatoID, permisoSG ing){
        int x=0;
            try{
                ps=cnn.prepareStatement("UPDATE permiso SET per_estado='Incompleto' , per_observacion_llegada='Tarde' WHERE per_ID='"+DatoID+"' ");
                ps.executeUpdate();
                
                crudPermisos crud = new crudPermisos();
                crud.consultarPermiso(ing);
                
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"no entro " +e);
            }
  
        return x;   
    }
    
    //cambio del estado completo (modulo seguridad)
    public int actualizar_estado_completo_permiso(int DatoID, permisoSG ing){
        int x=0;
        
        JOptionPane.showMessageDialog(null, "entro al metodo que actualiza el estado");
            try{
                ps=cnn.prepareStatement("UPDATE permiso SET per_estado='Completo', per_observacion_llegada='Temprano' WHERE per_ID='"+DatoID+"' ");
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "se actualizo el estado a completo");
                
                crudPermisos crud = new crudPermisos();
                crud.consultarPermiso(ing);
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"no entro " +e);
            }
        return x;   
    }
    
    
    //PROCEDIMIENTO ALMACENADOS PARA ELIMINAR Y ACTIVAR DISPARADOR
    public int procedimientos_historial(permisoSG setget, String resultadoEst){
        int x=0;
        
//        JOptionPane.showMessageDialog(null, "se envio Incompleto---->   "+ resultadoEst);
            JOptionPane.showMessageDialog(null, "se envio al procedimiento---->   "+ resultadoEst);


         //si el estado es completo
        try {    
            JOptionPane.showMessageDialog(null, "entro al try del precedimiento Completo---->   "+ resultadoEst);
            if(resultadoEst.equals("Completo")){
                    JOptionPane.showMessageDialog(null,"se envio aquiii: "+resultadoEst);
                    ps=cnn.prepareCall("call tipopermiso('Completo')");
                    ps.executeUpdate();               
                    JOptionPane.showMessageDialog(null,"Call estado:Completo");
            }else{
                JOptionPane.showMessageDialog(null,"no entro al Completo  ");
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no se llamo al procedimiento " +e);
        }
        
        //si el estado es incompleto
        try {       
            if(resultadoEst.equals("Incompleto") && setget.getPer_estado()==null ){
                    JOptionPane.showMessageDialog(null,"se envio aquiii: "+resultadoEst);
                    ps=cnn.prepareCall("call tipopermiso('Incompleto')");
                    ps.executeUpdate();               
                    JOptionPane.showMessageDialog(null,"Call estado:Incompleto");
            }else{
                JOptionPane.showMessageDialog(null,"no entro al Imcompleto");
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no se llamo al procedimiento " +e);
        }
        
        
        //si el estado es denegado
        try {
            if(setget.getPer_estado().equals("Denegado") ){
                    ps=cnn.prepareCall("call tipopermiso('Denegado')");
                    ps.executeUpdate();               
                    JOptionPane.showMessageDialog(null,"Call estado:Denegado");
            }else{
                JOptionPane.showMessageDialog(null,"no entro al Denegado");
            }
        } catch (Exception e) {
        }
        return x;
        
    }
    
    
    public int eliminar_permiso(permisoSG ing){    
        int x=0;
            try {            
                ps=(PreparedStatement) cnn.prepareStatement("DELETE FROM permiso WHERE per_ID='"+ing.getPer_ID()+"' ");
                x=ps.executeUpdate();            
                JOptionPane.showMessageDialog(null, "Datos Eliminados");                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Datos NO Eliminados" +e);            
            }            
        return x;    
    }
    
    //CONSULTA EL ESTADO DEL PERMISO Y SE LE MANDA PARAMETRO AL PROCEDIMIENTO
     public int consultarPermiso(permisoSG ing){    
        int x=0;
        //ENTRADA DE ESTADO INCOMPLETO
            try {            
                // Creamos y ejecutamos un SQL statement que regresa las personas
                String SQL = "select * from permiso where per_estado = 'Incompleto'";
                stmt = cnn.createStatement();
                rs = stmt.executeQuery(SQL);
                
                while (rs.next()) {
                    JOptionPane.showMessageDialog(null,rs.getString("per_estado") + " este es el estado que consultamos CELEBREN  ");
                    String resultadoEst =  rs.getString("per_estado");
                    //JOptionPane.showMessageDialog(null, "value get thanks you :)   ---->" +resultadoEst);
                    
                    crudPermisos crud = new crudPermisos();
                    crud.procedimientos_historial(ing, resultadoEst);
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"no entro " +e);
            }    
            
            //ENTRADA DE ESTADO COMPLETO
            try {            
                // Creamos y ejecutamos un SQL statement que regresa las personas
                String SQL = "select * from permiso where per_estado = 'Completo'";
                stmt = cnn.createStatement();
                rs = stmt.executeQuery(SQL);
                
                while (rs.next()) {
                    JOptionPane.showMessageDialog(null,rs.getString("per_estado") + " este es el estado que consultamos CELEBREN  ");
                    String resultadoEst =  rs.getString("per_estado");
                    JOptionPane.showMessageDialog(null, "value get thanks you :)   ---->" +resultadoEst);
   
                    crudPermisos crud = new crudPermisos();
                    crud.procedimientos_historial(ing, resultadoEst);
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"no entro " +e);
            } 
            return x;    
    }
     
     
     /*consultar fecha y hora de ingreso_real y obtener su valor
     *para pasarlo como parametro en consultar permiso*/
     
     public int consulta_fecha_hora_ingreso_real(int id){
         
         JOptionPane.showMessageDialog(null, "entro al metodo de consulta datos reales");
         
         try{
         
            String SQL="select * from permiso where per_estado = 'Autorizado' AND per_ID ='"+id+"'" ;
            stmt = cnn.createStatement();
            rs = stmt.executeQuery(SQL);
            
            JOptionPane.showMessageDialog(null, "Ingreso al metodo:  --> consulta_fecha_hora_ingreso_real");
            
            while (rs.next()) {
               // JOptionPane.showMessageDialog(null,rs.getString("per_estado") + " este es el estado que consultamos CELEBREN  ");
                String resultadoFechaReal =  rs.getString("per_fecha_ingresoReal");
                String resultadoHoraReal =  rs.getString("per_hora_ingresoReal");
                JOptionPane.showMessageDialog(null, "esta es la fecha de ingreso real  " +resultadoFechaReal+ "esta es la hora de ingreso real  "  +  resultadoHoraReal);

                
                if(resultadoFechaReal!=null && resultadoHoraReal!=null){
                 crudPermisos crud = new crudPermisos();
                 permisoSG ing = null;
                 crud.actualizar_estado_completo_permiso(id, ing);
                }
//                crudPermisos crud = new crudPermisos();
//                crud.procedimientos_historial(ing, resultadoEst);
            }
            
         }catch(Exception e){
         
         }
         
        return 0;
     
     }
    
}
   

