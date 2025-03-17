package Test1;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        page.click("//a[@role='button']//span[@class='title'][normalize-space()='My account']");
        assertThat(page).hasTitle("Account Login");
        page.getByPlaceholder("E-Mail Address").type("koushik350@gmail.com");
        page.getByPlaceholder("Password").type("Pass123$");
        page.locator("//input[@value='Login']").click();
        assertThat(page).hasTitle("My Account");
        System.out.println("Login successful");
        page.close();
        browser.close();
        playwright.close();
    }
}
