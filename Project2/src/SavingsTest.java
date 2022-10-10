import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class SavingsTest {

    @Test
    void monthlyInterest() {
        DecimalFormat df = new DecimalFormat("######0.00");
        Checking checking = new Checking();
        checking.setBalance(1000);
        CollegeChecking collegeChecking = new CollegeChecking(0);
        collegeChecking.setBalance(2000);
        Savings savings = new Savings(1);
        savings.setBalance(6700);
        MoneyMarket moneyMarket = new MoneyMarket(0);
        moneyMarket.setBalance(3500);
        assert df.format(checking.monthlyInterest()).equals("0.08");
        assert df.format(collegeChecking.monthlyInterest()).equals("0.42");
        assert df.format(savings.monthlyInterest()).equals("2.51");
        assert df.format(moneyMarket.monthlyInterest()).equals( "2.77");
    }
}