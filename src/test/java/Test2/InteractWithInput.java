package Test2;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithInput {
    public static void main(String[] args) {
        //Crée une instance de Playwright  
        Playwright playwright = Playwright.create();


        //Lance un navigateur Chromium en mode non-headless
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        //Crée une nouvelle page dans le navigateur.
        Page page = browser.newPage();

        //Ouvre une page web
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");


        //Click Action
        //Saisit le texte "Hello World"
//        page.locator("//input[@id='user-message']").type("Hello World");
        page.locator("//input[@id='user-message']").fill("Hello World");
//
        //Clique sur le bouton pour afficher le message saisi.
        page.locator("//button[@id='showInput']").click();

        //Récupère le texte affiché après l'envoi du message.
        String message = page.locator("//p[@id='message']").textContent();

        //Affiche le message récupéré dans la console.
        System.out.println("Message: " + message);

        //Type vs Fill
        //Navigue vers une autre page
        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");

        //Tape "Hello World" caractère par caractère
        page.locator("//textarea[@id='textbox']").type("Hello World");
        //page.locator("//textarea[@id='textbox']").fill("Hello World");


        //get input values
        page.navigate("https://letcode.in/edit");

        // Récupère la valeur pré-remplie d'un champ de texte.
        String inputValue = page.locator("//input[@id='getMe']").inputValue();
        System.out.println(inputValue);

        //Récupère l'attribut placeholder d'un champ de texte
        String xpathValue = page.getByPlaceholder("Enter first & last name").getAttribute("placeholder");

        //Affiche le placeholder récupéré dans la console.
        System.out.println("placeholder is: " + xpathValue);

        page.locator("//input[@id='clearMe']").clear();

        //how to handle checkbox
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        //Localise la case à cocher 
        Locator isAge = page.locator("//input[@id='isAgeSelected']");

        //Vérifie que la case à cocher n'est pas cochée au départ
        assertThat(isAge).not().isChecked();

        //Coche la case à cocher.
        isAge.check();

        // Vérifie que la case est bien cochée après l'action.
        assertThat(isAge).isChecked();

        //Ferme Playwright
        playwright.close();
    }
}
