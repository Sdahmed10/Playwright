import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class videoRecording {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("myvideos/")));
        Page page = browserContext.newPage();
        //Go to the naveen academy page
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
        browserContext.storageState(new BrowserContext.StorageStateOptions()
                .setPath(Paths.get("applogin.json")));

        System.out.println("Auth state saved to applogin.json");

        page.close();
        browserContext.close();
        playwright.close();


    }
}
