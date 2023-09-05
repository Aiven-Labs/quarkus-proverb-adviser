package org.sebi;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.sebi.model.Embedding;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(baseUri = "https://api.openai.com")
@Path("/v1/embeddings")
public interface OpenAIEmbeddingService {

    @POST
    @ClientHeaderParam(name = "Authorization" , value = "Bearer ${openaitoken}")
    public Embedding getEmbedding(EmebeddingRequest request);
    
}
