package com.project0.models



import scala.util.matching.Regex
import scala.collection.mutable.ListBuffer
import ujson._
import scala.collection.mutable.ArrayBuffer
import com.project0.models.singularModels.Rules._
import scala.io.StdIn.readLine


object Catagories{
    
    case class Catagory(name: String, url: String)

    def getDNDBodyResponseAndCreateCatagoryObjects(): Array[Catagory]={
        
        val catagories = new ArrayBuffer[Catagory]()
        val url = "https://www.dnd5eapi.co/api/"
        var r = requests.get(url)
        val json = ujson.read(r.text()).obj
       for((name,url) <- json){
           val catagory = new Catagory(name,url.toString())
           catagories += catagory
       }
       val catagoriesArray = catagories.toArray
       catagoriesArray
    }

    def main(args: Array[String]){

        val catagories = getDNDBodyResponseAndCreateCatagoryObjects()
        val items = getItemsArray(catagories)
        for(x <- items)println(x.index)
        println("\nWhich Catagory would you like to research?\n")
        val catagory = readLine()
        val ruleDetail = getRuleCatagoryDetail(items,catagory)
        for(x <- ruleDetail){
            for(y <- x.subSections){
                println(y.index)
            }
        }
        println("\nWhat sub-catagory would you like to research?\n")
        val subCatagory = readLine()
        val rule = getRuleDetail(ruleDetail,subCatagory)
        println(rule.desc)  
    } 
}