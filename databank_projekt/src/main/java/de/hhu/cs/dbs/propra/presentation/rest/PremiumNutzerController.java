package de.hhu.cs.dbs.propra.presentation.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.hhu.cs.dbs.propra.application.exceptions.ResourceNotFoundException;
import de.hhu.cs.dbs.propra.domain.model.Nutzer;
import de.hhu.cs.dbs.propra.domain.model.NutzerRepository;
import de.hhu.cs.dbs.propra.domain.model.PremiumNutzerRepo;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Optional;


@Path("/premiumnutzer")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class PremiumNutzerController {


    @Inject
    private PremiumNutzerRepo premiumNutzerRepo;

    @Context
    private SecurityContext securityContext;

    @Context
    private UriInfo uriInfo;


    @GET // GET http://localhost:8080/premiumnutzer
    public Response findPremiumNutzer(@QueryParam("abgelaufen") boolean abgelaufen) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String jsonInString = null;
        if (abgelaufen == true) {
            try {
                jsonInString = mapper.writeValueAsString(premiumNutzerRepo.fetchAll());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(jsonInString).build();

            {

            }

        }

    }
}