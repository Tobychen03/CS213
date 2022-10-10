package project3;

/**
 * Savings class
 * extend Account
 * create savings and money market saving
 * combined information
 * calculate interest and fee
 * get type
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Savings extends Account{
    private String type;
    private double Annual_Interest_Rate;
    private double Monthly_Fee;
    private int loyal;

    /**
     * determine whether account is loyal
     * @return
     */
    public int getLoyal(){
        if(type == "Money Market Savings" && balance < 2500){
            return 0;
        }
        return loyal;
    }

    /**
     * create Savings
     * @param loyal whether Savings's holder is loyal
     */
    public Savings(int loyal) {
        this.type = "Savings";
        this.Annual_Interest_Rate = 0.003;
        this.Monthly_Fee = 6;
        this.loyal = loyal;
    }

    /**
     * combine information
     * determined by type of account
     * then determine whether closed
     * if closed, withdraw change to 0
     * @return combined information
     */
    @Override
    public String toString() {
        String info = super.toString();
        String numofWD = Integer.toString(super.getNumberofwithdraw());
        if(getType() == "Savings" && getLoyal() == 1){
            if(super.getClosed()){
                return info + "::CLOSED";
            }else {
                return info + "::Loyal";
            }
        }
        if(getType() == "Money Market Savings"){
            if(getLoyal() == 1){
                if(super.getClosed()){
                    return info + "::CLOSED::withdrawl:0";
                }else{
                    return info + "::Loyal::withdrawl:" + numofWD;
                }
            }else {
                if(super.getClosed()){
                    return info + "::CLOSED::withdrawl:0";
                }else{
                    return info + "::withdrawl:" + numofWD;
                }
            }
        }
        if(super.getClosed()) {
            return info + "::CLOSED";
        }
        return info;
    }


    /**
     * create MoneyMarketSaving
     * default loyal
     * @param type account type
     * @param Annual_Interest_Rate interest rate
     * @param Monthly_Fee   fee
     */
    public Savings(String type, double Annual_Interest_Rate, double Monthly_Fee, int numofWD) {
        this.type = type;
        this.Annual_Interest_Rate = Annual_Interest_Rate;
        this.Monthly_Fee = Monthly_Fee;
        this.loyal = 1;
        numberofwithdraw = numofWD;
    }

    /**
     * calculate monthly interest
     * determined by whether is loyal and type
     * @return monthly interest
     */
    public double monthlyInterest() {
        if(getLoyal() == 1 && getType() == "Savings"){
            this.Annual_Interest_Rate = 0.0045;
        }
        if(getLoyal() == 1 && getType() == "Money Market Savings"){
            this.Annual_Interest_Rate = 0.0095;
        }else {
            this.Annual_Interest_Rate = 0.008;
        }
        return getBalance() * Annual_Interest_Rate / 12;
    }

    /**
     * calculate fee
     * if it is saving, balance greater than 300 , fee is 0
     * if is Money Market Saving, withdraw greater than 3, need fee
     * balance greater than 2500, fee is 0
     * @return fee
     */
    public double fee() {
        int waivedSaving = 300;
        int tooMuchWithdraw = 3;
        int waivedMMS = 2500;
        if(getType() == "Savings" && getBalance() >= waivedSaving){
            return 0.00;
        }
        if(getType() == "Money Market Savings"){
            if(super.getNumberofwithdraw() > tooMuchWithdraw){
                return Monthly_Fee;
            }
            if (super.getNumberofwithdraw() <= tooMuchWithdraw && getBalance() >= waivedMMS){
                return 0.00;
            }
        }
        return Monthly_Fee;
    }

    /**
     * get type of account
     * @return type
     */
    public String getType() {
        return type;
    }
}
