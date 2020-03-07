package com.knoldus

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

class StudentConsumer {

  def readStudentFromKafkaTopic(topic: String): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "latest")
    props.put("group.id", "consumer-group")

    val trainee = new KafkaConsumer[String, String](props)

    trainee.subscribe(util.Arrays.asList(topic))
    val timeOut = 1000
    for (data <- trainee.poll(timeOut).asScala.iterator)
      println(data.value)
    trainee.close()
  }
}

object StudentConsumerOb extends App {
  val studentConsumer = new StudentConsumer
  studentConsumer.readStudentFromKafkaTopic("student-details")
}