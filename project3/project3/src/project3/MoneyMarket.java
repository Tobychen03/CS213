package project3;

/**
 * MoneyMarket class
 * extends Savings
 * create Money Market Account
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class MoneyMarket extends Savings {
    /**
     * create Money Market Account
     * @param numofWD
     */
    public MoneyMarket(int numofWD){
        super( "Money Market Savings", 0.008, 10, numofWD);
    }
}
