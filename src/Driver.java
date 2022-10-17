/**
 * Assignment 1
 * @ShalviSaxena
 * Written by: Shalvi Saxena (40220846)
 * Description: Voter application to keep track of Voter's details
 */


import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    /**
     * voterBase: to keep list of created voters
     */
    private static ArrayList<Voter> voterBase;

    /**
     * macimum voters allowed
     */
    int maxVoters;
    private final String password = "password";
    private int attemptsCount = 0;
    private int choice = 0;

    /**
     * Check passwords and to keep track of failed attempts
     * @param attempts
     * @param sc
     * @param shouldUpdateAttempts
     * @return
     */
    private boolean checkPassword(int attempts, Scanner sc, boolean shouldUpdateAttempts) {
        if(attempts == 3) {
            System.out.println("Number of attempts exceeds the limit!");
            return false;
        }

        if(shouldUpdateAttempts)
            attemptsCount++;

        System.out.println("Enter password ");
        String pass = sc.nextLine();

        if(pass.equals(password))
            return true;
        return checkPassword(attempts+1, sc, shouldUpdateAttempts);
    }

    /**
     * To display Main menu
     * @param sc
     */
    public void displayMainMenu(Scanner sc) {
        System.out.println("What do you want to do?  \n" +
                "1. Enter new voters (password required)  \n" +
                "2. Change information of a voter (password required)  \n" +
                "3. Display all voters by a specific voterPcode  \n" +
                "4. Display all voters under a certain age.  \n" +
                "5. Quit  \n" +
                "Please enter your choice >\n");
        this.choice = Integer.parseInt(sc.nextLine());
    }

    /**
     * Find voters by ID
     * @param voterId
     * @return
     */
    private static Voter findVotersById(long voterId) {
        for(Voter v1: voterBase) {
            if(v1.getVoterID() == voterId)
                return v1;
        }
        return null;
    }

    /**
     * Find voters by age
     * @param voterAge
     * @return
     */
    private static ArrayList<Voter> findVotersBy(Byte voterAge) {
        ArrayList<Voter> arr = new ArrayList<>();
        for(Voter v1: voterBase) {
            if(v1.getVoterAge() < voterAge)
                arr.add(v1);
        }
        return arr;
    }

    private static ArrayList<Voter> findVotersBy(String voterPcode) {
        ArrayList<Voter> arr = new ArrayList<>();
        for(Voter v1: voterBase) {
            if(String.valueOf(v1.getVoterPcode()).equals(voterPcode))
                arr.add(v1);
        }
        return arr;
    }

    /**
     * update voter's details
     * @param sc
     * @param v1
     */
    private static void updateVoterDetails(Scanner sc, Voter v1) {
        System.out.println("What information would you like to change?  \n" +
                "1. Name  \n" +
                "2. Age  \n" +
                "3. Email  \n" +
                "4. PCode  \n" +
                "5. Quit  \n" +
                "Enter your choice >\n ");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.println("Write voter Name ");
                String voterName = sc.nextLine();
                if(!voterName.isBlank()) {
                    v1.setVoterName(voterName);
                    System.out.println("Changed successfully!");
                }
                break;
            case 2:
                System.out.println("Write voter Age ");
                Byte voterAge = Byte.valueOf(sc.nextLine());
                if(voterAge != null) {
                    v1.setVoterAge(voterAge);
                    System.out.println("Changed successfully!");
                }
                break;
            case 3:
                System.out.println("Write voter Email ");
                String voterEmail = sc.nextLine();
                if(!voterEmail.isBlank()) {
                    v1.setVoterEmail(voterEmail);
                    System.out.println("Changed successfully!");
                }
                break;
            case 4:
                System.out.println("Write voter Pcode (Optional)");
                char[] voterPcode = sc.nextLine().toCharArray();
                if(voterPcode.length == 0) {
                    v1.setVoterPcode(voterPcode);
                    System.out.println("Changed successfully!");
                }
                break;
            case 5:
                System.out.println("Quitting!! ");
                return;
        }
        if(choice >= 1 && choice <= 4)
            updateVoterDetails(sc, v1);
    }

    private static void updateVoter(Scanner sc) {
        System.out.println("Enter the voter ID of the required voter ");
        long id = Long.parseLong(sc.nextLine());
        Voter v1 = findVotersById(id);
        if(v1 != null) {
            updateVoterDetails(sc, v1);
        } else {
            System.out.println("Please re-enter another Voter ID");
            updateVoter(sc);
        }
        System.out.println("Enter YES if want to update data of another voter ");
        String choice = sc.nextLine();
        if(choice.equals("YES"))
            updateVoter(sc);
    }

    /**
     * Show Voter's details
     * @param sc
     */
    private static void showVoterDetailsByPcode(Scanner sc) {
        System.out.println("Enter Voter Pcode ");
        String voterPcode = sc.nextLine();
        ArrayList<Voter> v1 = findVotersBy(voterPcode);
        if(v1.size() > 0) {
            System.out.println("Following are the details of Voters of "+voterPcode+" postal code ");
            for(Voter v: v1)
                System.out.println(v1.toString());
        } else {
            System.out.println("No Voters found in "+voterPcode+" postal code ");
        }
    }

    private static void showVoterDetailsByAge(Scanner sc) {
        System.out.println("Enter Voter's Age ");
        byte voterAge = Byte.valueOf(sc.nextLine());
        ArrayList<Voter> v1 = findVotersBy(voterAge);
        if(v1.size() > 0) {
            System.out.println("Following are the details of Voters younger than "+voterAge);
            for(Voter v: v1)
                System.out.println(v1.toString());
        } else {
            System.out.println("No Voters found younger than "+voterAge);
        }
    }

    /**
     * Add more voters
     * @param sc
     */
    public void enterMoreVoters(Scanner sc) {
        System.out.println("Please enter the number of voters");
        int n = Integer.parseInt(sc.nextLine());
        if((voterBase.size()+n) <= maxVoters ) {
            while (n>0) {
                System.out.println("Enter New Voter Details");
                System.out.println("Write voter Id ");
                long voterID = Long.parseLong(sc.nextLine());
                System.out.println("Write voter Name (Optional)");
                String voterName = sc.nextLine();
                System.out.println("Write voter Age (Optional)");
                Byte voterAge = Byte.valueOf(sc.nextLine());
                System.out.println("Write voter Email (Optional)");
                String voterEmail = sc.nextLine();
                System.out.println("Write voter Pcode (Optional)");
                char[] voterPcode = sc.nextLine().toCharArray();

                Voter v1 = new Voter(voterID, voterName, voterEmail, voterAge, voterPcode);
                voterBase.add(v1);
                n--;
                System.out.println("Voter Added successfully!");
            }
        } else {
            System.out.println("You can only add "+(maxVoters - voterBase.size())+" voters!");
        }
    }
    public void enterVoters(Scanner sc) {
        System.out.println("Please enter the maximum number of voters");
        int n = Integer.parseInt(sc.nextLine());
        this.maxVoters = n;
        this.voterBase = new ArrayList<>();
    }

    /**
     * To display welcome message!
     */
    public void displayWelcomeMessage() {
        System.out.println("Bonjour!");
    }

    public static void main(String[] args) {

        Driver driver = new Driver();
        Scanner sc = new Scanner(System.in);

        driver.displayWelcomeMessage();

        try {
            driver.enterVoters(sc);

            while (driver.attemptsCount < 12) {
                driver.displayMainMenu(sc);

                switch (driver.choice) {
                    case 1:
                        if (driver.checkPassword(0, sc, true)) {
                            driver.enterMoreVoters(sc);
                        }
                        break;
                    case 2:
                        if (driver.checkPassword(0, sc, false)) {
                            driver.updateVoter(sc);
                        }
                        break;
                    case 3:
                        driver.showVoterDetailsByPcode(sc);
                        break;
                    case 4:
                        driver.showVoterDetailsByAge(sc);
                        break;
                    case 5:
                        System.out.println("Quiting the application. Thank you! ");
                        sc.close();
                        return;
                }
            }

            System.out.println("Program detected suspicious activities and will terminate immediately!, then  the program must exit");
            System.exit(0);

        }catch (Exception e) {
            sc.close();
            System.out.println("System crashed due to incorrect input!");
            System.exit(0);
        }

    }
}
