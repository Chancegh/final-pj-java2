import javafx.application.Application;
//import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class Main extends Application {

    String[][] keys = {
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+"},
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "\'"},
            {"Z", "X", "C", "V", "B", "N", "M", ",", ".", "?"}
    };
    double keySize = 40;
    double keyMargin = 6;
    String keyColor = "#666";
    String keyPressedColor = "#777";
    String keyboardColor = "#ccc";
//    DropShadow ds0 = new DropShadow(0, 0, 0, Color.web("#333", 0));
    DropShadow ds0 = new DropShadow(0, 1, 1, Color.web("#333"));
    DropShadow ds1 = new DropShadow(1, 2, 2, Color.web("#333"));
    DropShadow ds2 = new DropShadow(2, 3, 3, Color.web("#333"));

    public static void main (String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Digital Keyboard");
        primaryStage.show();

        // add keyboard
//        Pane paneKeys = new Pane();


        // add keys
        Group keysGroup = new Group();
        keysGroup.setLayoutX(300);
        keysGroup.setLayoutY(380);

        // add keyboard background
        Rectangle keyboard = new Rectangle(-20, -20, 720, 268);
        keyboard.setArcHeight(15);
        keyboard.setArcWidth(15);
        keyboard.setFill(Color.web(keyboardColor));
        keysGroup.getChildren().add(keyboard);

        // add main keys
        double blockSize = keySize + keyMargin;
        for(int i = 0; i < keys.length; i++) {
            for(int j = 0; j < keys[i].length; j++) {
                Button btn = createNewButton(
                        keys[i][j],
                        (j + 1) * blockSize + blockSize / 2 * i,
                        i * blockSize,
                        keySize,
                        keySize
                );
                keysGroup.getChildren().add(btn);
            }
        }

        // add other keys
        Button btnDot = createNewButton("~", 0, 0, keySize, keySize);
        Button btnDel = createNewButton(
                "Del",
                blockSize * (keys[0].length + 1),
                0,
                keySize * 2,
                keySize
        );
        Button btnTab = createNewButton("Tab", 0, blockSize, keySize * 1.6, keySize);
        Button btnBackslash = createNewButton(
                "\\",
                blockSize * keys[1].length + keySize * 1.57 + keyMargin,
                blockSize,
                keySize * 1.42,
                keySize
        );
        Button btnCaps = createNewButton("Caps", 0, blockSize * 2, keySize * 2 + keyMargin , keySize);
        Button btnEnter = createNewButton(
                "Enter",
                blockSize * (keys[2].length) + blockSize * 2,
                blockSize * 2,
                keySize * 2,
                keySize
        );
        Button btnShift1 = createNewButton("Shift", 0, blockSize * 3, keySize * 2.75, keySize);
        Button btnShift2 = createNewButton(
                "Shift",
                blockSize * (keys[3].length) + keySize * 2.75 + keyMargin,
                blockSize * 3,
                keySize * 2.56,
                keySize
        );
        Button btnCtrl1 = createNewButton("Ctrl", 0, blockSize * 4, keySize, keySize);
        Button btnFn = createNewButton("Fn", blockSize, blockSize * 4, keySize, keySize);
        Button btnAlt1 = createNewButton("Alt", blockSize * 2, blockSize * 4, keySize, keySize);
        Button btnSpace = createNewButton("Space", blockSize * 3, blockSize * 4, blockSize * 8.73, keySize);
        Button btnAlt2 = createNewButton("Alt", blockSize * 11 + keySize, blockSize * 4, keySize, keySize);
        Button btnBlock = createNewButton("", blockSize * 12 + keySize, blockSize * 4, keySize, keySize);
        Button btnCtrl2 = createNewButton("Ctrl", blockSize * 13 + keySize, blockSize * 4, keySize, keySize);

        keysGroup.getChildren().add(btnDot);
        keysGroup.getChildren().add(btnDel);
        keysGroup.getChildren().add(btnTab);
        keysGroup.getChildren().add(btnBackslash);
        keysGroup.getChildren().add(btnCaps);
        keysGroup.getChildren().add(btnEnter);
        keysGroup.getChildren().add(btnShift1);
        keysGroup.getChildren().add(btnShift2);
        keysGroup.getChildren().add(btnCtrl1);
        keysGroup.getChildren().add(btnFn);
        keysGroup.getChildren().add(btnAlt1);
        keysGroup.getChildren().add(btnSpace);
        keysGroup.getChildren().add(btnAlt2);
        keysGroup.getChildren().add(btnBlock);
        keysGroup.getChildren().add(btnCtrl2);

        root.getChildren().add(keysGroup);



    }

    public Button createNewButton(String keyName, double x, double y, double width, double height) {
        Button btn = new Button(keyName);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setId("key-" + keyName);
        btn.setEffect(ds1);
        btn.setEffect(ds2);
        btn.setPrefSize(width, height);
        btn.setStyle("-fx-background-color: "+ keyColor + "; -fx-text-fill: white;");

        btn.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                btn.setEffect(ds0);
                btn.setTranslateX(1);
                btn.setTranslateY(1);
                btn.setStyle("-fx-background-color: "+ keyPressedColor + "; -fx-text-fill: white;");
                System.out.println("You hit the key " + keyName);
            }
        });

        btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                btn.setEffect(ds1);
                btn.setEffect(ds2);
                btn.setTranslateX(0);
                btn.setTranslateY(0);
                btn.setStyle("-fx-background-color: "+ keyColor + "; -fx-text-fill: white;");
            }
        });

        return btn;
    }

}

