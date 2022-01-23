package com.miro.tests;

import com.miro.framework.enums.SignupPageValidationErrors;
import com.miro.framework.utils.DriverHelper;
import com.miro.framework.utils.TestDataProvider;
import com.miro.models.User;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

public class SignUpTest extends BaseTest {

    private User user;

    @BeforeEach
    public void precondition(TestInfo testInfo) {
        if (testInfo.getTestMethod().get().getParameters().length == 0) {
            user = TestDataProvider.generateRandomUserProfile();
            logger.info(format("Created user email: %s password: %s", user.getWorkEmail(), user.getPassword()));
        }
        navigationSteps.openMiroMainPage();
        mainPageSteps.clickSignUpForFree();
    }

    @Test
    @DisplayName("Sign up a new Miro user")
    @Description("Sign up a new Miro user and go to workspace via email confirmation link")
    void shouldEnterWorkSpace_WhenRegisterNewUserAndConfirmEmail() {
        DriverHelper.switchToBlankTab();
        inboxSteps.createNewInboxAccount(user);

        switchTo().window(0);
        signUpSteps.signUpNewMiroUser(user);

        switchTo().window(1);
        inboxSteps.followLinkFromEmailConfirmationLetter();
        signUpSteps.passWelcomeQualification(user);
        boardSteps.closeTemplatePicker();

        Assert.assertTrue(format("Workspace user name doesn't equal to the expected one (%s)", user.getName()),
                boardSteps.checkWorkspaceUserName(user.getName()));

    }

    @ParameterizedTest(name = "{1} validation error display when sign up")
    @MethodSource("com.miro.framework.utils.TestDataProvider#getUserValidationData")
    void shouldDisplayValidationError_WhenSignUpWithInvalidFieldValues(User user, SignupPageValidationErrors signupPageValidationError) {
        signUpSteps.signUpNewMiroUser(user);

        Assert.assertTrue(format("'%s' error is not displayed", signupPageValidationError.getValue()),
                signUpSteps.checkFieldValidationErrorExists(
                        signupPageValidationError.getKey(),
                        signupPageValidationError.getValue()));
    }

}
