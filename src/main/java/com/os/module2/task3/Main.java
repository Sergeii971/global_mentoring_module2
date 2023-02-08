package com.os.module2.task3;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();

        while (true) {
            System.out.println("1. File count.");
            System.out.println("2. Folder count.");
            System.out.println("3. Size (sum of all files size)");
            System.out.println("4. stop");
            System.out.print("choose number of menu: ");
            switch (scanner.nextInt()) {
                case 1 :
                    System.out.println(fileService.calculateFileCount("/Users/Siarhei_Viarbouski/IdeaProjects/global_mentoring_module2")+ " files\n");
                    break;
                case 2:
                    System.out.println(fileService.calculateFolderCount("/Users/Siarhei_Viarbouski/IdeaProjects/global_mentoring_module2") + " folders \n");
                    break;
                case 3 :
                    System.out.println(fileService.calculateAllFilesSize("/Users/Siarhei_Viarbouski/IdeaProjects/global_mentoring_module2") + " bytes\n");
                    break;
                case 4 :
                    Thread.currentThread().interrupt();
                    break;
                default:
                    System.out.println("incorrect number of menu!!!\n");
                    break;
            }
        }
    }
}
