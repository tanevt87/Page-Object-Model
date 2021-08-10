package tests;


import beans.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ClientPageTest extends BaseTest {


    @Test
    @DisplayName("Can create individual client")
    @Tag("positive")
    public void canCreateIndividualClient() {
        webApp.loginPage().defaultLogin();
        //Navigate to clients page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        // Create new client
        webApp.clientPage()
                .clickCreateNewClientButton()
                .clickIndividualClientRadioButton()
                .individualClientName("New Client")
                .clientAddress("Sofia")
                .clientCity("Sofia")
                .clickAddNewClientButton();
        //Verify client creation is successful
        Assertions.assertEquals("Клиентът е добавен успешно.", webApp.clientPage().getSuccessMessage());
        //Delete all clients
        api.clientAPI().deleteAllIClients();
    }

    @Test
    @DisplayName("Can search for existing client by full match")
    public void canSearchForExistingClientByFullMatch() {
        String clientToSearch = "TihomirTanev";
        Client client = Client.builder()
                .firm_name(clientToSearch)
                .firm_addr("Sofia")
                .firm_town("Sofia")
                .firm_is_reg_vat(true)
                .firm_mol("BG123456789")
                .firm_vat_number("BG123456789")
                .build();
        api.clientAPI().createClient(client);
        webApp.loginPage().defaultLogin();
        //Navigate to clients page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Search for client
        webApp.clientPage()
                .expandSearchSection()
                .enterCompanyName(clientToSearch)
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains(clientToSearch), "Client name was not found in the result table");
        api.clientAPI().deleteAllIClients();
    }

    @Test
    @DisplayName("Can search for existing client by partial match")
    public void canSearchForExistingClientByPartialMatch() {
        //Precondition is to have client to search for
        Client client1 = Client.builder()
                .firm_name("Tihomir")
                .firm_addr("Sofia")
                .firm_town("Sofia")
                .firm_is_reg_vat(true)
                .firm_mol("BG123456789")
                .firm_vat_number("BG123456789")
                .build();
        Client client2 = Client.builder()
                .firm_name("Tiho")
                .firm_addr("Plovdiv")
                .firm_town("Plovdiv")
                .firm_is_reg_vat(true)
                .firm_mol("BG1234567890")
                .firm_vat_number("BG1234567890")
                .build();
        api.clientAPI().createClient(client1);
        api.clientAPI().createClient(client2);
        webApp.loginPage().defaultLogin();
        //Navigate to clients page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Search for client
        webApp.clientPage()
                .expandSearchSection()
                .enterCompanyName("Tih")
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains("Tihomir"), "Client name was not found in the result table");
        Assertions.assertTrue(tableInfo.contains("Tiho"), "Client name was not found in the result table");
        api.clientAPI().deleteAllIClients();
        webApp.clientPage().navigate();
        String emptyListMessage = webApp.clientPage().getEmptyListMessage();
        Assertions.assertEquals("Все още нямате добавени клиенти.", emptyListMessage);
    }

    @Test
    @DisplayName("Can search for not-existing Client")
    public void canSearchForNotExistingClient() {
        webApp.loginPage().defaultLogin();
        //Navigate to clients page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Search for client
        webApp.clientPage()
                .expandSearchSection()
                .enterCompanyName("New Client")
                .clickSearchButton();
        Assertions.assertEquals("Не са намерени клиенти, отговарящи на зададените критерии.", webApp.clientPage().getEmptyListMessage());
    }

    @Test
    @DisplayName("Can edit existing client")
    public void canEditExistingClient() {
        String clientToSearch = "TihomirTanev";
        Client client = Client.builder()
                .firm_name(clientToSearch)
                .firm_addr("Sofia")
                .firm_town("Sofia")
                .firm_is_reg_vat(true)
                .firm_mol("BG123456789")
                .firm_vat_number("BG123456789")
                .build();
        api.clientAPI().createClient(client);
        webApp.loginPage().defaultLogin();
        //Navigate to clients page
        webApp.clientPage().navigate();
        //Verify navigation was successful
        Assertions.assertEquals("Клиенти", webApp.clientPage().getHeadline());
        //Search for client
        webApp.clientPage()
                .expandSearchSection()
                .enterCompanyName(clientToSearch)
                .clickSearchButton();
        //Check the result in the table
        String tableInfo = webApp.clientPage().getResultTableInfo();
        Assertions.assertTrue(tableInfo.contains(clientToSearch), "Client name was not found in the result table");
        webApp.clientPage()
                .clickEditClient()
                .companyName("Edit Company")
                .saveEditedCompanyName();
        api.clientAPI().deleteAllIClients();
    }
}
