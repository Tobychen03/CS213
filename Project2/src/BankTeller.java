import java.util.Calendar;
import java.util.Scanner;

/**
 * BankTeller class
 * Run code
 * Take instructions and follow them
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class BankTeller {
    static AccountDatabase accountDatabase = new AccountDatabase();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new BankTeller().run();
    }

    /**
     * To run the program
     * By receiving different instructions to make different output
     */
    public void run() {
        System.out.println("Bank Teller is running.\n\n");
        while (true) {
            String str = sc.nextLine().replaceAll("\\s{1,}", " ");
            String[] strs = str.split(" ");
            if (str.equals("")) continue;
            if (strs[0].equals("Q")) break;
            if (strs[0].equals("O")) {
                open(strs);
            } else if (strs[0].equals("C")) {
                close(strs);
            } else if (strs[0].equals("D")) {
                deposit(strs);
            } else if (strs[0].equals("W")) {
                withdraw(strs);
            } else if (strs[0].equals("P")) {
                accountDatabase.print();
            } else if (strs[0].equals("PT")) {
                accountDatabase.printByAccountType();
            } else if (strs[0].equals("PI")) {
                accountDatabase.printFeeAndInterest();
            } else if (strs[0].equals("UB")) {
                accountDatabase.update();
                accountDatabase.print();
            } else {
                System.out.println("Invalid command!");
            }
        }
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
            System.out.println(account.getHolder() + " " + account.getType() + " is not in the database.");
        } else {
            boolean withdraw = accountDatabase.withdraw(account);
            if (withdraw) {
                System.out.println("Withdraw - balance updated.");
            } else {
                System.out.println("Withdraw - insufficient fund.");
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
            if (y == year && m == mon && d > day) return false;
        }
        return p;
    }

}
