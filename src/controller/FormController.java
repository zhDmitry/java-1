package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.LabelValue;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class FormController implements Initializable {

    @FXML
    protected GridPane gridPane;
    @FXML
    protected ChoiceBox<LabelValue> typeSelect;

    @FXML
    protected ChoiceBox<LabelValue> nameSelect;
    @FXML
    protected TextField nameInput;

    @FXML
    protected ChoiceBox<LabelValue> groupSelect;

    @FXML
    protected TextField numberInput;

    @FXML
    protected ChoiceBox<LabelValue> lecturerSelect;

    @FXML
    protected ChoiceBox<LabelValue> semesterSelect;


    @FXML
    protected ChoiceBox<LabelValue> courseSelect;

    @FXML
    protected ChoiceBox<LabelValue> whenSelect;

    @FXML
    protected ChoiceBox<LabelValue> placeSelect;

    @FXML
    protected ChoiceBox<LabelValue> cathedrasSelect;


    protected ScreenController navigator;
    protected  SelectController selects;

    public void setNavigator(ScreenController n){
        this.navigator = n;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");
        this.selects = new SelectController();
        setSelects();
    }

    @FXML
    public void handleBack() throws Exception {
        navigator.activate("main");
    }



    public void  setSelects() {
        ObservableList<LabelValue> semestersOptions = selects.getSemestersOptions();
        semesterSelect.setItems(semestersOptions);
        if(semestersOptions.size()>0) {
            semesterSelect.setValue(semestersOptions.get(0));
        }

//        ObservableList<LabelValue> groupOptions = selects.getGroupsOptions();
//        groupSelect.setItems(groupOptions);
//        if(groupOptions.size()>0) {
//            groupSelect.setValue(groupOptions.get(0));
//        }

        ObservableList<LabelValue> lecturerOptions = selects.getLecturersList();
        lecturerSelect.setItems(lecturerOptions);
        if(lecturerOptions.size()>0) {
            lecturerSelect.setValue(lecturerOptions.get(0));
        }



        ObservableList<LabelValue> courseOptions = selects.getCourseOptions();
        courseSelect.setItems(courseOptions);
        if(courseOptions.size()>0) {
            courseSelect.setValue(courseOptions.get(0));
        }

        ObservableList<LabelValue> placeOptions = selects.getAuditoryOptions();
        placeSelect.setItems(placeOptions);
        if(placeOptions.size()>0) {
            placeSelect.setValue(placeOptions.get(0));
        }

        ObservableList<LabelValue> whenOptopns = selects.getScheduleOptions();
        whenSelect.setItems(whenOptopns);
        if(whenOptopns.size()>0) {
            whenSelect.setValue(whenOptopns.get(0));
        }

        ObservableList<LabelValue> streamSelectOptions = selects.getSteamNameOptions();
        nameSelect.setItems(streamSelectOptions);
        if(streamSelectOptions.size()>0) {
            nameSelect.setValue(streamSelectOptions.get(0));
        }

        ObservableList<LabelValue> cathedrasList = selects.getCathedrasList();
        cathedrasSelect.setItems(cathedrasList);
        if(streamSelectOptions.size()>0) {
            cathedrasSelect.setValue(cathedrasList.get(0));
        }



        ObservableList<LabelValue> typeOptions = selects.getTypeOptions();
        typeSelect.setItems(typeOptions);
        typeSelect.valueProperty().addListener((observable, oldValue, newValue) ->{
            resetVisibility(gridPane);
            if(newValue.value == "group") {
                removeRowFromGridPane(2, gridPane);
            } else {
                removeRowFromGridPane(1, gridPane);
                removeRowFromGridPane(3, gridPane);
                removeRowFromGridPane(4, gridPane);
            }
        });
        if(typeOptions.size()>0) {
            typeSelect.setValue(typeOptions.get(0));
        }





    }
    protected void resetVisibility(GridPane gridPane ) {
        for (Node child : gridPane.getChildren()) {
            child.setVisible(true);
        }
    }

    protected GridPane removeRowFromGridPane(int row, GridPane gridPane ) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : gridPane.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
            }
        }
        for(Node child: deleteNodes) {
            child.setVisible(false);
        }
        return gridPane;
    }

    public void executeQuery(String query) throws Exception {
        Connection conn = DbController.getConnection();
        Statement st;
        st = conn.createStatement();
        st.executeUpdate(query);

    }
}
