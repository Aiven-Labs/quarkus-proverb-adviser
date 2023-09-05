# Tell me how you feel, I give you a Proverb ! 

This is a demo to show how to use vector similarity searches on complex vectors, embeddings generated by openAI. The searches are performed by `pgvector`.

This repo comes with a CSV containing 100 proverbs and their embeddings. Their is an endpoint where you can provide a free text, it will be transformed into an embedding and then search for the 2 most nearest vectors and display the related proverbs.

## Setup

You will a need a Postgres instance with the `pgvector` extension.

You can get a completely **free** Postgres instance on Aiven, use this special referral [link](https://go.aiven.io/sebi-signup) to get extra credits to try out the other services ("Apache Kafka", "Clickhouse", "Apache Flink" etc ...), no credit cards asked.

Once connected to your DB , type : 

```
CREATE EXTENSION vector;
```

You need also to import the existings embeddings, the file is called `embeddings` and is in CSV format. 

From the command line you can do something like : 
```
psql 'postgres://avnadmin:password@yourdbhost:port/defaultdb?sslmode=require' -c  "\\copy ProverbEmbedding FROM 'embeddings' WITH CSV DELIMITER ','"

```

You will also need an OpenAI token, you can get one [here](https://platform.openai.com/).

In the `src/main/resources/application.properties` set the correct values for your db and your OpenAI token ! 


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script

./mvnw quarkus:dev

```

Now you can perform `curl` commands like : 

```
curl -X POST -d "I'm feeling sad" http://localhost:8080/embedding/feeling

```