/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jennifer
 */
public class CheckUserComment extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            
        if(request.getSession(false).getAttribute("username") != null){
            
            String postId = request.getParameter("postId");
            long pid = Long.parseLong(postId);
            
            request.setAttribute("postId", pid);
            
            request.getRequestDispatcher("newComment.jsp").include(request, response);
        }else{
            request.getRequestDispatcher("userNotPermitted.html").include(request, response);
        }
                
          
        
    }
}
