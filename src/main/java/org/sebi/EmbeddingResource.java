package org.sebi;

import java.util.List;

import org.sebi.entity.ProverbEmbedding;
import org.sebi.entity.ProverbOriginal;
import org.sebi.model.Embedding;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("embedding")
public class EmbeddingResource {
    
    @Inject
    EmbeddingService embeddingService;

    //You can use this endpoint to add more proverbs
    @POST
    @Path("save")
    public Response saveEmbedding(String input) {
        this.embeddingService.saveEmbedding(input);
        return Response.status(Status.CREATED).build();
    }

    //Not needed for running the demo
    @GET
    @Path("bulk")
    public Response builkImport(){
        List<ProverbOriginal> originals = ProverbOriginal.listAll();
        for (ProverbOriginal proverb : originals) {
             this.embeddingService.saveEmbedding(proverb.proverb);
        }
        return Response.status(Status.CREATED).build();
    }

    //This will rperform the vector search
    @POST
    @Path("feeling")
    public List<String> getNearestProverb(String feeling) {
       return embeddingService.findNearestEmbeddings(feeling);
    }
}
