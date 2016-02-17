package com.techinsight

/**
 * @author  Itzhak
 */

sealed trait OperateText {
  def prepareExpression(text: String): List[String]
  def makePair (text: List[String]): Map[String, String]
}

case class Text(inputText: String)

object TextHandler extends OperateText{

  override def prepareExpression(text: String): List[String] = {
    var requiredExpressions: List[String] = List()

    text match {
      case text if text == null => println("\n***\nInput text is Null.\n***\n"); return requiredExpressions
      case text if text.isEmpty => println("\n***\nInput text is empty.\n***\n"); return requiredExpressions
      case _ =>
    }

    var expressions: Array[String] = text.split("\n")
    expressions = expressions.map(_.trim).map(_.replaceAll(";","")).map(_.replaceAll("\\/\\/.*",""))
    requiredExpressions = expressions.toList.filter(_.startsWith("val")).map(_.replaceFirst("val ",""))
    return requiredExpressions
  }

  override def makePair(requiredExpressions: List[String]): Map[String, String] = {
    var pairMap =  Map[String, String]()
    for(i <- requiredExpressions)
    {
      val currentExpression = i.split("=", 2).map(_.trim)
      pairMap += (currentExpression(0) -> currentExpression(1))
    }
    println("name => expression: \n")
    pairMap match {
      case pairMapData if pairMapData.isEmpty => print("\n*----*\nThere is no any required expressions!\n*----*\n"); return pairMap;
      case _ => pairMap.foreach(tuple => println(tuple._1 +" => " + tuple._2)); return pairMap;
    }
  }

}
