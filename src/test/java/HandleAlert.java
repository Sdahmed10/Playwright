import com.microsoft.playwright.*;

public class HandleAlert {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");

            // Gérer toutes les boîtes de dialogue
            page.onDialog(dialog -> {
                System.out.println("Alerte détectée: " + dialog.message());
                dialog.accept("Test Playwright");
            });

            // Récupérer tous les boutons ayant "Click Me"
            Locator buttons = page.locator("text='Click Me'");

            // 1. Cliquer sur la première alerte simple
            buttons.nth(0).click();

            // 2. Cliquer sur la boîte de confirmation
            buttons.nth(1).click();
            System.out.println("Message après confirmation: " + page.locator("#confirm-demo").textContent());

            // 3. Cliquer sur la boîte de prompt
            buttons.nth(2).click();
            System.out.println("Message après prompt: " + page.locator("#prompt-demo").textContent());

            page.waitForTimeout(3000);
            browser.close();
        }
    }
}
