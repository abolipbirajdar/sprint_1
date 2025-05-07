package com.util;

import java.io.IOException;

public class PasswordField {

    public static String readPassword(String prompt) {
        EraserThread et = new EraserThread();
        Thread mask = new Thread(et);
        mask.start();

        System.out.print(prompt);

        StringBuilder password = new StringBuilder();
        try {
            while (true) {
                char ch = (char) System.in.read();
                if (ch == '\n' || ch == '\r') {
                    break;
                } else if (ch == '\b') {
                    if (password.length() > 0) {
                        password.setLength(password.length() - 1);
                        System.out.print("\b \b");
                    }
                } else {
                    password.append(ch);
                    System.out.print("*");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        et.stopMasking();
        return password.toString();
    }

    private static class EraserThread implements Runnable {
        private volatile boolean running = true;

        public void run() {
            while (running) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    running = false;
                }
            }
        }

        public void stopMasking() {
            running = false;
        }
    }
}
