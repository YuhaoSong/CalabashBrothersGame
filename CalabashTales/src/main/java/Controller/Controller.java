package Controller;

import Model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    @FXML private BorderPane borderpane;
    @FXML private MenuItem openIcon;
    @FXML private MenuItem saveIcon;
    @FXML private MenuItem startIcon;
    @FXML private MenuItem infoIcon;
    @FXML private AnchorPane anchorpane;
    private ExecutorService exec = Executors.newCachedThreadPool();
    static Model model;

    public void init()
    {
        model=new Model(anchorpane);
        borderpane.setFocusTraversable(true);
        borderpane.addEventFilter(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event)
            {
                System.out.print(event.getCode());

                if(event.getCode()== KeyCode.SPACE)
                {
                    exec.execute(model);
                }

            };

        });
    }


    public void openFile(ActionEvent actionEvent)
    {
        FileChooser chooser = new FileChooser(); // 创建一个文件对话框
        chooser.setTitle("打开文件"); // 设置文件对话框的标题
        chooser.setInitialDirectory(new File("E:\\")); // 设置文件对话框的初始目录
        // 给文件对话框添加多个文件类型的过滤器
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("所有文本", "*.txt"));
        // 显示文件打开对话框，且该对话框支持同时选择多个文件
        File file = chooser.showOpenDialog(null);

    }


    public void saveFile(ActionEvent actionEvent)
    {
        System.out.print("hadisufgboda");
    }

    public void fightStart(ActionEvent actionEvent)
    {
       exec.execute(model);
    }

    public void showInfo(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("This product is created by yhsong");
        alert.setContentText("email: yhsong.nju@gmail.com");
        alert.showAndWait();
    }
}
