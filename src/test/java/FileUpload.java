import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUpload {
    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

        //input type = file
        //select one file
        page.setInputFiles("//input[@id='filesToUpload']", Paths.get("/Users/takiacademy/Downloads/Large-31.jpeg"));
        Thread.sleep(2000);
        //remove the file
        page.setInputFiles("//input[@id='filesToUpload']", new Path[0] );

        //select multiple file
        page.setInputFiles("//input[@id='filesToUpload']", new Path[]{
                Paths.get("/Users/takiacademy/Downloads/Large-31.jpeg"),
                Paths.get("/Users/takiacademy/Downloads/Selenium_Logo.png"),
                Paths.get("/Users/takiacademy/Downloads/icon.png")
        });

        Thread.sleep(2000);
        //remove the file
        page.setInputFiles("//input[@id='filesToUpload']", new Path[0] );


    }
}
