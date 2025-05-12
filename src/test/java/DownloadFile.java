import com.microsoft.playwright.*;

import java.awt.*;

public class DownloadFile {
    public static void main(String[] args) {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screensize.width;
        double height = screensize.height;
        System.out.println(width + "x" + height);
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize((int) width, (int) height));

        Page page = context.newPage();
        page.navigate("https://chromedriver.storage.googleapis.com/index.html?path=100.0.4896.20/");

        Download download = page.waitForDownload(() -> {
            page.click("//a[normalize-space()='chromedriver_mac64.zip']");
        });

        System.out.println(download.url());
        //System.out.println(download.page().title());

        String path = download.path().toString();
        System.out.println(path);


    }
}
