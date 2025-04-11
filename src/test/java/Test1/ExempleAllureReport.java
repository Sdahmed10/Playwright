package Test1;


import com.microsoft.playwright.*;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("Authentication")
@Feature("Login")
@Listeners({AllureTestNg.class})

public class ExempleAllureReport {
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
        page.navigate("http://www.automationpractice.pl/index.php");
    }

    @Test(description = "Login with invalid password")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User logs in with email and password")
    public void testLoginFailed() {
        page.locator("//a[normalize-space()='Sign in']").click();
        page.locator("#email").fill("sdiriahmed1@outlook.fr");
        page.locator("#passwd").fill("123456Aa@");
        page.locator("//span[normalize-space()='Sign in']").click();

        Locator Texte = page.locator("//li[normalize-space()='Authentication failed.']");
        page.waitForSelector("//li[normalize-space()='Authentication failed.']");
        assertThat(Texte).isVisible();
        System.out.println("Login failed - Error message found");
        Allure.step("Auth state saved");
    }

    @Test(description = "Login with invalid Email")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User logs in with email and password")
    public void testLoginFail() {
        page.locator("//a[normalize-space()='Sign in']").click();
        page.locator("#email").fill("sdiriahmed16@outlook.fr");
        page.locator("#passwd").fill("12345Aa@");
        page.locator("//span[normalize-space()='Sign in']").click();

        Locator Texte = page.locator("//li[normalize-space()='Authentication failed.']");
        page.waitForSelector("//li[normalize-space()='Authentication failed.']");
        assertThat(Texte).isVisible();
        System.out.println("Login Failed - Error message found");
        Allure.step("Auth state saved");
    }

    @Test(description = "Login with invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User logs in with email and password")
    public void testLoginFail1() {
        page.locator("//a[normalize-space()='Sign in']").click();
        page.locator("#email").fill("sdiriahmed19@outlook.fr");
        page.locator("#passwd").fill("1234577Aa@");
        page.locator("//span[normalize-space()='Sign in']").click();

        Locator Texte = page.locator("//li[normalize-space()='Authentication failed.']");
        page.waitForSelector("//li[normalize-space()='Authentication failed.']");
        assertThat(Texte).isVisible();
        System.out.println("Login Failed - Error message found");
        Allure.step("Auth state saved");
    }

    @Test(description = "Login with valid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Story("User logs in with email and password")
    public void testLoginPassed() {
        page.locator("//a[normalize-space()='Sign in']").click();
        page.locator("#email").fill("sdiriahmed1@outlook.fr");
        page.locator("#passwd").fill("12345Aa@");
        page.locator("//span[normalize-space()='Sign in']").click();

        assertThat(page).hasTitle("My account - My Shop");
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
