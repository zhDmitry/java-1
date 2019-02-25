package controller;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import models.LabelValue;
import models.Lesson;

public class EditController extends FormController {

    public static String currentAlert;
    @FXML
    public void handleSave(){
        LabelValue type = typeSelect.getValue();
        LabelValue nameSelectValue = nameSelect.getValue();
        String nameText = nameInput.getText();
        LabelValue lecturer = lecturerSelect.getValue();
        String semester = semesterSelect.getValue().value;
        String course = courseSelect.getValue().value;

        LabelValue whenSelectValue = whenSelect.getValue();
        LabelValue cathedraValue = placeSelect.getValue();

        if(type.value.equals("stream")) {
            updateStream(lecturer.value, cathedraValue.value, semester, course, whenSelectValue.value, nameText);
        } else {
            updateGroup(lecturer.value, cathedraValue.value, semester, course, whenSelectValue.value,nameSelectValue.value, numberInput.getText());        }
    }

    public void updateStream(String lecturer, String cathedra_id,  String semester_id, String course_id, String schedule_id, String streamName ) {
        try {
            String query = "UPDATE deanery.Lessons SET "+
                    "schedule_id = "+schedule_id+","+
                    "lecturer_cathedra_id = "+cathedra_id+","+
                    "course_cathedra_id = "+cathedra_id+","+
                    "semester_id = "+semester_id+","+
                    "course_id = "+course_id+","+
                    "lecturer_id = "+lecturer+","+
                    "schedule_id = "+schedule_id+
                    " WHERE id = "+currentAlert+"\n";
            System.out.println(query);

            executeQuery(query);

            String streamInsertQuery=  "UPDATE deanery.Stream SET "+
                    "name='"+ streamName + "' WHERE id = "+currentAlert+"\n";
            System.out.println(streamInsertQuery);
            executeQuery(streamInsertQuery);
        } catch(Exception e){
            e.printStackTrace();
        }
        navigator.activate("main");
    }

    public void updateGroup(String lecturer, String cathedra_id,  String semester_id, String course_id, String schedule_id, String stream_id, String group_number) {
        try {
            String query = "UPDATE deanery.Lessons SET "+
                    "schedule_id = "+schedule_id+","+
                    "lecturer_cathedra_id = "+cathedra_id+","+
                    "course_cathedra_id = "+cathedra_id+","+
                    "semester_id = "+semester_id+","+
                    "course_id = "+course_id+","+
                    "lecturer_id = "+lecturer+","+
                    "schedule_id = "+schedule_id+
                    " WHERE id = "+currentAlert+"\n";

            executeQuery(query);
            String groupInsertQuery= "UPDATE deanery.Group SET " +
                    "group_no="+group_number+","+
                "stream_id="+stream_id+ " WHERE id = "+currentAlert+"\n";
            System.out.println(groupInsertQuery);
            executeQuery(groupInsertQuery);
        } catch(Exception e){
            e.printStackTrace();
        }
        navigator.activate("main");
    }
    @FXML
    public void handleDelete(){
        try {
            String query = "DELETE FROM deanery.Group WHERE id="+currentAlert;
            executeQuery(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    try {
        String query = "DELETE FROM deanery.Stream WHERE id="+currentAlert;
        executeQuery(query);
    } catch (Exception e){
        e.printStackTrace();
    }


        String query = "DELETE FROM deanery.Lessons WHERE id="+currentAlert;
    try {
        executeQuery(query);
    } catch (Exception e){
        e.printStackTrace();
    }
    navigator.activate("main");
    }

    public LabelValue getValue(String filterBy, ChoiceBox<LabelValue> select) {
        FilteredList<LabelValue> filtered = select.getItems().filtered(el -> el.value.equals(filterBy));
        if(filtered.size()>0){
            return filtered.get(0);
        }
        return select.getItems().get(0);
    }

    public void setValues(){
        Lesson lesson= SelectController.getById(currentAlert);
        System.out.println(typeSelect.getItems());
        typeSelect.setValue(getValue(lesson.type, typeSelect));
        semesterSelect.setValue(getValue(lesson.semesterId, semesterSelect));
        lecturerSelect.setValue(getValue(lesson.lecturerId, lecturerSelect));
        numberInput.setText(lesson.group_no);
        courseSelect.setValue(getValue(lesson.lecturerId, courseSelect));
        nameInput.setText(lesson.name);
        nameSelect.setValue(getValue(lesson.stream_id, nameSelect));
    }

}
