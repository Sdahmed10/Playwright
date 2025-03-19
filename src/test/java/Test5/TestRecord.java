package Test5;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;


public class TestRecord {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("video/"))
        );
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
        Locator MyAccount = page.locator("//a[@role='button']//span[@class='title'][normalize-space()='My account']");
        MyAccount.hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByPlaceholder("E-Mail Address").fill("koushik350@gmail.com");
        page.getByPlaceholder("Password").fill("Pass123$");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./Record/Login.png")));
        System.out.println("Login successful");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit your account")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).fill("Ahmed");
        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./Record/Edit lastname.png")));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
        page.getByText("Success: Your account has").click();
        System.out.println("Success: Your account has updated");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Logout")).click();
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account Logout")).click();
        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./Record/Logout.png")));
        System.out.println("Logout Successful");
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}



