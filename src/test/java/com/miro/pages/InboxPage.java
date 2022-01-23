package com.miro.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.miro.framework.utils.SimpleWait;
import com.miro.models.User;
import com.miro.pages.popups.InboxAccountsListPopover;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.miro.framework.enums.LocalNames.INBOX_PAGE_REFRESH_BUTTON;
import static com.miro.framework.enums.Urls.INBOX_PAGE_RU;
import static java.lang.String.format;

public class InboxPage extends BasePage {

    private static final String MAIN_ELEMENT = "a[aria-label='Mail.tm']";
    private static final String REFRESH_BUTTON = "//a[contains(text(),'%s')]";
    private static final String EMAIL_SUBJECT = "//div[contains(text(),\"%s\")]";
    private static final String MESSAGE_FRAME = "iframe:not(style)";
    private static final String PROFILE_MENU = "#accounts-menu img";
    private static final String EMAIL_CONFIRM_BUTTON = "//a[contains(@href,'confirm-email')]";
    private static final String CURRENT_EMAIL_INFO = "input#address";

    private InboxAccountsListPopover inboxAccountsListPopover = new InboxAccountsListPopover(this);
    private WelcomePage welcomePage = new WelcomePage();

    @Override
    protected String getMainPageElement() {
        return MAIN_ELEMENT;
    }

    @Override
    protected String getPageUrl() {
        return INBOX_PAGE_RU.getValue();
    }

    @Step("Create a new email account")
    public InboxPage createNewInbox(@NotNull User user) {
        $(PROFILE_MENU).shouldBe(visible).click();
        inboxAccountsListPopover.clickCreateAccountButton()
                .setEmail(user.getWorkEmail())
                .setPassword(user.getPassword())
                .clickCreateButton();
        user.setWorkEmail(getCurrentEmailInfo());
        return this;
    }

    @Step("Wait till message with subject '{emailSubject}' is received")
    public InboxPage waitForMessageWithSubjectReceived(String emailSubject) {
        int attempts = 0;
        do {
            SimpleWait.waitSec(3);
            $x(format(REFRESH_BUTTON, INBOX_PAGE_REFRESH_BUTTON.getValue())).click();
            attempts++;
            $x(format(EMAIL_SUBJECT, emailSubject)).click();
        }
        while (!($x(format(EMAIL_SUBJECT, emailSubject)).is(visible)) && attempts < 10);
        return this;
    }

    @Step("Follow the email confirmation link from email message body")
    public WelcomePage clickOnEmailConfirmLink() {
        $(MESSAGE_FRAME).scrollIntoView(true);
        Selenide.switchTo().frame($(MESSAGE_FRAME));
        WebDriverRunner.getWebDriver().navigate().to($x(EMAIL_CONFIRM_BUTTON).getAttribute("href"));
        Selenide.switchTo().defaultContent();
        return welcomePage;
    }

    public String getCurrentEmailInfo() {
        return $(CURRENT_EMAIL_INFO).getValue();
    }

}
