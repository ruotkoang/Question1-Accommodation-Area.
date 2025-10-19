import java.util.Scanner;

class AccommodationArea {
    String name;
    int occupants;
    boolean[] lights = new boolean[3];

    public AccommodationArea(String name) {
        this.name = name;
        this.occupants = 0;
    }

    public void addOccupants(int n) {
        occupants += n;
    }

    public void removeOccupants(int n) {
        occupants = Math.max(0, occupants - n);
    }

    public void switchLightOn(int n) {
        if (n >= 1 && n <= 3) lights[n - 1] = true;
    }

    public void switchLightOff(int n) {
        if (n >= 1 && n <= 3) lights[n - 1] = false;
    }

    public void reportStatus() {
        System.out.println("\n--- " + name + " STATUS ---");
        System.out.println("Occupants: " + occupants);
        for (int i = 0; i < lights.length; i++) {
            System.out.println("Light " + (i + 1) + ": " + (lights[i] ? "ON" : "OFF"));
        }
    }
}

class GymArea extends AccommodationArea {
    public GymArea() {
        super("Gym Area");
    }
}

class SwimmingArea extends AccommodationArea {
    public SwimmingArea() {
        super("Swimming Area");
    }
}

public class AccommodationMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GymArea gym = new GymArea();
        SwimmingArea pool = new SwimmingArea();
        AccommodationArea active = gym;

        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("S - Select area (G=Gym, P=Swimming)");
            System.out.println("W - Add occupants");
            System.out.println("X - Remove occupants");
            System.out.println("Y - Switch ON light (1–3)");
            System.out.println("Z - Switch OFF light (1–3)");
            System.out.println("R - Report status");
            System.out.println("Q - Quit");
            System.out.print("Enter your choice: ");
            char choice = sc.next().toUpperCase().charAt(0);

            switch (choice) {
                case 'S' -> {
                    System.out.print("Select area (G or P): ");
                    char area = sc.next().toUpperCase().charAt(0);
                    active = (area == 'P') ? pool : gym;
                    System.out.println("Now working on: " + active.name);
                }

                case 'W' -> {
                    System.out.print("Enter number to add: ");
                    int add = sc.nextInt();
                    active.addOccupants(add);
                    System.out.println(add + " occupants added.");
                }

                case 'X' -> {
                    System.out.print("Enter number to remove: ");
                    int rem = sc.nextInt();
                    active.removeOccupants(rem);
                    System.out.println(rem + " occupants removed.");
                }

                case 'Y' -> {
                    System.out.print("Enter light number (1–3): ");
                    int on = sc.nextInt();
                    active.switchLightOn(on);
                }

                case 'Z' -> {
                    System.out.print("Enter light number (1–3): ");
                    int off = sc.nextInt();
                    active.switchLightOff(off);
                }

                case 'R' -> active.reportStatus();

                case 'Q' -> {
                    System.out.println("Program ended. Goodbye!");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
