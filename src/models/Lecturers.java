package models;


public class Lecturers {

  private long id;
  private String name;
  private String position;
  private long cathedraId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }


  public long getCathedraId() {
    return cathedraId;
  }

  public void setCathedraId(long cathedraId) {
    this.cathedraId = cathedraId;
  }

}
