/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class TestConnection extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try {
            myConn = DatabaseConnection.getConnection();
            
            myStmt = myConn.createStatement();
            
            String sql = "SELECT * FROM tblAdmin";
            
            myRs = myStmt.executeQuery(sql);
            
            while(myRs.next()){
                String name = myRs.getString("full_name");
                
                out.println(name);
                out.print("<br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //close
            if(myRs != null) try {
                myRs.close();
            } catch (SQLException ignore){
                //ignore
            }
            
            if(myStmt != null) try {
                myStmt.close();
            } catch (SQLException ignore){
                //ignore
            }
            
            if(myConn != null) try {
                myConn.close();
            } catch (SQLException ignore){
                //ignore
            }
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
