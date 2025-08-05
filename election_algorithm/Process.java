package election_algorithm;

public class Process {
    int id;
    boolean isAlive;
    static int coordinatorId = -1;

    public Process(int id) {
        this.id = id;
        this.isAlive = true;
    }

    public void crash() {
        isAlive = false;
    }

    public void activate() {
        isAlive = true;
    }

    public void holdElection(Process[] processes) {
        System.out.println("Process " + id + " is holding an election...");
        boolean higherResponded = false;

        for (int i = id + 1; i < processes.length; i++) {
            if (processes[i].isAlive) {
                System.out.println("Process " + id + " sends election message to Process " + i);
                higherResponded = true;
                processes[i].holdElection(processes);
            }
        }

        if (!higherResponded) {
            coordinatorId = id;
            System.out.println("Process " + id + " becomes the coordinator.");
            announceCoordinator(processes);
        }
    }

    public static void announceCoordinator(Process[] processes) {
        for (Process p : processes) {
            if (p.isAlive && p.id != coordinatorId) {
                System.out.println("Coordinator announcement sent to Process " + p.id + " by Process " + coordinatorId);
            }
        }
    }
}
