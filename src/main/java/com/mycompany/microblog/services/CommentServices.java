/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.services;

import com.mycompany.microblog.DAO.CommentoJpaController;
import com.mycompany.microblog.DAO.PostJpaController;
import com.mycompany.microblog.entities.Commento;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author Jennifer
 */

@Path("/comments")
public class CommentServices {
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Commento> getComments() {
        List<Commento> ls = CommentoJpaController.findCommentoEntities();
        return ls;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Commento getComment(@PathParam("id") String CommentoId) {
        Commento Commento = CommentoJpaController.findCommento(Long.parseLong(CommentoId));
        return Commento;

    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createComment(Commento c){
        c.setDataOra(new Date());
        c.setPost(PostJpaController.findPost(c.getPost().getId()));
        CommentoJpaController.create(c);
        long id = c.getId();
        
        String locationString = "Microblog/rest/comments/" + id;
        
        UriBuilder location = UriBuilder.fromPath(locationString);
        
        return Response.created(location.build()).build();
    }
}
