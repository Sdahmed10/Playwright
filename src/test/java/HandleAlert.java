import com.microsoft.playwright.*;

public class HandleAlert {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            //JS Alerts, Prompt ,Confirmation popups
            page.onDialog(dialog -> {
                String text = dialog.message();
                System.out.println(text);
                dialog.accept("Hi Im Sdiri Ahmed");

            });

            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            //JS Alerts ,Confirmation popups
            page.locator("//button[normalize-space()='Click for JS Alert']").click();
            String result = page.textContent("//p[@id='result']");
            System.out.println(result);

            Thread.sleep(2000);

            page.locator("//button[normalize-space()='Click for JS Confirm']").click();
            String result1 = page.textContent("//p[@id='result']");
            System.out.println(result1);

            Thread.sleep(2000);

            //prompt
            page.locator("//button[normalize-space()='Click for JS Prompt']").click();
            String result2 = page.textContent("//p[@id='result']");
            System.out.println(result2);

            Thread.sleep(2000);

            page.close();
            browser.close();
            playwright.close();


        }
    }
