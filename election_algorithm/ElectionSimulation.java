package election_algorithm;
import java.util.Scanner;

public class ElectionSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // 👈 OPENED here

        int n = 5;
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Crash a process");
            System.out.println("2. Activate a process");
            System.out.println("3. Start election from a process");
            System.out.println("4. Show coordinator");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter process ID to crash: ");
                    int crashId = scanner.nextInt();
                    if (processes[crashId].isAlive) {
                        processes[crashId].crash();
                        System.out.println("Process " + crashId + " crashed.");
                        if (crashId == Process.coordinatorId) {
                            System.out.println("Coordinator crashed!");
                        }
                    } else {
                        System.out.println("Process already crashed.");
                    }
                    break;

                case 2:
                    System.out.print("Enter process ID to activate: ");
                    int activateId = scanner.nextInt();
                    processes[activateId].activate();
                    System.out.println("Process " + activateId + " activated.");
                    break;

                case 3:
                    System.out.print("Enter process ID to start election: ");
                    int electId = scanner.nextInt();
                    if (processes[electId].isAlive) {
                        processes[electId].holdElection(processes);
                    } else {
                        System.out.println("Process is not active.");
                    }
                    break;

                case 4:
                    if (Process.coordinatorId != -1 && processes[Process.coordinatorId].isAlive) {
                        System.out.println("Current Coordinator: Process " + Process.coordinatorId);
                    } else {
                        System.out.println("No active coordinator.");
                    }
                    break;

                case 5:
                    scanner.close();  // ✅ CLOSE Scanner before exit
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
