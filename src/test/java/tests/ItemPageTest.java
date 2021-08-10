package tests;

import beans.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemPageTest extends BaseTest {

    @Test
    @DisplayName("Can create item")
    public void canGetAllItems() {
        webApp.loginPage().defaultLogin();
        //Navigate to items page
        webApp.itemPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Артикули", webApp.itemPage().getHeadline());
        //Create item
        webApp.itemPage()
                .clickCreateNewItemButton()
                .enterName("Fluent item name")
                .enterPrice(5)
                .clickSaveButton();
        //Verify item creation is successful
        Assertions.assertEquals("Артикулът е добавен успешно.", webApp.itemPage().getSuccessMessage());
        //Delete all items
        api.itemAPI().deleteAllItems();
    }

    @Test
    @DisplayName("Can search for existing item by full match")
    public void canSearchForExistingItemByFullMatch(){
        String itemToSearch = "AlexKaramfilov";
        //Precondition is to have item to search for
        Item item = Item.builder()
                .name(itemToSearch)
                .quantity_unit("kg.")
                .price_for_quantity(10)
                .build();
        api.itemAPI().createItem(item);
        webApp.loginPage().defaultLogin();
        //Navigate to items page
        webApp.itemPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Артикули", webApp.itemPage().getHeadline());
        //Search for item
        webApp.itemPage()
                .expandSearchSection()
                .enterNameToSearchFor(itemToSearch)
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.itemPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains(itemToSearch), "Item name was not found in the result table");
        api.itemAPI().deleteAllItems();
    }

    @Test
    @DisplayName("Can search for existing item by partial match")
    public void canSearchForExistingItemByPartialMatch(){
        //Precondition is to have items to search for
        Item item1 = Item.builder()
                .name("Ivan")
                .quantity_unit("kg.")
                .price_for_quantity(10)
                .build();
        Item item2 = Item.builder()
                .name("Ivelina")
                .quantity_unit("kg.")
                .price_for_quantity(10)
                .build();
        //Create two items with partial string match
        api.itemAPI().createItem(item1);
        api.itemAPI().createItem(item2);
        webApp.loginPage().defaultLogin();
        //Navigate to items page
        webApp.itemPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Артикули", webApp.itemPage().getHeadline());
        //Search for item
        webApp.itemPage()
                .expandSearchSection()
                .enterNameToSearchFor("Iv")
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.itemPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains("Ivan"), "Item name was not found in the result table");
        Assertions.assertTrue(tableInfo.contains("Ivelina"), "Item name was not found in the result table");
        api.itemAPI().deleteAllItems();
        webApp.itemPage().navigate();
        String emptyListMessage = webApp.itemPage().getEmptyListMessage();
        Assertions.assertEquals("Все още нямате добавени артикули.", emptyListMessage);

    }

    @Test
    @DisplayName("Can search for not-existing item")
    public void canSearchForNotExistingItem(){
        webApp.loginPage().defaultLogin();
        //Navigate to items page
        webApp.itemPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Артикули", webApp.itemPage().getHeadline());
        //Search for item
        webApp.itemPage()
                .expandSearchSection()
                .enterNameToSearchFor("My item name")
                .clickSearchButton();
        //Check empty table list text
        Assertions.assertEquals("Не са намерени артикули, отговарящи на зададените критерии.", webApp.itemPage().getEmptyListMessage());
    }
}
