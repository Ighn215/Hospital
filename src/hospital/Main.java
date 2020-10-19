package hospital;

import hospital.controller.MainController;
import hospital.controller.StorageDialogController;
import hospital.model.TableConstructor;
import javafx.application.Application;
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
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("view/Main.fxml"));
        Parent root = Loader.load();
        primaryStage.getIcons().add(new Image("hospital/resources/icon.png"));
        primaryStage.setTitle("Управление персоналом больницы");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        MainController controller = Loader.getController(); //получаем контроллер для второй формы
        controller.setMain(this);
        primaryStage.show();

    }

    public boolean openDialog(TableConstructor table) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("view/StorageDialog.fxml"));
            Parent root = Loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOpacity(1);
            stage.setResizable(false);
            stage.setTitle("Редактировать данные");

            StorageDialogController controller = Loader.getController(); //получаем контроллер для второй формы
            controller.setHospital(table);

            stage.setScene(new Scene(root));
            stage.showAndWait();
            return controller.isOkClicked();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}