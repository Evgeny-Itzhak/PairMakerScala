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

    text match {
      case text if text == null => println("\n***\nInput text is Null.\n***\n"); return List()
      case text if text.isEmpty => println("\n***\nInput text is empty.\n***\n"); return List()
      case _ =>
    }

    val expressions: Array[String] = text.split("\n").map(_.trim.replaceAll(";","").replaceAll("\\/\\/.*",""))
      return expressions.toList.filter(_.startsWith("val")).map(_.replaceFirst("val ",""))
  }

  override def makePair(requiredExpressions: List[String]): Map[String, String] = {
    val pairMap =
    for(i <- requiredExpressions) yield(
    {
      val currentExpression = i.split("=", 2).map(_.trim)
      (currentExpression(0) -> currentExpression(1))

    })
    println("name => expression: \n")
    pairMap match {
      case pairMapData if pairMapData.isEmpty => print("\n*----*\nThere is no any required expressions!\n*----*\n"); return pairMap.toMap;
      case _ => pairMap.foreach(tuple => println(tuple._1 +" => " + tuple._2)); return pairMap.toMap;
    }
  }

}
