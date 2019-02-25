package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.LabelValue;

import java.sql.Connection;
import java.sql.Statement;

public class CreateController extends FormController {

    @FXML
    public void handleSave(){
        LabelValue type = typeSelect.getValue();
        LabelValue nameSelectValue = nameSelect.getValue();
        String nameText = nameInput.getText();
        LabelValue lecturer = lecturerSelect.getValue();
        String semester = semesterSelect.getValue().value;
        String course = courseSelect.getValue().value;

        LabelValue whenSelectValue = whenSelect.getValue();
        LabelValue cathedraValue = cathedrasSelect.getValue();

        if(type.value == "stream") {
            createStream(lecturer.value, cathedraValue.value, semester, course, whenSelectValue.value, nameText);
        } else {
            createGroup(lecturer.value, cathedraValue.value, semester, course, whenSelectValue.value,nameSelectValue.value, numberInput.getText());        }
    }

    public void createStream(String lecturer, String cathedra_id,  String semester_id, String course_id, String schedule_id, String streamName ) {
        try {
        String query = "INSERT INTO deanery.Lessons (type, lecturer_id, lecturer_cathedra_id, semester_id, course_id, course_cathedra_id, schedule_id) VALUES ('stream', "
                +lecturer+
                ", "+cathedra_id+", "+semester_id+", "+course_id+", "+cathedra_id+", "+schedule_id+");\n";
        System.out.println(query);
        executeQuery(query);

        String lastID = SelectController.getLastId("SELECT id FROM Lessons ORDER BY id DESC LIMIT 1;\n");
        String streamInsertQuery= "INSERT INTO deanery.Stream (name, id) VALUES ('" +streamName+"',"+lastID+");\n";
        System.out.println(streamInsertQuery);
        executeQuery(streamInsertQuery);
        } catch(Exception e){
            e.printStackTrace();
        }
        navigator.activate("main");
    }
    public void createGroup(String lecturer, String cathedra_id,  String semester_id, String course_id, String schedule_id, String stream_id, String group_number) {
        try {
            String query = "INSERT INTO deanery.Lessons (type, lecturer_id, lecturer_cathedra_id, semester_id, course_id, course_cathedra_id, schedule_id) VALUES ('group', "
                    +lecturer+
                    ", "+cathedra_id+", "+semester_id+", "+course_id+", "+cathedra_id+", "+schedule_id+");\n";
            System.out.println(query);
            executeQuery(query);
            String lastID = SelectController.getLastId("SELECT id FROM Lessons ORDER BY id DESC LIMIT 1;\n");
            String groupInsertQuery= "INSERT INTO deanery.Group (group_no, stream_id, id) VALUES (" +group_number+","+stream_id+","+lastID+");\n";
            System.out.println(groupInsertQuery);
            executeQuery(groupInsertQuery);
        } catch(Exception e){
            e.printStackTrace();
        }
        navigator.activate("main");
    }



}

