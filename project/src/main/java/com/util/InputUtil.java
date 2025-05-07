package com.util;

import java.io.Console;
import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static int getValidatedIntegerInput(String prompt) {
        int num;
        while (true) {
            try {
                System.out.print(prompt);
                num = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
        return num;
    }

    public static String readHiddenPassword() {
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword();
            return new String(passwordArray);
        } else {
            System.out.print("Password (will not hide here): ");
            return scanner.nextLine(); // fallback if console not available (like in IDEs)
        }
    }
}
