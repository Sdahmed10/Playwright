package Test1;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("Authentication")
@Feature("Login")
@Listeners({AllureTestNg.class})
public class AuthAllure {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        page = context.newPage();
    }

    @Test(description = "Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User logs in with email and password")
    public void testLogin() {
        page.navigate("http://www.automationpractice.pl/index.php");
        page.locator("//a[normalize-space()='Sign in']").click();
        page.locator("#email").fill("sdiriahmed1@outlook.fr");
        page.locator("#passwd").fill("12345Aa@");
        page.locator("//span[normalize-space()='Sign in']").click();

        assertThat(page).hasTitle("My account - My Shop");

        context.storageState(new BrowserContext.StorageStateOptions()
                .setPath(Paths.get("applogin.json")));

        Allure.step("Auth state saved");
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
