package application;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                "/view/MainPane.fxml"));
        Scene scene = new Scene(parent);
        ScreenController screenController = new ScreenController(scene);
        FXMLLoader rootScreen = new FXMLLoader(getClass().getResource( "/view/MainPane.fxml" ));
        screenController.add("main", rootScreen.load());
        MainController c = rootScreen.getController();
        c.setNavigator(screenController);
        screenController.addBeforeHook("main", (Object meta)->{
            c.setSelects();
            c.handleFilter();
            return null;
        });

        FXMLLoader createScreen = new FXMLLoader(getClass().getResource( "/view/Create.fxml" ));
        screenController.add("create", createScreen.load());
        CreateController c2 = createScreen.getController();
        c2.setNavigator(screenController);
        screenController.addAfterHook("main", (Object meta)->{
            c2.setSelects();
            return null;
        });

        FXMLLoader edit = new FXMLLoader(getClass().getResource( "/view/Edit.fxml" ));
        screenController.add("edit", edit.load());
        EditController c3 = edit.getController();
        c3.setNavigator(screenController);
        screenController.addAfterHook("edit", (Object meta)->{
            c3.setValues();
            return null;
        });
        stage.setScene(scene);
        stage.setTitle("Lessons");
        stage.show();
        screenController.activate("main");


    }

    public static void main(String[] args) {
        launch(args);
    }
}
