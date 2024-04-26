package com.kerem.utility;

import java.util.Scanner;

public class GetValue {
    static Scanner scanner = new Scanner(System.in);

    public static String getStringVal(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static Integer getIntVal(String message) {
        System.out.print(message);
        Integer value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static Double getDoubleVal(String message) {
        System.out.print(message);
        Double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static Long getLongVal(String message) {
        System.out.print(message);
        Long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    public static void getEmptyLine(){
        scanner.nextLine();
    }
}
