package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientPage extends BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientPage.class);
    private final String URL = "/clients/manage";
    private By headlineSelector = By.cssSelector("#tabs_clients");
    private By createNewClientSelector = By.cssSelector(".newbtn.selenium-add-client-button");
    private By individualClientSelector = By.xpath("//input[@id='type_person']");
    private By individualClientNameFieldSelector = By.xpath("//input[@name='person_name']");
    private By clientAddressFieldSelector = By.xpath("//td//textarea[@name='person_address']");
    private By clientCityFieldSelector = By.xpath("//input[@name='person_city']");
    private By addNewClientSelector = By.xpath("//input[@class='button--height24px'][1]");
    private By successMessageSelector = By.xpath("//div[@id='okmsg']");
    private By expandSearchButtonSelector = By.xpath("//a[@id='searchbtn']");
    private By companyNameFieldSelector = By.xpath("//input[@name='fnm']");
    private By searchButtonSelector = By.xpath("//input[@name='s']");
    private By searchResultTableSelector = By.id("fakturi_table");
    private By emptyResultListSelector = By.id("emptylist");
    private By editClientSelector = By.cssSelector("a.editinvoice");
    private By companyNameSelector = By.cssSelector("input[name=firm_name]");
    private By saveEditedCompanyNameSelector = By.cssSelector("input[name=do_submit]");


    public ClientPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Navigates to client page
     */
    public ClientPage navigate() {
        LOGGER.info("Navigating to url:" + URL);
        navigateTo(URL);
        return this;
    }

    /**
     * Returns the headline text
     *
     * @return text
     */
    public String getHeadline() {
        return getText(headlineSelector);
    }

    public ClientPage clickCreateNewClientButton() {
        LOGGER.info("Clicking new client button");
        click(createNewClientSelector);
        return this;
    }

    public ClientPage clickIndividualClientRadioButton() {
        LOGGER.info("Clicking individual client radio button");
        click(individualClientSelector);
        return this;
    }


    public ClientPage individualClientName(String name) {
        LOGGER.info("Entering client name: " + name);
        type(individualClientNameFieldSelector, name);
        return this;
    }

    public ClientPage clientAddress(String address) {
        LOGGER.info("Entering client address: " + address);
        type(clientAddressFieldSelector, address);
        return this;
    }

    public ClientPage clientCity(String city) {
        LOGGER.info("Entering client city: " + city);
        type(clientCityFieldSelector, city);
        return this;
    }

    public ClientPage clickAddNewClientButton() {
        LOGGER.info("Clicking add client button");
        click(addNewClientSelector);
        return this;
    }

    public String getSuccessMessage() {
        return getText(successMessageSelector);
    }

    public ClientPage expandSearchSection() {
        LOGGER.info("Expanding Search section");
        click(expandSearchButtonSelector);
        return this;
    }

    public ClientPage enterCompanyName(String name) {
        LOGGER.info("Entering company name to search for: " + name);
        type(companyNameFieldSelector, name);
        return this;
    }

    public ClientPage clickSearchButton() {
        LOGGER.info("Clicking Search button");
        click(searchButtonSelector);
        return this;
    }

    public String getResultTableInfo() {
        return getText(searchResultTableSelector);
    }

    public String getEmptyListMessage() {
        return getText(emptyResultListSelector);
    }

    public ClientPage clickEditClient() {
        LOGGER.info("Clicking edit client button");
        click(editClientSelector);
        return this;
    }

    public ClientPage companyName(String companyName) {
        LOGGER.info("Entering company name: " + companyName);
        clearDefaultTextAndType(companyNameSelector, companyName);
        return this;
    }

    public ClientPage saveEditedCompanyName() {
        LOGGER.info("Clicking submit button");
        click(saveEditedCompanyNameSelector);
        return this;
    }
}

