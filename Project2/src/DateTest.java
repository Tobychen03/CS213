import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        Date date1 = new Date("3/12/2021");
        Date date2 = new Date("2/32/2021");
        Date date3 = new Date("1/31/2021");
        Date date4 = new Date("4/31/2021");
        Date date5 = new Date("13/12/1982");
        assert date1.isValid() == true;
        assert date2.isValid() == false;
        assert date3.isValid() == true;
        assert date4.isValid() == false;
        assert date5.isValid() == false;
    }
}