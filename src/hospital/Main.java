package hospital;

import hospital.controller.MainController;
import hospital.controller.StorageEditController;
import hospital.model.TableConstructor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        primaryStage.getIcons().add(new Image("hospital/resources/icon.png"));
        primaryStage.setTitle("Управление персоналом больницы");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void openAddDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/StorageAddDialog.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setTitle("Добавить данные");
            stage.setScene(new Scene(root, 450, 450));
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openEditDialog(TableConstructor table) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("view/StorageEditDialog.fxml"));
            Parent root = Loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setResizable(false);
            stage.setTitle("Редактировать данные");

            StorageEditController controller = Loader.getController(); //получаем контроллер для второй формы
            controller.setHospital(table);

            stage.setScene(new Scene(root, 450, 450));
            stage.showAndWait();




        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}