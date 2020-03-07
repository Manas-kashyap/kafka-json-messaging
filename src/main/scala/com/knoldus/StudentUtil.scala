package com.knoldus

import java.io.{BufferedWriter, File, FileWriter}

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

object StudentUtil {

  def getStudentJsonData: String = {
    """  [
      {
        "studentId": "6330",
        "studentName": "Manas",
        "studentCollege": "Amity"
      }
    ]
  """
  }

  def getStudentDataFromJsonToList(studentJson: String): List[Student] = {
    implicit val formats: DefaultFormats.type = DefaultFormats
    val parsedJsonData = net.liftweb.json.parse(studentJson)
    parsedJsonData.children.map { data =>
      data.extract[Student]
    }
  }

  def writeStudentToFile(studentData: List[Student]): Unit = {
    studentData.foreach(data => {
      val writer = new BufferedWriter(new FileWriter(new File("./src/main/resources/StudentData.txt")
        , true))
      writer.write("\n" + data.toString)
      writer.close()
    })
  }

  def getStudentJsonDataFromObj(student: Student): String = {
    implicit val formats: DefaultFormats.type = DefaultFormats
    write(student)
  }
}
