package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTests {

    private static String REPOSITORYBLON = "AnastasiiaBlon/qa_guru_files_tests";

    private static String REPOSITORYER = "eroshenkoam/allure-example";
    private static String TEXT = "Welcome to issues!";

    private static int ISSUE = 95;

    @Test
    @DisplayName("Lambda steps via  step (name, () -> {})")
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Home page", () -> {
            open("https://github.com/");
        });

        step("Search for repository " + REPOSITORYBLON, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORYBLON).pressEnter();
        });

        step("Click on repository link " + REPOSITORYBLON, () -> {
            $(linkText(REPOSITORYBLON)).click();
        });

        step("Open Issues tab ", () -> {
            $("span[data-content='Issues']").click();
        });

        step("Check text on empty issues page " + TEXT, () -> {
            $(".container-md").shouldHave(text(TEXT));
        });
    }

    @Test
    @DisplayName("Steps with @Step annotation")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openHomePage();
        steps.searchForRepository(REPOSITORYBLON);
        steps.clickOnRepositoryLink(REPOSITORYBLON);
        steps.openIssuesTab();
        steps.shouldSeeEmptyPageText(TEXT);
    }

    @Test
    @DisplayName("Steps with @Step annotation for issue number search")
    public void testAnnotatedStepForIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openHomePage();
        steps.searchForRepository(REPOSITORYER);
        steps.clickOnRepositoryLink(REPOSITORYER);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

    @Test
    @DisplayName("Lambda steps via  step (name, () -> {}) for issue number search")
    public void testLambdaStepForIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Home page", () -> {
            open("https://github.com/");
        });

        step("Search for repository " + REPOSITORYER, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORYER).pressEnter();
        });

        step("Click on repository link " + REPOSITORYER, () -> {
            $(linkText(REPOSITORYER)).click();
        });

        step("Open Issues tab ", () -> {
            $("span[data-content='Issues']").click();
        });

        step("Check issue with number " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }
}
