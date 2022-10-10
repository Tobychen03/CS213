package project3;

/**
 * CollegeChecking class
 * extends Checking
 * create college checking
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class CollegeChecking extends Checking {
    /**
     * create college checking account
     * @param campus
     */
    public CollegeChecking(int campus) {
        super("College Checking", 0.0025, 0, campus);
    }
}
