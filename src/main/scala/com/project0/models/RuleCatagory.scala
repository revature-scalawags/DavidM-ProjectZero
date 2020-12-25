package com.project0.models

import spray.json._

case class RuleCatagory(index: String, name: String)

object RuleCatagory{
  def getRuleCatagories(): Array[String] = {
    val ruleCatagoryApi = "https://www.dnd5eapi.co/api/rules"
    val response = io.Source.fromURL(ruleCatagoryApi).mkString.parseJson.prettyPrint
    val jsonString = response
    val fileName = "RuleCatagories.csv"
    val apiResponseArray = Array(jsonString, fileName)
    apiResponseArray
  }
}
