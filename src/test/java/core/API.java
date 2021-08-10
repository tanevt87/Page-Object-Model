package core;

import api.ClientAPI;
import api.ItemAPI;

public class API {
    private ItemAPI itemAPI;
    private ClientAPI clientAPI;

    public ItemAPI itemAPI() {
        if (itemAPI == null) {
            itemAPI = new ItemAPI();
        }
        return itemAPI;
    }

    public ClientAPI clientAPI() {
        if (clientAPI == null) {
            clientAPI = new ClientAPI();
        }
        return clientAPI;
    }
}
