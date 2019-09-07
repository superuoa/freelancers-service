package com.demo.freelancer.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.freelancer.model.Skills;
import com.demo.freelancer.model.Users;
import com.demo.freelancer.service.FreelancerService;

@Path("/")
@Component
public class FreelancerEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(FreelancerEndpoint.class);

    @Resource
    private FreelancerService freelancerService;

    @GET
    @Path("/freelancers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getFreelancers() {
        return freelancerService.findAll();
    }

    @GET
    @Path("/freelancers/{freelancerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getFreelancer(@PathParam("freelancerId") long freelancerId) {
        return freelancerService.findById(freelancerId);
    }
    
    
    //for test insert
    @POST
    @Path("/insertUserSkill/{freelancerId}/{skillId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getFreelancers(@PathParam("freelancerId") long freelancerId,@PathParam("skillId") int skillId) {
    	
    	freelancerService.insertUserSkill(freelancerId, skillId); 
        
    }
    
    @GET
    @Path("/skills")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Skills> getSkills() {
        return freelancerService.getSkillAll();
    }
   

}
