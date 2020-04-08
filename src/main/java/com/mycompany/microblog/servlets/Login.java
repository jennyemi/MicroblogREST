/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.servlets;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.mycompany.microblog.DAO.PostJpaController;
import com.mycompany.microblog.DAO.UtenteJpaController;
import com.mycompany.microblog.entities.Post;
import com.mycompany.microblog.entities.Utente;
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
public class Login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utente u = UtenteJpaController.findUtentebyUsername(username);
        
        String salt = u.getSalt();

        String passwordEncrypted = password + salt;

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(passwordEncrypted, Charsets.UTF_8);
        String sha256 = hasher.hash().toString();

        if (sha256.equals(u.getPassword())) {

            HttpSession session = request.getSession();
            session.setAttribute("username", u.getUsername());
            
            List<Post> ls = PostJpaController.findPostEntities();
            
            request.setAttribute("ListaPost", ls);
            request.setCharacterEncoding("UTF-8");
            
            request.getRequestDispatcher("postList.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("userNotPermitted.html").forward(request, response);
        }

    }
}
