package project3;


/**
 * Profile class
 * create a profile
 * determine whether is same holder
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Profile {
    private String fname;
    private String lname;
    private Date dob;


    /**
     * create a profile
     * @param fname first name
     * @param lname last name
     * @param dob date of birth
     */
    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * determine whether is same person
     * @param obj profile
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
        Profile person = (Profile) obj;
        if(toString().toLowerCase().equals(person.toString().toLowerCase())){
            return true;
        }

        return false;
    }

    /**
     * form holder's information
     * @return firstname, lastname and date of birth
     */
    @Override
    public String toString() {
        String birth = null;
        if (this.dob.isValid()) {
            String birthy = Integer.toString(this.dob.getYear());
            String birthm = Integer.toString(this.dob.getMonth());
            String birthd = Integer.toString(this.dob.getDay());
            birth = birthm + "/" + birthd + "/" + birthy;
        }
        return this.fname + " " + this.lname + " " + birth;
    }
}