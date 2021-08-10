package tests;

import api.HTTPClient;
import core.API;
import core.BrowserFactory;
import core.WebApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;


public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private BrowserFactory browserFactory;
    protected WebApp webApp;
    protected API api = new API();


    @BeforeAll
    private static void beforeAll() {
        HTTPClient.setupRequestDefaults();
        BrowserFactory.downloadDriver(Defaults.BROWSER);
    }

    @BeforeEach
    private void beforeEachTest(TestInfo info) {
        LOGGER.info("STARTING TEST: " + info.getDisplayName());
        browserFactory = new BrowserFactory();
        webApp = new WebApp(browserFactory.startBrowser(Defaults.BROWSER));
    }

    @AfterEach
    private void afterEachTest() {
        browserFactory.quit();
    }
}


