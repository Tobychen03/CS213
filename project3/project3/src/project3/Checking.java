package project3;

/**
 * Checking class
 * extends Account
 * create Checking and College Checking
 * combined information
 * calculate interest and fee
 * get type
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Checking extends Account {
    private String type;
    private double Annual_Interest_Rate;
    private double Monthly_Fee;
    private int campuscode;

    /**
     * determine which campus
     * @return name of campus
     */
    public String getCampus(){
        if(campuscode == 0){
            return "NEW_BRUNSWICK";
        }else if(campuscode == 1){
            return "NEWARK";
        }
        return "CAMDEN";
    }


    /**
     * create Checking and toString() is in parent class
     */
    public Checking() {
        this.type = "Checking";
        this.Annual_Interest_Rate = 0.001;
        this.Monthly_Fee = 25;
    }

    public Checking(String type, double Annual_Interest_Rate, double Monthly_Fee, int campuscode) {
        this.type = type;
        this.Annual_Interest_Rate = Annual_Interest_Rate;
        this.Monthly_Fee = Monthly_Fee;
        this.campuscode = campuscode;
    }

    /**
     * collegechecking could use this toString()
     * @return holder's information and campus
     */
    @Override
    public String toString() {
        String info = super.toString();
        String campus = getCampus();
        if (getType() == "Checking"){
            if(super.getClosed()){
                return info + "::CLOSED";
            }else {
                return info;
            }
        }
        if(super.getClosed()){
            return info + "::CLOSED::" + campus;
        }
        return info + "::" + campus;
    }

    /**
     * monthly interest of checking and college checking
     * annual interest divided by 12
     * @return monthly interest
     */
    public double monthlyInterest() {
        return getBalance() * Annual_Interest_Rate / 12;
    }

    /**
     * account fee
     * @return fee
     */
    public double fee() {
        if(getType() == "Checking" && getBalance() >= 1000){
            return 0.00;
        }
        return Monthly_Fee;
    }

    /**
     * type of account
     * @return type
     */
    public String getType() {
        return type;
    }
}
