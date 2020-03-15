package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private static final int MINIMUM_ACCOUNT_NAME_LENGTH = 5;
    private static final int MINIMUM_PASSWORD_NAME_LENGTH = 8;

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validationAccount(account);
        addInformation(account);
        accountManager.createNewAccount(account);
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

    private boolean validationName(Account account) {
        if (account.getName().length() > MINIMUM_ACCOUNT_NAME_LENGTH) {
            return true;
        } else {
            throw new WrongAccountNameException();
        }
    }

    private boolean validationPassword(Account account) {
        if (account.getPassword().length() > MINIMUM_PASSWORD_NAME_LENGTH && passwordChecker.validate(password) == OK) {
            return true;
        } else {
            throw new WrongPasswordException();
        }
    }

    private boolean validationAccount(Account account){
        return (validationName(account) && validationPassword(account));
    }

    private void addInformation(Account account){
        account.setCreatedDate(new Date());
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }
}
