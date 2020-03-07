package com.knoldus

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class StudentProducer {

  def writeStudentToKafkaTopic(topic: String): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val stud = new KafkaProducer[String, String](props)
    val studentJsonData: String = StudentUtil.getStudentJsonData
    val studentList: List[Student] = StudentUtil.getStudentDataFromJsonToList(studentJsonData)

    studentList.foreach(stud => {
      val studentRecord = new ProducerRecord[String, String](topic, "key", student.toString)
      stud.send(studentRecord)
      println("student info")
    })


    StudentUtil.writeStudentToFile(studentList)

    stud.close()

  }
}
object StudentProducerOb extends App {
  val studentProducer = new StudentProducer
  studentProducer.writeStudentToKafkaTopic("student-details")
}