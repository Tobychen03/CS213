import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseTest {

    @Test
    void open() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Account account1 = new Checking();
        account1.setHolder(new Profile("Don", "Mo", new Date("3/2/1998")));
        account1.setBalance(1000);
        assert  accountDatabase.open(account1) == true;
        assert  accountDatabase.open(account1) == false;

        Account account2= new CollegeChecking(0);
        account2.setHolder(new Profile("Don", "Mo", new Date("3/2/1998")));
        account2.setBalance(1000);
        assert  accountDatabase.open(account2) == true;
        assert  accountDatabase.open(account2) == false;

        Account account3= new CollegeChecking(0);
        account3.setHolder(new Profile("Don", "Moe", new Date("3/2/1998")));
        account3.setBalance(1000);
        assert  accountDatabase.open(account3) == true;
        assert  accountDatabase.open(account3) == false;
    }

    @Test
    void close() {
        AccountDatabase accountDatabase = new AccountDatabase();
        Account account1 = new Checking();
        account1.setHolder(new Profile("Don", "Mo", new Date("3/2/1998")));
        account1.setBalance(1000);
        assert  accountDatabase.close(account1) == true;
        assert  accountDatabase.close(account1) == false;

        Account account2= new CollegeChecking(0);
        account2.setHolder(new Profile("Don", "Mo", new Date("3/2/1998")));
        account2.setBalance(1000);
        assert  accountDatabase.close(account2) == true;
        assert  accountDatabase.close(account2) == false;

        Account account3= new CollegeChecking(0);
        account3.setHolder(new Profile("Don", "Moe", new Date("3/2/1998")));
        account3.setBalance(1000);
        assert  accountDatabase.close(account3) == true;
        assert  accountDatabase.close(account3) == false;
    }
}