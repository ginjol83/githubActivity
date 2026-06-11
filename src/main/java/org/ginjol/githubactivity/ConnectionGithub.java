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

    public GitHubData connect(){

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

    public GitHubData toJson(String jsonText){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonText);

            // Extract value
            JsonNode nodeList   = node.get(0);

            String   id         = nodeList.get("id").asText();
            String   type       = nodeList.get("type").asText();
            boolean  isPublic   = nodeList.get("public").asBoolean();
            String   createdAt  = nodeList.get("created_at").asText();

            JsonNode payloadNode          = nodeList.get("payload");
            String   payloadRef           = payloadNode.get("ref").asText();
            String   payloadHead          = payloadNode.get("head").asText();
            String   payloadPushId        = payloadNode.get("push_id").asText();
            String   payloadRepositoryId  = payloadNode.get("repository_id").asText();
            Payload  payload              = new Payload(payloadRepositoryId,payloadPushId,payloadRef,payloadHead);


            JsonNode actorNode         = nodeList.get("actor");
            String   actorId           = actorNode.get("id").asText();
            String   actorUrl          = actorNode.get("url").asText();
            String   actorLogin        = actorNode.get("login").asText();
            String   actorAvatarUrl    = actorNode.get("avatar_url").asText();
            String   actorGravatarId   = actorNode.get("gravatar_id").asText();
            String   actorDisplayLogin = actorNode.get("display_login").asText();
            Actor    actor             = new Actor(actorId,actorLogin,actorDisplayLogin,actorGravatarId,actorUrl,actorAvatarUrl);

            JsonNode repoNode   = nodeList.get("repo");
            String   repoId     = repoNode.get("id").asText();
            String   repoUrl    = repoNode.get("url").asText();
            String   repoName   = repoNode.get("name").asText();
            Repo     repo       = new Repo(repoId,repoName,repoUrl);

            GitHubData gitHubData = new GitHubData();
            gitHubData.setCreatedAt(createdAt);
            gitHubData.setPayload(payload);
            gitHubData.setPublic(isPublic);
            gitHubData.setActor(actor);
            gitHubData.setRepo(repo);
            gitHubData.setType(type);
            gitHubData.setId(id);

            return gitHubData;
        }catch (JsonProcessingException e){

        }
        return null;
    }
}
