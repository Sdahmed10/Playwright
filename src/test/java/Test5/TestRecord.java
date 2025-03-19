package Test5;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;


public class TestRecord {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail Address")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail Address")).fill("koushik350@gmail.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Pass123$");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            System.out.println("Login successful");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Edit your account")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).fill("Chaterjee1234");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            page.getByText("Success: Your account has").click();
            System.out.println("Success: Your account has updated");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Logout")).click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(" Account Logout")).click();
            System.out.println("Logout Successful");
            page.close();
        }
    }
}


