package Test3;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithDropdowns {
    public static void main(String[] args) {
        //l’URL de la page de test
        String selectURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";


        //launch browser
        Page page = Playwright.create().chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        ).newPage();

        //Navigation to the URL
        page.navigate(selectURL);


        Locator Daylocator = page.locator("//select[@id='select-demo']");

        //select By value
        Daylocator.selectOption("Sunday");

        //Checking the text displayed after selection
        Locator result = page.locator("//p[@class='selected-value text-size-14']");
        assertThat(result).containsText("Sunday");
        System.out.println(result.textContent());

        //select By index
        Daylocator.selectOption(new SelectOption().setIndex(3));

        //Checking the text displayed after selection
        Locator result1 = page.locator("//p[@class='selected-value text-size-14']");
        assertThat(result1).containsText("Tuesday");
        System.out.println(result1.textContent());

        //select multiple
        Locator states = page.locator("//select[@id='multi-select']");

        //Select three options simultaneously
        states.selectOption(new String[]{"California", "New York", "Washington"});
        //Retrieving and displaying selected options
        Locator options = states.locator("option");
        System.out.println(options.count());

        //Retrieves the list of option texts.
        //Prints each option to the console.
        List<String> allstates = options.allInnerTexts();
        allstates.forEach(option -> System.out.println(option));

        page.close();


    }
}
