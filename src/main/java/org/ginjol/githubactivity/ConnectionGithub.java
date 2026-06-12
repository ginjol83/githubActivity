package org.ginjol.githubactivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ginjol.githubactivity.models.Actor;
import org.ginjol.githubactivity.models.GitHubData;
import org.ginjol.githubactivity.models.Payload;
import org.ginjol.githubactivity.models.Repo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ConnectionGithub {
    private String userName;

    public ConnectionGithub (String userName){
        this.userName = userName;
    }

    private String getUserName(){
        return userName;
    }

    private void setUserName(String userName){
        this.userName = userName;
    }

    public ArrayList<GitHubData> connect(){

        try {
            // 1. Url define
            URI uri = URI.create("https://api.github.com/users/"+userName+"/events");
            URL url = uri.toURL();

            // 2. Open connection
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");

            // 3. response code (200 OK)
            int respuesta = conexion.getResponseCode();
            if (respuesta == 200) {
                // 4. read server response
                BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder contenido = new StringBuilder();
                String linea;

                while ((linea = lector.readLine()) != null) {
                    contenido.append(linea);
                }
                lector.close();

                if(contenido.isEmpty()){
                    return null;
                }

                return toJson(contenido.toString());

            } else {
                System.out.println("Error en la petición. Código: " + respuesta);
            }


            // 5. Close connection
            conexion.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<GitHubData> toJson(String jsonText){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonText);

            ArrayList<GitHubData> gitHubDataList = new ArrayList<>();
            // Extract value
            for(JsonNode nodeList: node){
                //JsonNode nodeList   = node.get(0);

                String   id         = nodeList.path("id").asText();
                String   type       = nodeList.path("type").asText();
                boolean  isPublic   = nodeList.path("public").asBoolean();
                String   createdAt  = nodeList.path("created_at").asText();
                GitHubData gitHubData = new GitHubData();
                gitHubData.setCreatedAt(createdAt);

                JsonNode payloadNode          = nodeList.path("payload");
                if(!payloadNode.isNull()){
                    String   payloadRef           = payloadNode.path("ref").asText();
                    String   payloadHead          = payloadNode.path("head").asText();
                    String   payloadPushId        = payloadNode.path("push_id").asText();
                    String   payloadRepositoryId  = payloadNode.path("repository_id").asText();
                    Payload  payload              = new Payload(payloadRepositoryId,payloadPushId,payloadRef,payloadHead);
                    gitHubData.setPayload(payload);
                }


                JsonNode actorNode         = nodeList.path("actor");
                String   actorId           = actorNode.path("id").asText();
                String   actorUrl          = actorNode.path("url").asText();
                String   actorLogin        = actorNode.path("login").asText();
                String   actorAvatarUrl    = actorNode.path("avatar_url").asText();
                String   actorGravatarId   = actorNode.path("gravatar_id").asText();
                String   actorDisplayLogin = actorNode.path("display_login").asText();
                Actor    actor             = new Actor(actorId,actorLogin,actorDisplayLogin,actorGravatarId,actorUrl,actorAvatarUrl);

                JsonNode repoNode   = nodeList.path("repo");
                String   repoId     = repoNode.path("id").asText();
                String   repoUrl    = repoNode.path("url").asText();
                String   repoName   = repoNode.path("name").asText();
                Repo     repo       = new Repo(repoId,repoName,repoUrl);



                gitHubData.setPublic(isPublic);
                gitHubData.setActor(actor);
                gitHubData.setRepo(repo);
                gitHubData.setType(type);
                gitHubData.setId(id);

                gitHubDataList.add(gitHubData);
            }

            return gitHubDataList;
        }catch (JsonProcessingException e){

        }
        return null;
    }
}
