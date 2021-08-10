package api;

import beans.Item;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class ItemAPI extends HTTPClient {

    private static final String ITEMS_URL = "/items";
    private static final String ITEM_URL = "/item";

    public Response getAllItems() {
        return get(ITEMS_URL);
    }

    public Response getItem(String id) {
        return get(ITEM_URL + "/" + id);
    }

    public Response deleteItem(String id) {
        return delete(ITEM_URL + "/" + id);
    }

    public void deleteAllItems() {
        //Get all items
        Response response = getAllItems();
        //List with the ids of the items to be deleted
        List<Integer> itemsToBeDeleted = JsonPath.read(response.body().asString(), "$..id");
        if (itemsToBeDeleted.isEmpty()) {
            System.out.println("Sorry dude, your list is empty");
        }
        itemsToBeDeleted.forEach(i -> System.out.println(i));
        //Delete items one by one from the list
        itemsToBeDeleted.stream().distinct().forEach(id -> deleteItem(String.valueOf(id)));
    }

    /**
     * Creates new item via API
     *
     * @param body of the item
     * @return response
     */
    public Response createItem(Item body) {
        return post(ITEM_URL, GSON.toJson(body));

    }

    /**
     * Updates existing item
     *
     * @param id   of the item to be updated
     * @param body body for the new item
     * @return response
     */
    public Response updateItem(Item id, Item body) {
        return put(ITEM_URL + "/" + id, GSON.toJson(body));
    }
}
