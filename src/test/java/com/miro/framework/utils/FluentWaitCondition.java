package com.miro.framework.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;

import java.time.Duration;

/**
 * Wrapper class of selenide conditions
 */
public class FluentWaitCondition {

    /**
     * Check that the element is visible within a specific explicit wait
     *
     * @param elem     SelenideElement
     * @param duration Duration of explicit wait in sec
     * @return true - element is visible, false - element is not visible within the duration time limit
     */
    public static boolean shouldBeVisible(SelenideElement elem, long duration) {
        boolean flag;
        try {
            elem.should(Condition.visible, Duration.ofSeconds(duration));
            flag = true;
        } catch (ElementNotFound | ElementShould e) {
            flag = false;
        }
        return flag;
    }

    /**
     * Check that the element is visible within 4 sec explicit wait
     *
     * @param elem SelenideElement
     * @return true - element is visible, false - element is not visible within 4 secs
     */
    public static boolean shouldBeVisible(SelenideElement elem) {
        return shouldBeVisible(elem, 4);
    }

    /***
     * Check that the element is not visible within a specific explicit wait
     *
     * @param elem SelenideElement
     * @param duration Duration of explicit wait in sec
     * @return true - element is not visible, false - element is visible within the duration time limit
     */
    public static boolean shouldNotBeVisible(SelenideElement elem, long duration) {
        boolean flag;
        try {
            elem.shouldNotBe(Condition.visible, Duration.ofSeconds(duration));
            flag = true;
        } catch (ElementShouldNot e) {
            flag = false;
        }
        return flag;
    }

    /**
     * Check that the element is not visible within 4 sec explicit wait
     *
     * @param elem SelenideElement
     * @return element is not visible, false - element is visible within 4 secs
     */
    public static boolean shouldNotBeVisible(SelenideElement elem) {
        return shouldNotBeVisible(elem, 4);
    }

}
