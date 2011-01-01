package selenium.admin.publish.edit;

import org.junit.After;
import org.junit.Before;
import selenium.admin.login.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.ExceptionHandler;
import selenium.admin.publish.view.ViewPage;
import static org.junit.Assert.*;

/**
 * @author jogri
 */
public class EditPageFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get("http://prototype.kantega.lan/template-site/admin");
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testClickCancel() throws Throwable {
        try {
            LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
            loginPage.login();

            ViewPage viewPage = PageFactory.initElements(webDriver, ViewPage.class);
            viewPage.checkThatPageElementsArePresent("/template-site");
            viewPage.getModesMenu().clickEdit();

            EditPage editPage = new EditPage(webDriver);
            editPage.checkThatPageElementsArePresent("/template-site");
            editPage.getEditContentButtons().clickCancel();

            viewPage.checkThatPageElementsArePresent("/template-site");
        } catch (Throwable throwable) {
            ExceptionHandler.createErrorReport(webDriver, getClass());
            throw throwable;
        }
    }
}
