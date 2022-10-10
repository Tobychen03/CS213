package project3;

import javafx.scene.control.*;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Control various keys
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Controller {
    //page1
    public TextField firstname1;
    public TextField lastname1;
    public TextField amount1;
    public DatePicker dob1;
    public RadioButton checking1;
    public RadioButton collegechecking1;
    public RadioButton moneymarket1;
    public RadioButton saving1;
    public RadioButton newbrunswick;
    public RadioButton newark;
    public RadioButton camden;
    public CheckBox loyal;

    public TextArea board;



    //page 3
    public Button print;
    public Button print_type;
    public Button calculate_interests_fees;
    public Button apply_interests_fees;

    //page2
    public TextField firstname2;
    public TextField lastname2;
    public DatePicker dob2;
    public RadioButton checking2;
    public RadioButton collegechecking2;
    public RadioButton moneymarket2;
    public RadioButton saving2;
    public TextField amount2;
    Pattern p=Pattern.compile("[A-Za-z]+");
    Pattern p2=Pattern.compile("[0-9]+");
    /**
     * Click the open button
     * Determine whether an account can be opened based on the account type and user
     */
    public void open() {
        try {

            String[] args = null;
            String firstname = firstname1.getText();
            String lastname = lastname1.getText();
            String[] dateStr = dob1.getEditor().getText().split("-");
            String date = dateStr[1] + "/" + dateStr[2] + "/" + dateStr[0];
            String acount = amount1.getText();

            Matcher matcher1 = p.matcher(firstname);
            Matcher matcher2 = p.matcher(lastname);
            Matcher matcher3 = p2.matcher(acount);
            if (!matcher1.matches() ||  !matcher2.matches() || !matcher3.matches()) {
                board.setText("Only English letters can be entered\n");
                return ;
            }

            if (checking1.isSelected()) {
                args = new String[6];
                args[1] = "C";
            }
            if (collegechecking1.isSelected()) {
                args = new String[7];
                args[1] = "CC";
            }
            if (saving1.isSelected()) {
                args = new String[7];
                args[1] = "S";
            }
            if (moneymarket1.isSelected()) {
                args = new String[6];
                args[1] = "MM";
            }
            args[2] = firstname; args[3] = lastname;
            args[4] = date;
            args[5] = acount;


            String seven = null;
            if (args[1] == "S"){
                if (loyal.isSelected()) args[6] = "1";
                else args[6] = "0";
            }
            if (args[1] == "CC") {
                if (newbrunswick.isSelected()) args[6] = "0";
                if (newark.isSelected()) args[6] = "1";
                if (camden.isSelected()) args[6] = "2";
            }

            board.setText(open_str(args));
        }catch (Exception e) {
            board.setText("Error\n");
        }
    }
    /**
     * Click the close button
     * Close accounts by user and account type
     */
    public void close() {
        try {

            String[] args = new String[5];;
            String firstname = firstname1.getText();
            String lastname = lastname1.getText();
            String[] dateStr = dob1.getEditor().getText().split("-");
            String date = dateStr[1] + "/" + dateStr[2] + "/" + dateStr[0];


            Matcher matcher1 = p.matcher(firstname);
            Matcher matcher2 = p.matcher(lastname);
            if (!matcher1.matches() ||  !matcher2.matches() ) {
                board.setText("Only English letters can be entered\n");
                return ;
            }
            if (checking1.isSelected()) {
                args[1] = "C";
            }
            if (collegechecking1.isSelected()) {
                args[1] = "CC";
            }
            if (saving1.isSelected()) {
                args[1] = "S";
            }
            if (moneymarket1.isSelected()) {
                args[1] = "MM";
            }
            args[2] = firstname; args[3] = lastname;
            args[4] = date;

            board.setText(close_str(args));
        }catch (Exception e) {
            board.setText("Error\n");
        }
    }

    /**
     * Click Print All Accounts
     * print all accounts
     */
    public void print() {
        board.setText(pirnt());
    }

    /**
     * Click Calculate interests and Fees
     * print every account's fee and interest
     */
    public void calculate_interests_fees( ) {
        board.setText(printFeeAndInterest());
    }

    /**
     * Click Apply interests and Fees
     * balance minus fees and calculate interest
     */
    public void apply_interests_fees( ) {

        board.setText(update());

    }

    /**
     * Click Print All Accounts by Types
     * print all accounts by types
     */
    public void print_type( ) {
        board.setText(printByAccountType());
    }

    /**
     * Click withdraw button
     * withdraw money from account
     */
    public void withdraw( ) {
        try {
            String[] args = new String[6];;
            String firstname = firstname2.getText();
            String lastname = lastname2.getText();
            String[] dateStr = dob2.getEditor().getText().split("-");
            String date = dateStr[1] + "/" + dateStr[2] + "/" + dateStr[0];
            String amount = amount2.getText();

            Matcher matcher1 = p.matcher(firstname);
            Matcher matcher2 = p.matcher(lastname);
            Matcher matcher3 = p2.matcher(amount);
            if (!matcher1.matches() ||  !matcher2.matches() || !matcher3.matches()) {
                board.setText("Only English letters can be entered\n");
                return ;
            }

            if (checking2.isSelected()) {
                args[1] = "C";
            }
            if (collegechecking2.isSelected()) {
                args[1] = "CC";
            }
            if (saving2.isSelected()) {
                args[1] = "S";
            }
            if (moneymarket2.isSelected()) {
                args[1] = "MM";
            }
            args[2] = firstname; args[3] = lastname;
            args[4] = date;
            args[5] = amount;

            board.setText(withdraw_str(args));
        }catch (Exception e) {
            board.setText("Error\n");
        }

    }

    /**
     * click deposit button
     * deposit money
     */
    public void deposit( ) {
        try {
            String[] args = new String[6];;
            String firstname = firstname2.getText();
            String lastname = lastname2.getText();
            String[] dateStr = dob2.getEditor().getText().split("-");
            String date = dateStr[1] + "/" + dateStr[2] + "/" + dateStr[0];
            String amount = amount2.getText();

            Matcher matcher1 = p.matcher(firstname);
            Matcher matcher2 = p.matcher(lastname);
            Matcher matcher3 = p2.matcher(amount);
            if (!matcher1.matches() ||  !matcher2.matches() || !matcher3.matches()) {
                board.setText("Only English letters can be entered\n");
                return ;
            }

            if (checking2.isSelected()) {
                args[1] = "C";
            }
            if (collegechecking2.isSelected()) {
                args[1] = "CC";
            }
            if (saving2.isSelected()) {
                args[1] = "S";
            }
            if (moneymarket2.isSelected()) {
                args[1] = "MM";
            }
            args[2] = firstname; args[3] = lastname;
            args[4] = date;
            args[5] = amount;
            board.setText(deposit_str(args));
        } catch (Exception e) {
            board.setText("Error\n");
        }

    }


    public ToggleGroup AccountType;


    /**
     * if choose college checking, it can choose campus.
     * if choose savings or money market, it can choose whether account is loyal.
     * default money market is loyal.
     */
    public void showcampus(){
        if(AccountType.getSelectedToggle().equals(collegechecking1)) {
            newbrunswick.setDisable(false);
            newark.setDisable(false);
            camden.setDisable(false);
        }else{
            newbrunswick.setDisable(true);
            newark.setDisable(true);
            camden.setDisable(true);
            newbrunswick.setSelected(false);
            newark.setSelected(false);
            camden.setSelected(false);
        }
        if(AccountType.getSelectedToggle().equals(moneymarket1) || AccountType.getSelectedToggle().equals(saving1)){
            loyal.setDisable(false);
            if(AccountType.getSelectedToggle().equals(moneymarket1)){
                loyal.setSelected(true);
            }else {
                loyal.setSelected(false);
            }
        }else {
            loyal.setDisable(true);
        }
    }

    public static AccountDatabase accountDatabase = new AccountDatabase();
    /**
     *
     * @return all accounts
     */
    public String pirnt() {
        String s = accountDatabase.print_str();
        return s;
    }
    /**
     *
     * @return all accounts by types
     */
    public String printByAccountType() {
        return accountDatabase.printByAccountType_str();
    }
    /**
     *
     * @return update all accounts
     */
    public String update() {
        accountDatabase.update();
        String s = accountDatabase.print_str();
        return s;
    }
    /**
     *
     * @return all accounts with fees and interests
     */
    public String printFeeAndInterest() {
        return accountDatabase.printFeeAndInterest_str();
    }
    /**
     * This method executes the W command, handles abnormal input, and makes database changes if the account is correct.
     * @param strs code need withdraw
     */
    private static void withdraw(String[] strs) {
        if (strs.length != 6) {
            System.out.println("Missing data for closing an account.");
            return;
        }
        Account account = null;
        if (strs[1].equals("C")) account = new Checking();
        if (strs[1].equals("CC")) {
            account = new CollegeChecking(0);
        }
        if (strs[1].equals("S")) account = new Savings(1);
        if (strs[1].equals("MM")) account = new MoneyMarket(0);
        try {
            account.setBalance(Double.valueOf(strs[5]));
            if (account.getBalance() <= 0) {
                System.out.println("Withdraw - amount cannot be 0 or negative.");
                return;
            }

        } catch (Exception exception) {
            System.out.println("Not a valid amount.");
            return;
        }

        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            System.out.println("Date of birth invalid.");
            return;
        }
        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        if (!accountDatabase.find3(account)) {
            System.out.println(account.getHolder() + " " + account.getType() + "  is not in the database.");
        } else {
            boolean withdraw = accountDatabase.withdraw(account);
            if (withdraw) {
                System.out.println("Withdraw - balance updated.");
            } else {
                System.out.println("Withdraw - insufficient fund.");
            }
        }
    }

    public  String withdraw_str(String[] strs) {
        if (strs.length != 6) {
            return ("Missing data for closing an account.");
        }
        Account account = null;
        if (strs[1].equals("C")) account = new Checking();
        if (strs[1].equals("CC")) {
            account = new CollegeChecking(0);
        }
        if (strs[1].equals("S")) account = new Savings(1);
        if (strs[1].equals("MM")) account = new MoneyMarket(0);
        try {
            account.setBalance(Double.valueOf(strs[5]));
            if (account.getBalance() <= 0) {
                return("Withdraw - amount cannot be 0 or negative.");
            }

        } catch (Exception exception) {
            return("Not a valid amount.");

        }

        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            return("Date of birth invalid.");

        }
        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        if (!accountDatabase.find3(account)) {
            return(account.getHolder() + " " + account.getType() + "  is not in the database.");
        } else {
            boolean withdraw = accountDatabase.withdraw(account);
            if (withdraw) {
                return("Withdraw - balance updated.");
            } else {
                return("Withdraw - insufficient fund.");
            }
        }
    }

    /**
     * This method executes the D command, processes the abnormal account entry and gives the appropriate prompt,
     * and makes a deposit if the account is correct.
     * @param strs account need deposit
     */
    private static void deposit(String[] strs) {
        if (strs.length != 6) {
            System.out.println("Missing data for closing an account.");
            return;
        }
        Account account = null;
        if (strs[1].equals("C")) account = new Checking();
        if (strs[1].equals("CC")) {
            account = new CollegeChecking(0);
        }
        if (strs[1].equals("S")) account = new Savings(1);
        if (strs[1].equals("MM")) account = new MoneyMarket(0);
        try {
            account.setBalance(Double.valueOf(strs[5]));
            if (account.getBalance() <= 0) {
                System.out.println("Deposit - amount cannot be 0 or negative.");
                return;
            }

        } catch (Exception exception) {
            System.out.println("Not a valid amount.");
            return;
        }
        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            System.out.println("Date of birth invalid.");
            return;
        }
        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        if (!accountDatabase.find3(account)) {
            System.out.println(account.getHolder() + " " + account.getType() + "  is not in the database.");
        } else {
            accountDatabase.deposit(account);
            System.out.println("Deposit - balance updated.");
        }
    }
    public  String deposit_str(String[] strs) {
        if (strs.length != 6) {
            return ("Missing data for closing an account.");

        }
        Account account = null;
        if (strs[1].equals("C")) account = new Checking();
        if (strs[1].equals("CC")) {
            account = new CollegeChecking(0);
        }
        if (strs[1].equals("S")) account = new Savings(1);
        if (strs[1].equals("MM")) account = new MoneyMarket(0);
        try {
            account.setBalance(Double.valueOf(strs[5]));
            if (account.getBalance() <= 0) {
                return("Deposit - amount cannot be 0 or negative.");

            }

        } catch (Exception exception) {
            return("Not a valid amount.");

        }
        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            return("Date of birth invalid.");

        }
        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        if (!accountDatabase.find3(account)) {
            return(account.getHolder() + " " + account.getType() + "  is not in the database.");
        } else {
            accountDatabase.deposit(account);
            return("Deposit - balance updated.");
        }
    }
    /**
     * This method performs account closure operations, processes C commands, handles abnormal account entries
     * and gives the appropriate prompts,
     * and if the account is correct and exists in the database, closes the account in the database,
     * but does not delete it in the database.
     * @param strs account need close
     */
    public static void close(String[] strs) {
        Account account = null;
        if (strs.length != 5) {
            System.out.println("Missing data for closing an account.");
            return;
        }
        if (strs[1].equals("C")) account = new Checking();
        if (strs[1].equals("CC")) {
            account = new CollegeChecking(0);
        }
        if (strs[1].equals("S")) account = new Savings(0);
        if (strs[1].equals("MM")) account = new MoneyMarket(0);
        if (account == null) {
            System.out.println("Missing data for closing an account.");
            return;
        }
        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            System.out.println("Date of birth invalid.");
            return;
        }

        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        if (!accountDatabase.find2(account)) {
            System.out.println(account.getHolder() + " " + account.getType() + "  is not in the database.");
        } else {
            boolean ok = accountDatabase.close(account);
            if (ok) {
                System.out.println("Account closed.");
            } else {
                System.out.println("Account is closed already.");
            }
        }
    }
    public  String close_str(String[] strs) {
        Account account = null;
        if (strs.length != 5) {
            return("Missing data for closing an account.");
        }
        if (strs[1].equals("C")) account = new Checking();
        if (strs[1].equals("CC")) {
            account = new CollegeChecking(0);
        }
        if (strs[1].equals("S")) account = new Savings(0);
        if (strs[1].equals("MM")) account = new MoneyMarket(0);
        if (account == null) {
            return("Missing data for closing an account.");
        }
        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            return("Date of birth invalid.");
        }

        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        if (!accountDatabase.find2(account)) {
            return(account.getHolder() + " " + account.getType() + "  is not in the database.");
        } else {
            boolean ok = accountDatabase.close(account);
            if (ok) {
                return("Account closed.");
            } else {
                return("Account is closed already.");
            }
        }
    }

    /**
     * This method performs account opening operations, processes O commands, handles abnormal account entries
     * and gives the appropriate prompts,
     * and if the account is correct, adds the account to the database
     * @param strs open an account
     */
    public static void open(String[] strs) {
        Account account = null;
        if (strs.length >= 6 && strs[1].equals("C")) account = new Checking();
        if (strs.length >= 7 && strs[1].equals("CC")) {
            if (Integer.valueOf(strs[6]) >= 3 || Integer.valueOf(strs[6]) < 0) {
                System.out.println("Invalid campus code.");
                return;
            }
            account = new CollegeChecking(Integer.valueOf(strs[6]));
        }
        if (strs.length >= 7 && strs[1].equals("S")) account = new Savings(Integer.valueOf(strs[6]));
        if (strs.length >= 6 && strs[1].equals("MM")) account = new MoneyMarket(0);
        if (account == null) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        if (strs.length >= 5) {
            try {
                account.setBalance(Double.valueOf(strs[5]));
                if (account.getBalance() <= 0) {
                    System.out.println("Initial deposit cannot be 0 or negative.");
                    return;
                }
                if (strs.length >= 6 && strs[1].equals("MM") && account.getBalance() < 2500) {
                    System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                    return;
                }
            } catch (Exception exception) {
                System.out.println("Not a valid amount.");
                return;
            }
        }
        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            System.out.println("Date of birth invalid.");
            return;
        }
        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        //verify whether exist
        boolean isEx = accountDatabase.open(account);
        if (!isEx) {
            System.out.println(profile + " same account(type) is in the database.");
        }
    }

    public  String open_str(String[] strs) {
        Account account = null;
        if (strs.length >= 6 && strs[1].equals("C")) account = new Checking();
        if (strs.length >= 7 && strs[1].equals("CC")) {
            try {
                if (Integer.valueOf(strs[6]) >= 3 || Integer.valueOf(strs[6]) < 0) {
                    return("Invalid campus code.");
                }
                account = new CollegeChecking(Integer.valueOf(strs[6]));
            } catch (Exception e) {
                return("Invalid campus code.");
            }
        }
        if (strs.length >= 7 && strs[1].equals("S")) account = new Savings(Integer.valueOf(strs[6]));
        if (strs.length >= 6 && strs[1].equals("MM")) account = new MoneyMarket(0);
        if (account == null) {
            return("Missing data for opening an account.");
        }
        if (strs.length >= 5) {
            try {
                account.setBalance(Double.valueOf(strs[5]));
                if (account.getBalance() <= 0) {
                    return("Initial deposit cannot be 0 or negative.");
                }
                if (strs.length >= 6 && strs[1].equals("MM") && account.getBalance() < 2500) {
                    return("Minimum of $2500 to open a MoneyMarket account.");
                }
            } catch (Exception exception) {
                return("Not a valid amount.");
            }
        }
        Date date = new Date(strs[4]);
        //determine the date
        boolean date_ok = judge(date.getYear(), date.getMonth(), date.getDay());
        if (!date_ok) {
            return("Date of birth invalid.");
        }
        Profile profile = new Profile(strs[2], strs[3], date);
        account.setHolder(profile);
        //verify whether exist
        boolean accountDatabase3 = accountDatabase.find2(account);
        if (accountDatabase3) {
            boolean isEx = accountDatabase.open(account);
            if (!isEx) {
                return(profile + " same account(type) is in the database.");
            } else {
                return "Account reopened.";
            }
        } else {
            boolean isEx = accountDatabase.open(account);
            return "Account opened.";
        }
    }
    /**
     * This method determines the date and returns true if the date is correct, otherwise it returns false.
     * The date must conform to the basic date format, and the date cannot be a future date
     * @param y year
     * @param m month
     * @param d day
     * @return true if is correct date
     */
    public static boolean judge(int y, int m, int d) {
        boolean p = false;
        if (m < 1 || m > 12) {
            p = false;
        } else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            if (d <= 31) {
                p = true;
            } else {
                p = false;
            }
        } else if (m == 2) {
            if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
                if (d <= 29) {
                    p = true;
                } else {
                    p = false;
                }
            } else {
                if (d <= 28) {
                    p = true;
                } else {
                    p = false;
                }
            }
        } else {
            if (d <= 30) {
                p = true;
            } else {
                p = false;
            }
        }
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int mon = (now.get(Calendar.MONTH) + 1);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (p) {
            if (y > year) return false;
            if (y == year && m > mon) return false;
            if (y == year && m == mon && d >= day) return false;
        }
        return p;
    }
}
