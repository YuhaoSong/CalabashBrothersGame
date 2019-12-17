package Controller;

import Model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller implements Runnable{
    @FXML private BorderPane borderpane;
    @FXML private MenuItem openIcon;
    @FXML private MenuItem saveIcon;
    @FXML private MenuItem startIcon;
    @FXML private MenuItem infoIcon;
    @FXML private Canvas canvas;

    static Model model;
    public void init()
    {
        if(canvas==null)
        {
            System.out.println("NO!!!!!!!!!!!");
        }
        else
        {
            System.out.println("Yes!!!!!!!!!!!");
        }
        model=new Model(canvas);
    }
    public void run() {

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
        try
        {
            new Thread(model).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
