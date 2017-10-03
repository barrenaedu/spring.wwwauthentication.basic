package com.rest;


import com.domain.Message;
import com.service.MessageManager;
import com.service.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/messages")
@Component
public class MessagesResource {
    private SecurityManager securityManager;
    private final MessageManager messageManager;

    @Autowired
    public MessagesResource(SecurityManager securityManager, MessageManager messageManager) {
        this.securityManager = securityManager;
        this.messageManager = messageManager;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessages(@HeaderParam("authorization") String authString) {
        if (!securityManager.isAuthorized("LIST_MESSAGES", authString)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Collection<Message> msgs = messageManager.getMessages();
        return Response.status(Response.Status.OK).entity(msgs.toArray(new Message[msgs.size()])).build();
    }
}
