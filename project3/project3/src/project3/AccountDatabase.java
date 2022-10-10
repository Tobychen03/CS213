package project3;

import java.text.DecimalFormat;

/**
 * AccountDatabase class
 * Store account and modify
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class AccountDatabase {
    private Account[] accounts;
    private int numAcct;

    public AccountDatabase() {
        numAcct = 0;
        accounts = new Account[4];
    }

    /**
     *
     * @param account find account
     * @return find return index, return -1 if not found
     */
    private int find(Account account) {
        //System.out.println(account + "=====");
        for (int i = 0; i < numAcct; i++) {
           // System.out.println(accounts[i] + "----equals:" + account.equals(accounts[i]));
            if (account.equals(accounts[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Dynamically increase the capacity of the database, each time by 4
     */

    private void grow() {
        if (numAcct == accounts.length) {
            Account[] accounts_tem = new Account[accounts.length + 4];
            for (int i = 0; i < numAcct; i++) {
                accounts_tem[i] = accounts[i];
            }
            accounts = accounts_tem;
        }
    }

    /**
     * Perform open operation on the account
     * @param account
     * @return Success returns true, failure returns false
     */

    public boolean open(Account account) {
        //deposit in database
        int idx = find(account);
        if (numAcct == accounts.length) grow();

        if (idx == -1) {
            accounts[numAcct++] = account;
        } else {
            if (!accounts[idx].closed) return false; //Already exists and not closed
            else {
                //if closed, reopen must be same
                if (accounts[idx].getType().equals(account.getType())) {
                    accounts[idx] = account;
                    System.out.println("Account reopened.");
                    return true;
                } else {
                    //different type
                    return false; //
                }
            }
        }
        System.out.println("Account opened.");
        return true;
    }

    /**
     * Close the account
     * @param account
     * @return true if succeed, false if failed
     */

    public boolean close(Account account) {
        //close account
        int idx = find(account);
        if (idx == -1) return false;
        accounts[idx].balance = 0;
        if (accounts[idx].closed) return false; //close again
        accounts[idx].closed = true; //has closed
        return true;
    }

    /**
     * Perform a deposit operation on the account
     */

    public void deposit(Account account) {
        int idx = find(account);
        if (idx == -1) return;
        accounts[idx].balance += account.balance;
    }

    /**
     * Perform a withdraw operation on the account
     * @param account
     * @return true if succeed, false if failed
     */

    public boolean withdraw(Account account) {
        int idx = find(account);
        if (idx == -1) return false;
        if (accounts[idx].balance < account.balance) return false;
        accounts[idx].balance -= account.balance;
        accounts[idx].numberofwithdraw += 1;
        return true;
    } //return false if insufficient fund

    /**
     * output each account in the database
     */

    public void print() {
        if (numAcct == 0) {
            System.out.println("Account Database is empty!");
            return;
        }
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
    }
    public String print_str() {
        if (numAcct == 0) {
            return("Account Database is empty!\n");
        }
        String str = "";
        for (int i = 0; i < numAcct; i++) {
            str += (accounts[i].toString()) + "\n";
        }
        return str;
    }
    /**
     * output the accounts in the database and sort them by the type of account
     */

    public void printByAccountType() {
        if (numAcct == 0) {
            System.out.println("Account Database is empty!");
            return;
        }
        //sort by AccountType
        for (int i = 0; i < numAcct; i++) {
            int idx = i;
            for (int j = i + 1; j < numAcct; j++) {
                if (accounts[j].getType().compareTo(accounts[idx].getType()) < 0) {
                    idx = j;
                }
            }
            Account account_tem = accounts[idx];
            accounts[idx] = accounts[i];
            accounts[i] = account_tem;
        }
        print();
    }
    public String printByAccountType_str() {
        if (numAcct == 0) {
            return ("Account Database is empty!");
        }
        //sort by AccountType
        for (int i = 0; i < numAcct; i++) {
            int idx = i;
            for (int j = i + 1; j < numAcct; j++) {
                if (accounts[j].getType().compareTo(accounts[idx].getType()) < 0) {
                    idx = j;
                }
            }
            Account account_tem = accounts[idx];
            accounts[idx] = accounts[i];
            accounts[i] = account_tem;
        }
        return print_str();
    }


    /**
     * output the accounts in the database, and output fee and interest
     */

    public void printFeeAndInterest() {
        if (numAcct == 0) {
            System.out.println("Account Database is empty!");
            return;
        }
        //Checking::April March 1/15/1987::Balance $950.00::fee $25.00::monthly interest $0.08
        DecimalFormat df = new DecimalFormat("######0.00");
        for (int i = 0; i < numAcct; i++) {
            System.out.print(accounts[i]);
            System.out.print("::fee $" + df.format(accounts[i].fee()));
            System.out.print("::monthly interest $" + df.format(accounts[i].monthlyInterest()));
            System.out.println();
        }
    }

    public String printFeeAndInterest_str() {
        if (numAcct == 0) {
            return("Account Database is empty!");
        }
        String str = "";
        //Checking::April March 1/15/1987::Balance $950.00::fee $25.00::monthly interest $0.08
        DecimalFormat df = new DecimalFormat("######0.00");
        for (int i = 0; i < numAcct; i++) {
            str += (accounts[i].toString());
            str +=("::fee $" + df.format(accounts[i].fee()));
            str += ("::monthly interest $" + df.format(accounts[i].monthlyInterest()));
            str += "\n";
        }
        return str;
    }


    /**
     *  Execute the account update operation of the UB command
     */

    public void update() {
        //update all balances
        if (numAcct == 0) {
            return;
        }
        for (int i = 0; i < numAcct; i++) {
            accounts[i].setBalance(accounts[i].balance + accounts[i].monthlyInterest() - accounts[i].fee());
        }
    }

    /**
     * Find if the account exists in the database
     * @param account
     * @return true if exists, false if not
     */

    public boolean find2(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (account.equals(accounts[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find if the account exists in the database and must be of the exact same type
     * @param account
     * @return true if exists, false if not
     */

    public boolean find3(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (account.equals(accounts[i]) && account.getType().equals(accounts[i].getType())) {
                return true;
            }
        }
        return false;
    }
}
