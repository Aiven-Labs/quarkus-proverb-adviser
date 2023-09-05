package org.sebi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.sebi.entity.ProverbEmbedding;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmbeddingService {

    @ConfigProperty(name = "model", defaultValue = "text-embedding-ada-002")
    String model;

    @RestClient
    OpenAIEmbeddingService openAIEmbeddingService;

    @Transactional
    public void saveEmbedding(String input) {
        EmebeddingRequest emebeddingRequest = new EmebeddingRequest(input, model);
        ProverbEmbedding embedding = new ProverbEmbedding();
        embedding.proverb = input;
        embedding.embedding = openAIEmbeddingService.getEmbedding(emebeddingRequest).data.get(0).embedding;
        embedding.persist();
    }

    public List<String> findNearestEmbeddings(String input) {
        EmebeddingRequest emebeddingRequest = new EmebeddingRequest(input, model);
        List<Double> retrievedEmbedding = openAIEmbeddingService.getEmbedding(emebeddingRequest).data.get(0).embedding;
       
       //TODO refactor this ugly code :) 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Double number : retrievedEmbedding) {
            stringBuilder.append(number).append(","); // Append each number and a comma
        }
        int length = stringBuilder.length();
        if (length > 2) {
            // Remove the trailing comma and space
            stringBuilder.delete(length - 1, length);
        }
        stringBuilder.append("]");
        List<ProverbEmbedding> embeddings = ProverbEmbedding.findNearestNeighbors(stringBuilder.toString());
        List<String> replies = new ArrayList<>();
        for (ProverbEmbedding embedding : embeddings) {
            replies.add(embedding.proverb);
        }
        return replies;
    }
}
