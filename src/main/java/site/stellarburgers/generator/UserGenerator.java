package site.stellarburgers.generator;

import site.stellarburgers.pojo.LoginUser;
import site.stellarburgers.pojo.RegisterUser;

public class UserGenerator {

    final static String DEFAULT_EMAIL = "stdr8@test.ru";
    final static String DEFAULT_PASSWORD = "123";
    final static String DEFAULT_NAME = "Seva";
    final static String FAKE_EMAIL = "dfjzgzn4564dnbxfgb@ffg435gb.r7u6tr";
    final static String NEW_EMAIL = "jfdjxivnjdf"+DEFAULT_EMAIL;
    final static String NEW_PASSWORD = "12345";
    final static String NEW_NAME = "aveS";

    public enum UserField {
        EMAIL,
        PASSWORD,
        NAME
    }

    public static RegisterUser getDefaultRegistrationData() {
        return new RegisterUser(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_NAME);
    }

    public static RegisterUser getRegistrationDataWithOneEmptyField(UserField emptyField) {
        RegisterUser data = null;
        switch (emptyField) {
            case EMAIL:
                data = new RegisterUser("", DEFAULT_PASSWORD, DEFAULT_NAME);
                break;
            case PASSWORD:
                data = new RegisterUser(DEFAULT_EMAIL, "", DEFAULT_NAME);
                break;
            case NAME:
                data = new RegisterUser(DEFAULT_EMAIL, DEFAULT_PASSWORD, "");
                break;
        }
        return data;
    }

    public static LoginUser getDefaultLoginData() {
        return new LoginUser(DEFAULT_EMAIL, DEFAULT_PASSWORD);
    }

    public static LoginUser getNewLoginData() {
        return new LoginUser(NEW_EMAIL, DEFAULT_PASSWORD);
    }

    public static LoginUser getFakeLoginData() {
        return new LoginUser(FAKE_EMAIL, DEFAULT_PASSWORD);
    }

    public static RegisterUser getDefaultUpdateData() {
        return new RegisterUser(NEW_EMAIL, NEW_PASSWORD, NEW_NAME);
    }
}

