

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFXApplication1 extends Application {

    Text txt;
    StackPane root;
    Rectangle rectangle;

    @Override
    public void init() throws Exception {
        super.init(); 
        rectangle = new Rectangle(400,400);
        rectangle.setId("rect");
        txt = new Text("Hello World");
        txt.setId("hello");
        
        
//        Stop[] stop = {new Stop(0, Color.BLACK),new Stop(0.5, Color.WHITE),new Stop(1, Color.BLACK)};
//         LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop);
//      //styling with classes
//        LinearGradient g= LinearGradient.valueOf("linear-gradient(#000 30%, #fff 65%, #000 100%)");
//        rectangle.setFill(lg);
//       
//        Reflection reflection = new Reflection(0.9,0.9,0.1,0.5);
//        txt.setEffect(reflection);
//        txt.setFill(Color.RED);
//        Font font = Font.font("Verdana", FontWeight.BOLD, 30);
//        txt.setFont(font);
//        
        root = new StackPane();
        root.getChildren().addAll(rectangle,txt);
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(root, 300, 250);
       scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
}
