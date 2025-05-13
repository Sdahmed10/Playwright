

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("Authentication")
@Feature("Login")
@Listeners({AllureTestNg.class})

public class TestLoginLinkefootWithReport {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

    }

    @BeforeMethod
    public void createPage() {
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = context.newPage();
        page.navigate("https://staginglinkfootweb.softylines.com/auth/jwt/login");
    }

    @Test(description = "Login with invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User logs in with email and password")
    public void testLoginFailed() {
        page.locator("//input[@id='email']").type("hipator675@skrak.com");
        page.locator("//input[@id='password']").type("12345Aa");
        page.locator("//button[normalize-space()='Login']").click();
        Locator Texte = page.locator("//p[contains(@class, 'error-message')]");
        page.waitForSelector("//p[contains(@class, 'error-message')]");
        assertThat(Texte).isVisible();
        System.out.println("Login failed - Error message found");
        Allure.step("Auth state saved");
    }

    @Test(description = "Login with invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User logs in with email and password")
    public void testLoginFail() {
        page.locator("//input[@id='email']").type("hipator675@skrak.com");
        page.locator("//input[@id='password']").type("12345Aa@Q");
        page.locator("//button[normalize-space()='Login']").click();
        Locator ErrorMessage = page.locator("//p[@class='login-error-message error-message']");
        page.waitForSelector("//p[@class='login-error-message error-message']");
        assertThat(ErrorMessage).isVisible();
        System.out.println("Login Failed - Error message found");
        Allure.step("Auth state saved");
    }

    @Test(description = "Login with invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User logs in with email and password")
    public void testLoginFail1() {
        page.locator("//input[@id='email']").type("hipator6751@skrak.com");
        page.locator("//input[@id='password']").type("12345Aa@");
        page.locator("//button[normalize-space()='Login']").click();
        Locator ErrorMessage1 = page.locator("//p[@class='login-error-message error-message']");
        page.waitForSelector("//p[@class='login-error-message error-message']");
        assertThat(ErrorMessage1).isVisible();
        System.out.println("Login Failed - User not registered or not verified");
        Allure.step("Auth state saved");
    }

    @Test(description = "Login with valid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Story("User logs in with email and password")
    public void testLoginPassed() {
        page.locator("//input[@id='email']").type("hipator675@skrak.com");
        page.locator("//input[@id='password']").type("12345Aa@");
        page.locator("//button[normalize-space()='Login']").click();
        Locator logo = page.locator("//img[@alt='linkefoot-logo']");
        page.waitForSelector("//img[@alt='linkefoot-logo']");
        assertThat(logo).isVisible();
        System.out.println("Login successful - Logo found");
        Allure.step("Auth state saved");
    }


    @AfterMethod
    public void closePage() {
        if (page != null) {
            page.close();
        }
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
