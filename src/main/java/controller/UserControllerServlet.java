/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import models.User;
import services.UserService;

/**
 *
 * @author Admin
 */
public class UserControllerServlet extends HttpServlet {

    private UserService userService;

    @Resource(name = "jdbc/web_login")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            userService = new UserService(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
                //read command
                String command = request.getParameter("command");
                
                if(command == null){
                    response.sendRedirect("login_page.jsp");
                }
                
                switch(command){
                    case "LOGIN":
                        login(request, response);
                        break;
                    case "LOGOUT":
                        logout(request, response);
                        break;
                }
                
            } catch (Exception e) {
                throw new ServletException(e);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User theUser = new User(username, password);
            //add to db
            User checkLogin = userService.checkLogin(theUser);

            if (checkLogin == null) {
                
            response.sendRedirect("login_page.jsp");
//            response.sendRedirect(request.getContextPath() + "/login_page.jsp");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login_page.jsp");
//                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                
                session.setAttribute("user", username);
                session.setAttribute("pass", password);
                
                request.getRequestDispatcher("./show_result_page.jsp").forward(request, response);
//            response.sendRedirect("show_result_page.jsp");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/show_result_page.jsp");
//                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        
        session.setAttribute("user",null);
        session.setAttribute("pass",null);
        
        response.sendRedirect("./login_page.jsp");
//        request.getRequestDispatcher("./logout.jsp").forward(request, response);
    }

    

}
