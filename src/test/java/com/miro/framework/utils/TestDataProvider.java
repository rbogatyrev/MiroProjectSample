package com.miro.framework.utils;

import com.miro.framework.enums.SignupPageValidationErrors;
import com.miro.models.User;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.miro.framework.enums.SignupPageValidationErrors.*;


public class TestDataProvider {

    public static User generateRandomUserProfile() {
        User user = new User();
        user.setName(RandomValuesGenerator.generateRandomString(8));
        user.setOrganizationName(RandomValuesGenerator.generateRandomString(8));
        user.setWorkEmail(RandomValuesGenerator.generateWorkEmail());
        user.setPassword(RandomValuesGenerator.generateGoodPassword());
        return user;
    }

    static Stream<Arguments> getUserValidationData() {
        User firstUser = generateRandomUserProfile();
        firstUser.setWorkEmail("st4my@yandex.ru");
        SignupPageValidationErrors emailAlreadyRegisteredError = EMAIL_ALREADY_REGISTERED;

        User secondUser = generateRandomUserProfile();
        secondUser.setWorkEmail("st4my@mailnator.com");
        SignupPageValidationErrors invalidEmailDomainError = INVALID_EMAIL_DOMAIN;

        User thirdUser = generateRandomUserProfile();
        thirdUser.setPassword(RandomValuesGenerator.generateRandomString(7));
        SignupPageValidationErrors passwordTooShortError = PASSWORD_TOO_SHORT;

        User forthUser = generateRandomUserProfile();
        forthUser.setName("");
        SignupPageValidationErrors nameIsEmptyError = NAME_IS_EMPTY;


        return Stream.of(
                Arguments.of(firstUser, emailAlreadyRegisteredError),
                Arguments.of(secondUser, invalidEmailDomainError),
                Arguments.of(thirdUser, passwordTooShortError),
                Arguments.of(forthUser, nameIsEmptyError)
        );
    }


}
