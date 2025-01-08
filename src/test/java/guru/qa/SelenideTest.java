package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-button").click();
        $("#query-builder-test").setValue("AnastasiiaBlon/qa_guru_files_tests").pressEnter();
        $("a[href='/AnastasiiaBlon/qa_guru_files_tests']").click();
        $("span[data-content='Issues']").click();
        $(".container-md").shouldHave(text("Welcome to issues!"));
    }
}
