package Test7;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleBrowser {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        // Chromium browser
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );


        // First context and page
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByPlaceholder("E-Mail Address").fill("koushik350@gmail.com");
        page.getByPlaceholder("Password").fill("Pass123$");
        page.locator("//input[@value='Login']").click();
        assertThat(page).hasTitle("My Account");
        System.out.println("Login successful");

        //how to open new tab
        Page newTab = page.context().newPage();
        newTab.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        assertThat(newTab).hasTitle("My Account");
        System.out.println("Redirection successfully");
        newTab.close();
        context.close();

        //how to open new context
        BrowserContext newContext = browser.newContext();
        Page newTab2 = newContext.newPage();
        newTab2.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        assertThat(newTab2).hasTitle("Account Login");
        System.out.println("new context opened successfully");
        newTab2.close();
        newContext.close();

        // Firefox browser
        Browser firefox =  playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page firefoxPage = firefox.newPage();
        firefoxPage.navigate("https://ecommerce-playground.lambdatest.io");
        System.out.println("Firefox page title: " + firefoxPage.title());
        firefoxPage.close();
        firefox.close();


        browser.close();
        playwright.close();


    }
}
