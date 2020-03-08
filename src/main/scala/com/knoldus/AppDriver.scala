package com.knoldus

import net.liftweb.json.DefaultFormats



object AppDriver extends App {
  implicit val formats = DefaultFormats

  def listProducer: List[User] = {
    val parsedJsonData = net.liftweb.json.parse(getJsonString())
    val data = parsedJsonData.children.map { data =>
      data.extract[User]
    }
    data
  }

  def getJsonString(): String = {
    implicit val formats = DefaultFormats
    val jsonString =
      """  [
     {
       "id": 6330,
       "name":"Manas",
       "age":25,
       "address":"Delhi"
}

    ]
  """
    jsonString
  }
}
