package bankers_algorithm;

import java.util.Scanner;

public class BankersAlgorithm {

    static int numberOfProcesses;
    static int numberOfResources;

    static int[][] maximum;
    static int[][] allocation;
    static int[][] need;
    static int[] available;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of processes and resources
        System.out.print("Enter number of processes: ");
        numberOfProcesses = sc.nextInt();

        System.out.print("Enter number of resources: ");
        numberOfResources = sc.nextInt();

        maximum = new int[numberOfProcesses][numberOfResources];
        allocation = new int[numberOfProcesses][numberOfResources];
        need = new int[numberOfProcesses][numberOfResources];
        available = new int[numberOfResources];

        // Input: Allocation matrix
        System.out.println("\nEnter Allocation Matrix:");
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        // Input: Maximum matrix
        System.out.println("\nEnter Maximum Matrix:");
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                maximum[i][j] = sc.nextInt();
                need[i][j] = maximum[i][j] - allocation[i][j]; // Calculate need
            }
        }

        // Input: Available resources
        System.out.println("\nEnter Available Resources:");
        for (int i = 0; i < numberOfResources; i++) {
            available[i] = sc.nextInt();
        }

        // Run Banker's Algorithm
        if (isSafe()) {
            System.out.println("\nSystem is in a SAFE state.");
        } else {
            System.out.println("\nSystem is in an UNSAFE state.");
        }

        sc.close();
    }

    static boolean isSafe() {
        int[] work = new int[numberOfResources];
        boolean[] finish = new boolean[numberOfProcesses];
        int[] safeSequence = new int[numberOfProcesses];
        int count = 0;

        System.arraycopy(available, 0, work, 0, numberOfResources);

        while (count < numberOfProcesses) {
            boolean found = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < numberOfResources; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == numberOfResources) {
                        for (int k = 0; k < numberOfResources; k++) {
                            work[k] += allocation[i][k];
                        }
                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                return false; // No safe sequence
            }
        }

        System.out.print("Safe Sequence: ");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("P" + safeSequence[i]);
            if (i != numberOfProcesses - 1) System.out.print(" -> ");
        }
        System.out.println();
        return true;
    }
}

