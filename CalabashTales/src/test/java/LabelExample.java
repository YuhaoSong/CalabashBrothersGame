
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LabelExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Label Example");
       // Image image = new Image(getClass().getResourceAsStream("pic/Sidekicks.png"));
        Label label1 = new Label("Search");
        //label1.setGraphic(new ImageView(image));
        label1.setTextFill(Color.web("#0076a3"));
        label1.setFont(new Font("Arial", 30));

        Label label2 = new Label("Values");
        label2.setFont(new Font("Cambria", 32));
        label2.setRotate(270);
        label2.setTranslateY(100);

        final Label label3 = new Label("A label that needsto be wrapped");
        label3.setWrapText(true);

        label3.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                label3.setScaleX(1.5);
                label3.setScaleY(1.5);
            }
        });
        label3.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                label3.setText("change!");
                label3.setScaleX(1);
                label3.setScaleY(1);
            }
        });
        AnchorPane root = new AnchorPane();
        root.getChildren().add(label1);
        root.getChildren().add(label2);
        root.getChildren().add(label3);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
