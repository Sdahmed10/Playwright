package TakiAcademyLeaveRequest;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.util.regex.Pattern;


public class CodeGenLeaveRequest {
    public static void main(String[] args) throws InterruptedException {
        // Création de l'instance Playwright
        try (Playwright playwright = Playwright.create()) {
            // Lancement du navigateur Chromium en mode non-headless (interface visible)
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            // Création d'un nouveau contexte de navigation avec résolution 1920x1080
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080));
            // Ouverture d’un nouvel onglet (Page)
            Page page = context.newPage();
            page.navigate("https://devleaverequest.softylines.com/sign-in");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email Address")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email Address")).fill("superadmin@gmail.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("123456789");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            // Accès au tableau de bord après connexion
            page.getByText("Dashboard").click();
            // Clic sur le bouton "Add New Employee" (avec une icône encodée en base64)
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAWCAYAAADAQbwGAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAADnSURBVHgB1ZVvDYMwEMVfMTAkIAEJOAAHQ8IkMAVIYA4mgTkYOKiETcHtGsrSjHb9E/Zhv+Romtx7TZp3RcADEeW8lHorhRASqbBZT1t6pMDCgdwMsWYN+als2szhWcHPMcYwRyIuwwf8PBGKup+AOywQAwu6L2YdUmBhy3U3jEaVAPw1wtzosBZYYnPjuZ1sIt2n5lulYTL7hG5QBldsAy2VgGvmOuiD1kNNLlzn98PxcfGpjKtZS/vRZAib21DqDDvzE0OJ/ZjX2Jx4qbHEokQcEjpaHJtO2DpoyWUB97so1cf2w3oBWRh3dfhvoBEAAAAASUVORK5CYII= Add New Employee").setExact(true)).click();
            // Ouverture du calendrier pour sélectionner la date d'anniversaire
            page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Birthday$"))).getByTestId("datepicker-input").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Year Selector")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("1982")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Month Selector")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("April")).click();
            page.getByRole(AriaRole.GRIDCELL, new Page.GetByRoleOptions().setName("Thursday, 15 April")).click();
            Thread.sleep(3000);
        }
    }
}