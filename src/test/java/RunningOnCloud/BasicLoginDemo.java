package RunningOnCloud;

import com.google.gson.JsonObject;
import com.microsoft.playwright.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BasicLoginDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        JsonObject capabilities = new JsonObject();
        JsonObject ltOptions = new JsonObject();

        String user = "s.ahmed198894";
        String accessKey = "LT_CQcBhG9IBVrsy9OVETCxQkhFRn60s7lIRfrGRVhm7MvpRVr";

        capabilities.addProperty("browserName", "Chrome"); // correction de l'orthographe
        capabilities.addProperty("browserVersion", "latest"); // correction de l'orthographe
        ltOptions.addProperty("platform", "macOS Sonoma"); // correction ici, "macOS Sonoma" devient "macOS 14"
        ltOptions.addProperty("name", "Playwright Test");
        ltOptions.addProperty("build", "Playwright Java Build 2");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        capabilities.add("ltOptions", ltOptions);

        // Playwright test
        Playwright playwright = Playwright.create();
        BrowserType chrome = playwright.chromium();
        String caps = URLEncoder.encode(capabilities.toString(), "UTF-8");
        String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + caps;
        Browser browser = chrome.connect(cdpUrl);
        Page page = browser.newPage();
        try {

            page.navigate("https://duckduckgo.com/");
            Locator locator = page.locator("//input[@id='searchbox_input']");
            locator.click();
            locator.fill("LambdaTest");
            page.keyboard().press("Enter");
            String title = page.title();

            if (title.equals("LambdaTest at DuckDuckGo")) {
                // use the following code to mark the test status
                setTestStatus("passed", "Title matched", page);
            } else {
                setTestStatus("failed", "Title did not match", page);
            }
            browser.close();
            playwright.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setTestStatus(String status, String remark, Page page) {
        page.evaluate("_ => {}",
                "lambdatest_action: { \"action\": \"setTestStatus\", \"arguments\": { \"status\": \"" + status
                        + "\", \"remark\": \"" + remark + "\"}}");
    }
}
