package TakiAcademyLeaveRequest;

import com.microsoft.playwright.*;
import org.testng.annotations.*;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LeaveRequest {
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
        page.navigate("https://devleaverequest.softylines.com/");
    }

    @Test
    public void LeaveReq() throws InterruptedException {
        page.locator("//input[@id='email']").type("superadmin@gmail.com");
        page.locator("//input[@id='password']").type("123456789");
        page.locator("//button[normalize-space()='Login']").click();
        Locator Texte = page.locator("//div[@class='user-name']");
        page.waitForSelector("//div[@class='user-name']");
        assertThat(Texte).isVisible();
        System.out.println("Login Success");

        page.locator("//button[normalize-space()='Add New Employee']").click();
        Locator Texte1 = page.locator("//span[@class='title-modals']");
        page.waitForSelector("//span[@class='title-modals']");
        assertThat(Texte1).isVisible();
        System.out.println("Redirect to the Add new employee page");


        page.waitForSelector("//div[@class='card_container cardOne-wrapper']//input[@placeholder='Select...']");
        page.locator("//div[@class='card_container cardOne-wrapper']//input[@placeholder='Select...']").click();


        page.waitForSelector("[aria-label='Open Year Selector']");
        page.locator("[aria-label='Open Year Selector']").first().click();
        page.waitForSelector("li.Calendar__yearSelectorItem button:text('1990')").click();


        page.waitForSelector("[aria-label='Open Month Selector']");
        page.locator("[aria-label='Open Month Selector']").first().click();
        page.locator("li.Calendar__monthSelectorItem >> text=June").click();


        page.waitForSelector("[aria-label='Wednesday, 13 June 1990']");
        page.locator("[aria-label='Wednesday, 13 June 1990']").click();

        Locator Texte2 = page.locator("//input[@value='1990/06/13']");
        page.waitForSelector("//input[@value='1990/06/13']");
        assertThat(Texte2).isVisible();
        System.out.println("Birthday selected successfully");


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
