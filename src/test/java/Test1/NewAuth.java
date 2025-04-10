package Test1;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NewAuth {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        //Automatic login : no need to write login steps
        BrowserContext brContext = browser.newContext();
        Page page = brContext.newPage();

        page.navigate("http://www.automationpractice.pl/index.php");
        page.locator("//a[normalize-space()='Sign in']").click();
        page.locator("//input[@id='email']")
                .fill("sdiriahmed1@outlook.fr");
        page.locator("//input[@id='passwd']")
                .fill("12345Aa@");
        page.locator("//span[normalize-space()='Sign in']")
                        .click();

        assertThat(page).hasTitle("My account - My Shop");
        System.out.println(page.title());

        // Save storage state
        brContext.storageState(new BrowserContext.StorageStateOptions()
                .setPath(Paths.get("applogin.json")));

        System.out.println("Auth state saved to applogin.json");



    }
}
