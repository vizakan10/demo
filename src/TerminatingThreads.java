

public class TerminatingThreads {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000); // Main thread sleeps for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt(); // Interrupt the MyThread
    }
}



class MyThread extends Thread {

    private volatile boolean exit = false;

    @Override
    public void run() {
        try {
            while (!exit) {
                System.out.println("Thread is running ...");
                Thread.sleep(100); // Sleep for a short period to simulate work
            }
        } catch (InterruptedException e) {
            System.err.println("Caught InterruptedException: " + e.getMessage());
            // Optionally, you can set the exit flag to true to stop the thread
            exit = true;
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }
    }

    public void stopThread() {
        this.exit = true;
        this.interrupt(); // Interrupt the thread to wake it up if it's sleeping
    }
}
