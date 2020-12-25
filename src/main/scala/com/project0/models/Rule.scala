package com.project0.models

case class Rule(index: String, name: String, description: String, Subsections: List[Subsection])

object Rule {
  def getRuleInfo(): Unit ={
    val ruleAPI = "https://www.dnd5eapi.co/api/rules"
  }
}