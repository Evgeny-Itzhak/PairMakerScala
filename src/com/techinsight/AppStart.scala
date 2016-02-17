package com.techinsight

/**
 * @author  Itzhak
 */
object AppStart extends App{

  val textToParse = "def sort(xs: Array[Int]) {\n  def swap(i: Int, j: Int) {\n    val t = xs(i);\n    xs(i) = xs(j);\n    xs(j) = t\n  }\n  def sort1(l: Int, r: Int) {\n    val pivot = xs((l + r) / 2)\n    var i = l;\n    var j = r\n    while (i <= j) {\n      while (xs(i) < pivot) i += 1\n      while (xs(j) > pivot) j -= 1\n      if (i <= j) {\n        swap(i, j)\n        i += 1\n        j -= 1\n      }\n    }\n    if (l < j) sort1(l, j)\n    if (j < r) sort1(i, r)\n  }\n  sort1(0, xs.length - 1)\n}"

  /*run with object creation*/
  val textInObject = Text(textToParse)
  TextHandler.makePair(TextHandler.prepareExpression(textInObject.inputText))

  /*run without object creation*/
  val preparedExpression = TextHandler.prepareExpression(textToParse)
  TextHandler.makePair(preparedExpression)


}
