package api;


import beans.Client;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ClientAPI extends HTTPClient {

    private static final String CLIENTS_URL = "/clients";
    private static final String CLIENT_URL = "/client";

    public Response getAllIClients() {
        return get(CLIENTS_URL);
    }

    public Response getClient(String id) {
        return get(CLIENT_URL + "/" + id);
    }

    public Response deleteClient(String id) {
        return delete(CLIENT_URL + "/" + id);
    }

    public void deleteAllIClients() {
        //Get all clients
        Response response = getAllIClients();
        //List with the ids of the clients to be deleted
        List<Integer> clientsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        if (clientsToBeDeleted.isEmpty()) {
            System.out.println("Sorry dude, your client list is empty");
        }

        clientsToBeDeleted.forEach(i -> System.out.println(i));
        //Delete clients one by one from the list
        clientsToBeDeleted.stream().distinct().forEach(id -> deleteClient(String.valueOf(id)));
    }

    public Response createClient(Client body) {
        return post(CLIENT_URL, GSON.toJson(body));
    }
}
