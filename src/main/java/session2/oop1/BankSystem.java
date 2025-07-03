package session2.oop1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {

        //  store bank account objects
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        Scanner scannerObj = new Scanner(System.in);

        while (true) {
            //  ui display for user's initial input
            System.out.println("\n=== Bank Menu ===");
            System.out.println("""
                    1. Create Account
                    2. View all accounts
                    3. Check Balance
                    4. Deposit
                    5. Withdraw
                    6. Exit"""
            );
            System.out.print("Enter Choice (1-6): ");
            int choiceInput = scannerObj.nextInt();

            switch (choiceInput) {
                case 1:
                    System.out.print("\nEnter account number: ");
                    String acctNumInput = scannerObj.next();

                    System.out.print("Enter holder name: ");
                    String acctHolderInput = scannerObj.next();

                    System.out.print("Initial deposit? (y/n): ");
                    String initDepChoice = scannerObj.next();

                    //  initial deposit choice conditions
                    if (Objects.equals(initDepChoice, "Y") || Objects.equals(initDepChoice, "y")) {
                        System.out.print("Enter initial deposit amount: $");
                        double initDepoAmount = scannerObj.nextDouble();
                        bankAccounts.add(new BankAccount(acctHolderInput, acctNumInput, initDepoAmount));

                        // success message output
                        System.out.println("\n** Account Added Successfully **");
                    } else if (Objects.equals(initDepChoice, "N") || Objects.equals(initDepChoice, "n")) {
                        bankAccounts.add(new BankAccount(acctHolderInput, acctNumInput));

                        // success message output
                        System.out.println("\n** Account Added Successfully **");
                    }

                    //  ask if user wants return to menu or not
                    System.out.print("Would you like to return to the menu? (y/n): ");
                    String returnMenuChoice = scannerObj.next();

                    //  if yes (y-Y), will continue the while loop
                    if (Objects.equals(returnMenuChoice, "Y") || Objects.equals(returnMenuChoice, "y")) {
                        continue;

                    // if no (n-N), will exit the program/break the loop
                    } else if (Objects.equals(returnMenuChoice, "N") || Objects.equals(returnMenuChoice, "n")) {
                        System.exit(0);
                    }

                    break;
                case 2:
                    displayInfo(bankAccounts);

                    break;
                case 3:
                    displayAccounts(bankAccounts); //   display guide for user to view list of all holders
                    System.out.print("Enter which account you want check: ");
                    int balanceViewInput = scannerObj.nextInt();

                    System.out.printf("\nAccount holder: %s", bankAccounts.get(balanceViewInput).getHolder());
                    System.out.printf("\nCurrent balance: $%s", bankAccounts.get(balanceViewInput).getBalance());

                    break;
                case 4:
                    displayAccounts(bankAccounts); //   display guide for user to view list of all holders
                    System.out.print("Enter which account you want deposit: ");
                    int depositIndexInput = scannerObj.nextInt();

                    System.out.printf("\nAccount holder: %s", bankAccounts.get(depositIndexInput).getHolder());
                    System.out.printf("\nAvailable balance: $%s\n", bankAccounts.get(depositIndexInput).getBalance());

                    while (true) {
                        System.out.print("Enter deposit amount: $");
                        double depositInput = scannerObj.nextDouble();

                        if (depositInput <= 0) {
                            System.out.println("You can't deposit a negative/zero amount.");
                        }
                        else {
                            try {
                                bankAccounts.get(depositIndexInput).deposit(depositInput);
                                System.out.printf("$%s of amount added to %s!\n", depositInput, bankAccounts.get(depositIndexInput).getHolder());
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    System.out.printf("\nAccount holder: %s", bankAccounts.get(depositIndexInput).getHolder());
                    System.out.printf("\nAvailable balance: $%s\n", bankAccounts.get(depositIndexInput).getBalance());

                    break;

                case 5:
                    displayAccounts(bankAccounts); //   display guide for user to view list of all holders
                    System.out.print("Enter which account you want withdraw: ");
                    int withdrawIndexInput = scannerObj.nextInt();

                    System.out.printf("\nAccount holder: %s", bankAccounts.get(withdrawIndexInput).getHolder());
                    System.out.printf("\nAvailable balance: $%s\n", bankAccounts.get(withdrawIndexInput).getBalance());

                    while (true) {
                        System.out.print("Enter withdraw amount: $");
                        double withdrawInput = scannerObj.nextDouble();

                        try {
                            bankAccounts.get(withdrawIndexInput).withdraw(withdrawInput);
                            System.out.printf("** $%s of amount withdrawn from %s **\n", withdrawInput, bankAccounts.get(withdrawIndexInput).getHolder());
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    System.out.printf("\nAccount holder: %s", bankAccounts.get(withdrawIndexInput).getHolder());
                    System.out.printf("\nAvailable balance: $%s\n", bankAccounts.get(withdrawIndexInput).getBalance());

                    break;

                case 6:
                    System.out.println("\n** Thanks You! **");
                    System.exit(0);

                default:
                    System.out.println("\nNo such inputs. Try Again");
            }
        }

    }

    //  display info of accounts
    public static void displayInfo (ArrayList<BankAccount> bankAccounts) {
        System.out.println("\n~ List of Accounts & Information ~");
        for (BankAccount bankAccount : bankAccounts) {
            System.out.println("Holder: " + bankAccount.getHolder());
            System.out.println("Account Number: " + bankAccount.getAccountNumber());
            System.out.println("Balance: $" + bankAccount.getBalance());
            System.out.println();
        }
        System.out.println();
    }

    //  display info for list of accounts for users to select
    public static void displayAccounts (ArrayList<BankAccount> bankAccounts) {
        System.out.println("\n~ List of Accounts ~");
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.printf("\n%s. %s", i , bankAccounts.get(i).getHolder());
        }
        System.out.println();
    }

}