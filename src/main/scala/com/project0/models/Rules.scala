package com.project0.models.singularModels

import scala.collection.mutable.ArrayBuffer
import com.project0.models.Catagories.Catagory
import ujson._
import scala.util.matching.Regex


object Rules {

    case class Item(name:String,index:String,url:String)

    def getItemsArray(catagories: Array[Catagory]): Array[Item]={
        val items = new ArrayBuffer[Item]()
        val ruleUrl = catagories(13).url.replace("\"","")
        val url = s"https://www.dnd5eapi.co"+ruleUrl
        val r = requests.get(url)
        val json = ujson.read(r.text())
        val itemsArr = json("results").arr.value
        for(x <- itemsArr){
            val json = ujson.read(x)
            val name = json("name")
            val index = json("index")
            val url = json("url")
            val item = new Item(name.toString(),index.toString(),url.toString())
            items += item
        }
        items.toArray
    }

    case class RuleCatagoryDetail(name: String, index: String, desc: String, subSections: Array[SubSection])
    case class SubSection(name: String, index: String, url: String)

    def getRuleCatagoryDetail(items: Array[Item],selection: String): Array[RuleCatagoryDetail]={
        val rules = new ArrayBuffer[RuleCatagoryDetail]()
        var ruleCatagoryDetailUrl = "https://www.dnd5eapi.co/api/rules/"+selection
        val r = requests.get(ruleCatagoryDetailUrl)
        val json = ujson.read(r.text())
        val name = json("name")
        val index = json("index")
        val desc = json("desc")
        val subSections = json("subsections").arr.value
        val subSectionsArray = new ArrayBuffer[SubSection]()
        for (x <- subSections){
            val json = ujson.read(x)
            val name = json("name")
            val index = json("index")
            val url = json("url")
            val subsection = new SubSection(name.toString(),index.toString(),url.toString())
            subSectionsArray += subsection
        }
        val rule = new RuleCatagoryDetail(name.toString(),index.toString(),desc.toString(),subSectionsArray.toArray)
        rules += rule
        rules.toArray   
    }

    case class Rule(name:String,index:String,desc:String,url:String)

    def getRuleDetail(rulesCatagoryDetail: Array[RuleCatagoryDetail], selection: String): Rule={
        val ruleUrl = "https://www.dnd5eapi.co/api/rule-sections/"+selection
        val r = requests.get(ruleUrl)
        val json = ujson.read(r.text())
        val name = json("name")
        val index = json("index")
        val desc = json("desc")
        val url = json("url")
        val rule = new Rule(name.toString(),index.toString(),desc.toString(),url.toString())
        
        rule
    }
}

