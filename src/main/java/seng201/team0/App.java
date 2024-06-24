package seng201.team0;

import seng201.team0.gui.FXWindow;


/**
 * Entry point class.
 * @author seng201 teaching team
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class App {

    /**
     * Default constructor for the App class.
     * Initializes the application and prepares necessary components.
     */
    public App() {
        super();
    }

    /**
     * Entry point which runs the javaFX application Due to how JavaFX works we
     * must call MainWindow.launchWrapper() from here, trying to run MainWindow
     * itself will cause an error.
     *
     * @param args program arguments from command line
     */
    public static void main(final String[] args) {
        FXWindow.launchWrapper(args);
    }
}
