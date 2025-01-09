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
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    @DisplayName("Pure Selenide test number one (with Listener)")
    public void testEmptyPageTextSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-button").click();
        $("#query-builder-test").setValue("AnastasiiaBlon/qa_guru_files_tests").pressEnter();
        $("a[href='/AnastasiiaBlon/qa_guru_files_tests']").click();
        $("span[data-content='Issues']").click();
        $(".container-md").shouldHave(text("Welcome to issues!"));
    }

    @Test
    @DisplayName("Pure Selenide test number two (with Listener)")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-button").click();
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("span[data-content='Issues']").click();
        $(withText("#95")).should(Condition.exist);
    }
}
