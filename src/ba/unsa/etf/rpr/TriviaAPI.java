package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TriviaAPI extends Thread {

    private String trivia;
    @FXML
    private Label labelIndex;

    public TriviaAPI(Label labelIndex) {
        setDaemon(true);
        trivia="";
        this.labelIndex=labelIndex;
    }

    public String getTrivia() {
        return trivia;
    }

    public void setTrivia(String trivia) {
        this.trivia=trivia;
        labelIndex.setText(trivia);
    }

    @Override
    public void run() {
        while (!this.isInterrupted()){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL("http://numbersapi.com/random/trivia").openConnection();
                        connection.setRequestMethod("GET");
                        int responseCode = connection.getResponseCode();
                        if(responseCode == 200){
                            String response = "";
                            Scanner scanner = new Scanner(connection.getInputStream());
                            while(scanner.hasNextLine()){
                                response += scanner.nextLine();
                                response += "\n";
                            }
                            scanner.close();
                            setTrivia(response);
                            connection.disconnect();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                sleep(TimeUnit.SECONDS.toMillis(10));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
