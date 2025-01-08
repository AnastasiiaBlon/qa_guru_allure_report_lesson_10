package guru.qa;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {
    private static String REPOSITORY = "AnastasiiaBlon/qa_guru_files_tests";
    private static String TEXT = "Welcome to issues!";

    @Test
    @Feature("Issue in repository")
    @Story("Issue creation")
    @Owner("AnastasiiaBlon")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Issue creation for logged in user")
    public void testStaticLabels() {

    }

    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Issue creation for logged in user")
        );

        Allure.feature("Issue in repository");
        Allure.story("Issue creation");
        Allure.label("owner", "AnastasiiaBlon");
        Allure.label("severity", SeverityLevel.CRITICAL.value());

    }
}
