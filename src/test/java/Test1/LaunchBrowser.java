package Test1;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {
        // Création d'une instance de Playwright
        Playwright playwright = Playwright.create();

        // Lancement d'un navigateur Chromium en mode non headless (affichage visible du navigateur)
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        // Création d'une nouvelle page dans le navigateur
        Page page = browser.newPage();

        // Navigation vers le site spécifié
        page.navigate("https://ecommerce-playground.lambdatest.io/");

        // Clic sur le bouton "My account" pour accéder à la page de connexion
        page.click("//a[@role='button']//span[@class='title'][normalize-space()='My account']");

        // Vérification que la page a bien le titre "Account Login"
        assertThat(page).hasTitle("Account Login");

        // Saisie de l'adresse e-mail dans le champ correspondant
        page.getByPlaceholder("E-Mail Address").type("koushik350@gmail.com");

        // Saisie du mot de passe dans le champ correspondant
        page.getByPlaceholder("Password").type("Pass123$");

        // Clic sur le bouton "Login" pour se connecter
        page.locator("//input[@value='Login']").click();

        // Vérification que la page après connexion a bien le titre "My Account"
        assertThat(page).hasTitle("My Account");

        // Affichage d'un message de confirmation de connexion réussie dans la console
        System.out.println("Login successful");

        // Fermeture de la page actuelle
        page.close();

        // Fermeture du navigateur
        browser.close();

        // Fermeture de l'instance Playwright
        playwright.close();
    }
}
