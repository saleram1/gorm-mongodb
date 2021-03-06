package org.acme

import com.google.code.morphia.annotations.Id
import com.google.code.morphia.Key
import com.google.code.morphia.utils.IndexDirection

class Task {

  @Id
  String taskId // test manual id

  Key<Project> projectId
  String name

  Date startDate
  Date completionDate
  Integer estimatedHours
  Integer actualHours
  Boolean incomplete // if null, it is not stored in db

  String description

  Date dateCreated
  Date lastUpdated

  int version

  transient String pass = "pass" // test ignore transients

  static indexes = {
    idx_date unique:true, dropDups:true, fields:[asc('dateCreated'), desc('lastUpdated')]
    idx_project fields:[desc('projectId'), 'startDate'] // if direction is omitted, asc is default
    idx_incomplete sparse:true, fields:['incomplete'] // try sparse index
  }

  static constraints = {
    projectId nullable: true
    description nullable: true
    name blank: false
    actualHours nullable: true
    estimatedHours nullable: true
    startDate nullable: true
    completionDate nullable: true
    incomplete nullable: true
  }

  def beforeSave = {
//    println "Task before save: $taskId"
  }

  def afterSave = {
//    println "Task after save: $taskId"
  }

  def beforeDelete = {
//    println "Task before delete: $taskId"
  }

  def afterDelete = {
//    println "Task after delete: $taskId"
  }

  def beforeValidate = {
//    println "Task before validate: $taskId"
  }


  public String toString ( ) {
    return "Task{" +
        "taskId='" + taskId + '\'' +
        ", projectId='" + projectId + '\'' +
        ", name='" + name + '\'' +
        ", startDate=" + startDate +
        ", completionDate=" + completionDate +
        ", estimatedHours=" + estimatedHours +
        ", actualHours=" + actualHours +
        ", description='" + description + '\'' +
        ", dateCreated=" + dateCreated +
        ", lastUpdated=" + lastUpdated +
        ", pass='" + pass + '\'' +
        '}' ;
  }}
