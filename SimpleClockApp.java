import java.text.SimpleDateFormat;
import java.util.Date;

// Clock class responsible for holding and updating current time/date
class Clock {
    private String currentTime;

    // Method to update the current time continuously
    public synchronized void updateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        currentTime = formatter.format(new Date());
    }

    // Method to get the current time safely
    public synchronized String getCurrentTime() {
        return currentTime;
    }
}

// Thread that updates the clock in the background
class UpdateThread extends Thread {
    private final Clock clock;

    public UpdateThread(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        while (true) {
            clock.updateTime();
            try {
                // Sleep briefly to simulate background updating
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Update thread interrupted.");
                break;
            }
        }
    }
}

// Thread that displays the clock time
class DisplayThread extends Thread {
    private final Clock clock;

    public DisplayThread(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Current Time: " + clock.getCurrentTime());
            try {
                // Sleep briefly before printing again
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Display thread interrupted.");
                break;
            }
        }
    }
}

// Main class to run the application
public class SimpleClockApp {
    public static void main(String[] args) {
        Clock clock = new Clock();

        // Create threads
        UpdateThread updater = new UpdateThread(clock);
        DisplayThread displayer = new DisplayThread(clock);

        // Set priorities: display thread higher than update thread
        updater.setPriority(Thread.MIN_PRIORITY);   // Lower priority
        displayer.setPriority(Thread.MAX_PRIORITY); // Higher priority

        // Start threads
        updater.start();
        displayer.start();
    }
}
