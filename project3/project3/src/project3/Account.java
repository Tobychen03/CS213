package project3;

/**
 * Account class
 * change an account information
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;
    protected int numberofwithdraw;

    /**
     * get number of withdraw
     * @return numberofwithdraw
     */
    public int getNumberofwithdraw(){
        return numberofwithdraw;
    }

    /**
     * get holder information
     * @return holder
     */
    public Profile getHolder(){return holder;}

    /**
     * determine whether closed
     * @return closed
     */
    public boolean getClosed(){return closed;}

    /**
     * get balance
     * @return balance
     */
    public double getBalance(){return balance;}

    /**
     * set holder's information
     * @param holder
     */
    public void setHolder(Profile holder) {
        this.holder = holder;
    }

    /**
     * set whether account is closed
     * @param closed
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * set balance
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Determine whether it is the same account
     * @param obj account
     * @return true if same, false if different
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null){
            return false;
        }
        Account id = (Account) obj;
        String a = getType();
        String b = id.getType();
        if(getHolder().equals(id.getHolder()) && (a.equals(b) || (a.contains("Checking") && b.contains("Checking")))){
            return true;
        }

        return false;
    }

    /**
     * String the account type and holder information and balance
     * @return type, holder's information and balance
     */
    @Override
    public String toString() {
        String custom = holder.toString();
        String money = "Balance $" + String.format("%.2f", balance);
        String type = getType();
        return type + "::" + custom + "::" + money;
    }

    /**
     * withdraw money and subtract from balance
     * @param amount money need withdraw
     */
    public void withdraw(double amount) {
        numberofwithdraw = numberofwithdraw + 1;
        this.balance = this.balance - amount;
    }

    /**
     * deposit money and add to balance
     * @param amount money need deposit
     */
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    public abstract double monthlyInterest(); //return the monthly interest

    public abstract double fee(); //return the monthly fee

    public abstract String getType(); //return the account type (class name)
}