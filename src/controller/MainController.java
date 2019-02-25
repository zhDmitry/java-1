package controller;

import javafx.scene.control.*;
import library.ActionButtonTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import models.LabelValue;
import models.LessonsDTO;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.ResourceBundle;





public class MainController implements Initializable {

    @FXML
    private TableView<LessonsDTO> TableView;
    
    @FXML
    private TableColumn<LessonsDTO, String> typeColumn;

    @FXML
    private TableColumn<LessonsDTO, String> lecturerColumn;
    @FXML
    private TableColumn<LessonsDTO, String> scheduleColumn;
    @FXML
    private TableColumn<LessonsDTO, String> courseColumn;

    @FXML
    private TableColumn<LessonsDTO, String> auditoryColumn;
    @FXML
    private TableColumn<LessonsDTO, String> cathedraColumn;
    @FXML
    private TableColumn<LessonsDTO, String> semesterColumn;
    @FXML
    private TableColumn<LessonsDTO, Button> actionsColumn;


    @FXML
    private ChoiceBox<LabelValue> semesterSelect;

    @FXML
    private ChoiceBox<LabelValue> groupSelect;

    @FXML
    private ChoiceBox<LabelValue> lecturerSelect;

    @FXML
    private ChoiceBox<LabelValue> cathedraSelect;

    @FXML
    public void handleFilter() {

        ArrayList<String> query = new ArrayList<String>();

        if(semesterSelect.getValue().value != "") {
            query.add("S3.id="+semesterSelect.getValue().value);
        }

        if(groupSelect.getValue().value != "") {
            query.add("G.id="+groupSelect.getValue().value );
        }

        if(lecturerSelect.getValue().value != "") {
            query.add("L2.id="+lecturerSelect.getValue().value);
        }
        if(cathedraSelect.getValue().value != "") {
            query.add("C2.id="+cathedraSelect.getValue().value);
        }
        String sqlQuery = String.join(" AND\n", query);
        System.out.println(sqlQuery);

        if(sqlQuery!= "" && sqlQuery.length()>0) {
            sqlQuery = "WHERE " + sqlQuery;
        }
        System.out.println(sqlQuery);

        showLessons(sqlQuery);

    }

    @FXML
    private void handleCreate() {
        this.navigator.activate("create");
    }

    private SelectController selects;
    private ScreenController navigator;

    public void setNavigator(ScreenController n){
        this.navigator = n;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showLessons("");
        this.selects = new SelectController();
        setSelects();
        System.out.println("init main");
    }




    public ObservableList<LessonsDTO> getLessonsList(String subquery){
        ObservableList<LessonsDTO> lessonsList = FXCollections.observableArrayList();
        Connection connection = DbController.getConnection();
        String query = "SELECT Lessons.id as id,\n" +
                "    L2.name as lecturer,\n" +
                "    C.name as course_name,\n" +
                "    C2.name as cathedra,\n" +
                "    S3.semester as semester,\n" +
                "    S3.year as semester_year,\n" +
                "    Lessons.type as type,\n" +
                "    S2.Day as lesson_day,\n" +
                "    S2.LessonOrder as lesson_no,\n" +
                "    A.hall as hall,\n" +
                "    A.num as auditory_number\n" +
                "from Lessons\n" +
                "  LEFT OUTER JOIN `Group` G on Lessons.id = G.id\n" +
                "  LEFT OUTER JOIN `Stream` S on Lessons.id = S.id\n" +
                "  LEFT OUTER JOIN `Courses` C on Lessons.course_id = C.id\n" +
                "  LEFT OUTER JOIN Cathedras C2 on C.cathedra_id = C2.id\n" +
                "  LEFT OUTER JOIN Schedule S2 on Lessons.schedule_id = S2.id\n" +
                "  LEFT OUTER JOIN Semesters S3 on Lessons.semester_id = S3.id\n" +
                "  LEFT OUTER JOIN AuditoryToLessons ATL on Lessons.id = ATL.lesson_id\n" +
                "  LEFT OUTER JOIN  Auditory A on ATL.auditory_id = A.id\n" +
                "   JOIN Lecturers L2 on L2.id = lecturer_id\n";
        if(subquery.equals("")) {
            query+= "\n" + subquery;
        }

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                LessonsDTO lesson = new LessonsDTO(
                        rs.getString("id"),
                        rs.getString("lecturer"),
                        rs.getString("course_name"),
                        rs.getString("cathedra"),
                        rs.getString("semester"),
                        rs.getString("semester_year"),
                        rs.getString("type"),
                        rs.getString("lesson_day"),
                        rs.getString("lesson_no"),
                        rs.getString("hall"),
                        rs.getString("auditory_number")
                );
                lessonsList.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessonsList;
    }




    public void  setSelects() {
        LabelValue all = new LabelValue("all", "");
        ObservableList<LabelValue> semestersOptions = selects.getSemestersOptions();
        semestersOptions.add(all);
        semesterSelect.setItems(semestersOptions);
        semesterSelect.setValue(all);

        ObservableList<LabelValue> groupOptions = selects.getGroupsOptions();
        groupOptions.add(all);
        groupSelect.setItems(groupOptions);
        groupSelect.setValue(all);

        ObservableList<LabelValue> lecturerOptions = selects.getLecturersList();
        lecturerOptions.add(all);
        lecturerSelect.setItems(lecturerOptions);
        lecturerSelect.setValue(all);


        ObservableList<LabelValue> cathedraOptions = selects.getCathedrasList();
        cathedraOptions.add(all);
        cathedraSelect.setItems(cathedraOptions);
        cathedraSelect.setValue(all);

    }

    public void showLessons(String subQuery) {
        ObservableList<LessonsDTO> list = getLessonsList(subQuery);
        typeColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("type"));

        lecturerColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("lecturer"));
        scheduleColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("lesson_time"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("course_name"));
        auditoryColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("place"));
        cathedraColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("cathedra"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<LessonsDTO,String>("time"));

        actionsColumn.setCellFactory(ActionButtonTableCell.<LessonsDTO>forTableColumn("Edit", (LessonsDTO p) -> {
            EditController.currentAlert = p.getId();
            navigator.activate("edit");
            return null;
        }));
        TableView.setItems(list);
    }


}
