import java.util.Arrays;
import java.util.Objects;

public class Voter {

    /**
     * Static variable is used to maintain count
     */
    static int numberOfCreatedVoters = 0;
    private final long voterID;
    private String voterName, voterEmail;
    private Byte voterAge;
    private char[] voterPcode;

    /**
     * Constructors to initialze Object
     * @param voterID
     */
    public Voter(long voterID) {
        this.voterID = voterID;
        this.voterName = "";
        this.voterEmail = "";
        this.voterAge = 0;
        this.voterPcode = new char[20];
        numberOfCreatedVoters++;
    }

    /**
     * Constructors to initialze Object
     * @param voterID
     * @param voterName
     * @param voterEmail
     * @param voterAge
     * @param voterPcode
     */
    public Voter(long voterID, String voterName, String voterEmail, Byte voterAge, char[] voterPcode) {
        this.voterID = voterID;
        this.voterName = voterName;
        this.voterEmail = voterEmail;
        this.voterAge = voterAge;
        this.voterPcode = voterPcode;
        numberOfCreatedVoters++;
    }

    /**
     * Setters
     * @param voterPcode
     */

    public void setVoterPcode(char[] voterPcode) {
        this.voterPcode = voterPcode;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public void setVoterEmail(String voterEmail) {
        this.voterEmail = voterEmail;
    }

    public void setVoterAge(Byte voterAge) {
        this.voterAge = voterAge;
    }

    /**
     * Getters
     * @return
     */

    public long getVoterID() {
        return this.voterID;
    }

    public String getVoterName() {
        return this.voterName;
    }

    public String getVoterEmail() {
        return this.voterEmail;
    }

    public Byte getVoterAge() {
        return this.voterAge;
    }

    public char[] getVoterPcode() {
        return this.voterPcode;
    }

    @Override
    public String toString() {
        String result = "";
        result += "VoterID: " + voterID + "\n";
        result += "Voter Name: " + voterName + "\n";
        result += "Voter Email: " + voterEmail + "\n";
        result += "Voter Age: " + voterAge + "\n";
        result += "Voter Pcode: " + voterPcode + "\n";
        return result;
    }

    public int findNumberOfCreatedVoters() {
        return numberOfCreatedVoters;
    }

    public boolean equals(Voter v1) {
        return (Arrays.equals(v1.voterPcode, this.voterPcode) && Objects.equals(v1.voterID, this.voterID));
    }
}