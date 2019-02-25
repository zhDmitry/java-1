package com.sample;


public class Schedule {

  private long id;
  private String day;
  private long lessonOrder;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }


  public long getLessonOrder() {
    return lessonOrder;
  }

  public void setLessonOrder(long lessonOrder) {
    this.lessonOrder = lessonOrder;
  }

}
