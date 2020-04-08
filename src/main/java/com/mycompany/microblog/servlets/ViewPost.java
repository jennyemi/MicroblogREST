/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.servlets;

import com.mycompany.microblog.DAO.PostJpaController;
import com.mycompany.microblog.entities.Post;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jennifer
 */
public class ViewPost extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Post> ls = PostJpaController.findPostEntities();
        
        HttpSession session = request.getSession(false);
        
        request.setAttribute("ListaPost", ls);
        request.setCharacterEncoding("UTF-8");
        
        request.getRequestDispatcher("postList.jsp").forward(request, response);
    }
}
