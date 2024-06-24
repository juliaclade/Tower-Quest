package seng201.team0.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Starts the javaFX application window.
 * @author seng202 teaching team, Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class FXWindow extends Application {

    /**
     * Default constructor for FXWindow.
     * Initializes the main JavaFX application window.
     */
    public FXWindow() {
        super();
    }

    /**
     * Stores screen width.
     */
    private static final int SCREEN_WIDTH = 900;

    /**
     * Stores screen height.
     */
    private static final int SCREEN_HEIGHT = 600;

    /**
     * Opens the gui with the fxml content specified in
     * resources/fxml/fx_wrapper.fxml.
     * @param primaryStage The current fxml stage, handled by javaFX Application
     *                    class
     * @throws IOException if there is an issue loading fxml file
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass()
                                   .getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseLoader.load();
        FXWrapper fxWrapper = baseLoader.getController();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        primaryStage.setTitle("Tower Quest");
        primaryStage.setScene(scene);
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }

    /**
     * Launches the FXML application, this must be called from another class
     * (in this cass App.java) otherwise JavaFX errors out and does not run.
     *
     * @param args command line arguments
     */
    public static void launchWrapper(final String[] args) {
        launch(args);
    }

}
