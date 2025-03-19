package test4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.Arrays;

public class learnScreenshots {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        System.out.println(page.title());

        //screenshots
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./scre/scr.png")));

        //full page screenshots
        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./scre/fullpage.png")));

        //locator screenshot
        Locator BookADemoButton = page.locator("//button[normalize-space()='Book a Demo']");
        BookADemoButton.screenshot(new Locator.ScreenshotOptions()
                .setPath((Paths.get("./scre/locator.png"))));

        //masking locator
        Locator message = page.locator("//input[@id='user-message']");
        message.type("S.Ahmed");
        message.screenshot(new Locator.ScreenshotOptions()
                .setPath((Paths.get("./scre/masking.png"))));

        page.screenshot(screenshotOptions.setPath(Paths.get("./scre/masking1.png"))
                .setFullPage(false)
                .setMask(Arrays.asList(message))
        );

        //Caret or cursor show/hide
        message.click();
        page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./scre/hide.jpg")));
        page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.INITIAL)
                .setPath(Paths.get("./scre/init.jpg")));


        page.close();
        playwright.close();

    }
}
