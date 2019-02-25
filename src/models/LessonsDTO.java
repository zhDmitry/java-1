package models;

public class LessonsDTO {
    private String id;
    private String lecturer;
    private String course_name;
    private String cathedra;
    private String semester;
    private String semester_year;
    private String type;
    private String lesson_day;
    private String lesson_no;
    private String hall;
    private String auditory_number;

    private String place;
    private String time;


    public LessonsDTO(String id,
             String lecturer,
             String course_name,
             String cathedra,
             String semester,
             String semester_year,
             String type,
             String lesson_day,
             String lesson_no,
             String hall,
             String auditory_number) {
        this.id=id;
        this.course_name = course_name;
        this.auditory_number=auditory_number;
        this.hall = hall;
        this.semester=semester;
        this.semester_year = semester_year;
        this.lecturer= lecturer;
        this.cathedra = cathedra;
        this.lesson_no = lesson_no;
        this.lesson_day = lesson_day;
        this.type = type;
    }
    public String getPlace(){
        return this.auditory_number + " auditory";
    }

    public String getTime(){
        return this.semester + " semester, " + this.semester_year;
    }

    public String getCathedra() {
        return cathedra;
    }

    public String getId() {
        return id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getCourse_name() {
        return course_name;
    }
    public String get() {
        return course_name;
    }

    public String getLesson_time() {
        return lesson_day + "day " + lesson_no + "lesson";
    }

    public String getType() {
        return type;
    }
}
