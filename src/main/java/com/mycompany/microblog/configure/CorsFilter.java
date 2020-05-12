/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog.configure;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Jennifer
 */

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext,
            final ContainerResponseContext crc) throws IOException {
        crc.getHeaders().add("Access-Control-Allow-Origin", "*");
        crc.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        crc.getHeaders().add("Access-Control-Allow-Credentials", "true");
        crc.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        crc.getHeaders().add("Access-Control-Max-Age", "1209600");
        crc.getHeaders().add("Access-Control-Expose-Headers", "x-requested-with, authorization, content-type");
    }

}