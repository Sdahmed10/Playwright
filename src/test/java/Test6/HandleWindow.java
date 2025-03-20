package Test6;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HandleWindow {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        assertThat(page).hasTitle("Selenium Grid Online | Run Selenium Test On Cloud");
        System.out.println(page.title());

        Page popup = page.waitForPopup(() -> {
            page.getByText("Follow On Twitter").click();
        });
        popup.waitForLoadState();
        assertThat(popup).hasTitle("LambdaTest (@lambdatesting) / X");
        System.out.println(popup.title());

        popup.getByText("Log in").click();

        page.close();
        browser.close();
        playwright.close();


    }
}
