package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;


    /**
     * Basic constructor for setting up account services
     */
    public AccountService() {
        this.accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    /**
     * Uses accountDAO to login user
     * 
     * @param account
     * @return
     */
    public Account loginAccount(Account account) {
        return accountDAO.loginAccount(account);
    }

    public String getAccount(Account account) {
        return accountDAO.getAccount(account);
    }

    public Account registerAccount(Account account) {
        return accountDAO.registerAccount(account);
    }




}
