package ben.kn.toolbox.concurrent;

public class OverlordTest {

    public static void main(String[] args) {
        Overlord overlord = new Overlord();

        overlord.run();
        if ( overlord.isRunning() ) {
            System.out.println("Running");
        }
        while ( overlord.isRunning() ) {

        }
        System.out.println("Finished");
    }
}
