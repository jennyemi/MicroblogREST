/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.servlets;

import com.mycompany.microblog.DAO.PostJpaController;
import com.mycompany.microblog.DAO.UtenteJpaController;
import com.mycompany.microblog.entities.Post;
import com.mycompany.microblog.entities.Utente;
import java.io.IOException;
import java.util.Date;
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
public class CreatePost extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titolo = request.getParameter("title");
        String testo = request.getParameter("text");

        Date date = new Date();

        Post post = new Post();
        post.setTitolo(titolo);
        post.setTesto(testo);
        post.setDataOra(date);

        HttpSession session = request.getSession(false);

        Utente u = UtenteJpaController.findUtentebyUsername((String) session.getAttribute("username"));

        post.setUtente(u);

        PostJpaController.create(post);

        List<Post> ls = PostJpaController.findPostEntities();

        request.setAttribute("ListaPost", ls);
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("postList.jsp").forward(request, response);

    }
}
