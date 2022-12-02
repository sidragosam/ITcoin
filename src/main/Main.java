package main;
//privát kulccsal írunk alá, nem public-kal
import java.security.KeyPair;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        KeyPair kpRSA;
        /* User jani = new User("Jani", "asd321");
        User anna = new User("Anna", "kormos"); */
        try {
                kpRSA = RSA.generateRSA();
        } catch (Exception e) {
            System.err.println("Couldn't create the RSA KeyPair: " + e.getMessage());
            return;
        }
        BlockChain blockchain = new BlockChain();
        System.out.println("-------------[BLOCKCHAIN]-------------");
        while (true) {
            printOptions();
            int input = readIntInput();
            switch (input) {
                case 1:
                    try {
                        System.out.println("Type the message: ");
                        String sinput = readStringInput();
                        blockchain.addTransaction(kpRSA.getPrivate(), sinput);
                        System.out.println(sinput + " sent.");
                        blockchain.addBlock();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println(blockchain);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        blockchain.mineBlocks();
                        System.out.println(blockchain);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Type in the username: ");
                    String userinput = readStringInput();
                    System.out.println("Type in the private key: ");
                    String keyinput = readStringInput();
                    User user = new User(userinput, keyinput);
                    kpRSA = user.generateRSAforUser();
                    break;
                case 5:
                    System.out.println("Closing website window.\n");
                    return;
                default:
                    System.out.println("Invalid input!\n");
                    break;
            }
        }
    }

    private static void printOptions() {
        System.out.println("(1) Send message (Add Transaction).");
        System.out.println("(2) Show Transactions");
        System.out.println("(3) Mine Blocks");
        System.out.println("(4) Switch user.");
        System.out.println("(5) Close the application.");
        System.out.println("\nWhat do you want to do?");
    }

    private static int readIntInput() {
        Scanner scanner = new Scanner(System.in);
        int input;
        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            input = 0;
        }
        return input;
    }
    private static String readStringInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        try {
            input = scanner.nextLine();
        } catch (Exception e) {
            input = "";
        }
        return input;
    }
}
