package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.provider.PolicySpiFile;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        char[] arr = getSourceArray();
        int hw = (int) Math.sqrt(arr.length);

        //Creating a writable image
        WritableImage wImage = new WritableImage(hw, hw);

        //getting the pixel writer
        PixelWriter writer = wImage.getPixelWriter();



        int arrCount = 0;
        for(int y = 0; y < hw; y++) {
            for(int x = 0; x < hw; x++) {
                char bitValue = arr[arrCount];
                if(bitValue == '0') {
                    System.out.println("Writing WHITE to " + x + " " + y);
                    writer.setColor(x, y, Color.WHITE);
                } else {
                    System.out.println("Writing BLACK to " + x + " " + y);
                    writer.setColor(x, y, Color.BLACK);
                }
                arrCount++;
            }
        }

        //Setting the view for the writable image
        ImageView imageView = new ImageView(wImage);

        //Creating a Group object
        Group root = new Group(imageView);
        root.setLayoutX(50);
        root.setLayoutY(35);

//        StackPane pane = new StackPane();
//        pane.getChildren().addAll(root);
//        pane.setAlignment(root, Pos.CENTER);

//        pane.setLayoutX(hw);
//        pane.setLayoutY(hw);

        //Creating a scene object
        Scene scene = new Scene(root, hw + 100, hw + 100);

        //Setting title to the Stage
        primaryStage.setTitle("Writing pixels ");

        //Adding scene to the stage
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(hw + 100);
        primaryStage.setMinWidth(hw + 100);
        primaryStage.setMaxHeight(hw + 100);
        primaryStage.setMaxWidth(hw + 100);

        //Displaying the contents of the stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    static char[] getSourceArray() {
        String s = "1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010";
        if(isPerfectSquare(s.length())) {
            return s.toCharArray();
        }
        return squaredString(s).toCharArray();
    }

    static String squaredString(String s) {
        System.out.println("starting length: " + s.length());
        int squaredLength = 0;
        for(int x = s.length(); !isPerfectSquare(x); x--) {
            System.out.println(x + " " + isPerfectSquare(x));
            squaredLength = x;
        }
        squaredLength--;
        System.out.println("cut to perfect square: " + squaredLength);
        return s.substring(0, squaredLength);
    }

    static boolean isPerfectSquare(double x)
    {
        // Find floating point value of
        // square root of x.
        double sr = Math.sqrt(x);

        // If square root is an integer
        return ((sr - Math.floor(sr)) == 0);
    }
}
