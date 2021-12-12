package de.hhu.cs.dbs.propra.presentation.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.hhu.cs.dbs.propra.application.exceptions.ResourceNotFoundException;
import de.hhu.cs.dbs.propra.domain.model.Nutzer;
import de.hhu.cs.dbs.propra.domain.model.NutzerRepository;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Optional;


@Path("/nutzer")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class NutzerController {


    @Inject
    private NutzerRepository nutzerRepo;

    @Context
    private SecurityContext securityContext;

    @Context
    private UriInfo uriInfo;


    @GET // GET http://localhost:8080/nutzer
    public Response findNutzer(@QueryParam("name") String name, @QueryParam("email") String email) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String jsonInString = null;
        if (name == null && email == null) {
            try {
                jsonInString = mapper.writeValueAsString(nutzerRepo.fetchAll());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(jsonInString).build();
        } else if (name != null && email == null) {
            Optional<Nutzer> nutzer = nutzerRepo.findByName(name);
            if (nutzer.isEmpty()) throw new ResourceNotFoundException();
            try {
                jsonInString = mapper.writeValueAsString(nutzer.get());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(jsonInString).build();
        }else if(name == null && email != null) {
            Optional<Nutzer> nutzer = nutzerRepo.findByEmail(email);
            if (nutzer.isEmpty()) throw new ResourceNotFoundException();
            try {
                jsonInString = mapper.writeValueAsString(nutzer.get());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(jsonInString).build();
        }else {
            Optional<Nutzer> nutzer = nutzerRepo.findByNameAndEmail(name,email);
            if (nutzer.isEmpty()) throw new ResourceNotFoundException();
            try {
                jsonInString = mapper.writeValueAsString(nutzer.get());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(jsonInString).build();
        }
    }

    @POST
    public Response saveNutzer(@FormParam("email")String email, @FormParam("passwort")String passwort, @FormParam("benutzername")String name){
        nutzerRepo.save(Nutzer.builder()
                .name(name)
                .email(email)
                .passwort(passwort)
                .build());

        return Response.status(201).build();

    }





}



