package com.techinsight

import org.scalatest.{Tag, BeforeAndAfterEach, FunSuite}

/**
 * @author  Itzhak
 */
class TextHandlerTest extends FunSuite with BeforeAndAfterEach {

  val textToParsePositive = "def sort(xs: Array[Int]) {\n  def swap(i: Int, j: Int) {\n    val t = xs(i);\n    xs(i) = xs(j);\n    xs(j) = t\n  }\n  def sort1(l: Int, r: Int) {\n    val pivot = xs((l + r) / 2)\n    var i = l;\n    var j = r\n    while (i <= j) {\n      while (xs(i) < pivot) i += 1\n      while (xs(j) > pivot) j -= 1\n      if (i <= j) {\n        swap(i, j)\n        i += 1\n        j -= 1\n      }\n    }\n    if (l < j) sort1(l, j)\n    if (j < r) sort1(i, r)\n  }\n  sort1(0, xs.length - 1)\n}"
  val textToParseNegative = "def sort(xs: Array[Int]) {\n  def swap(i: Int, j: Int) {xs(i) = xs(j);}"
  val textNull: String = null
  val textEmpty: String = ""

  var requiredExpressionsWithTextPositive = List("t = xs(i)", "pivot = xs((l + r) / 2)")
  var requiredExpressionsWithTextNegative = List()


  test("testPrepareExpression should return ArrayList with expressions") {
    assertResult(List("t = xs(i)", "pivot = xs((l + r) / 2)"),"Actual result doesn't matches expected")(TextHandler.prepareExpression(textToParsePositive))
  }

  test("testPrepareExpression should return empty ArrayList in case argument doesn't contain required expressions or it's null or empty") {
    assert((TextHandler.prepareExpression(textEmpty).isEmpty), "list should be empty when argument doesn't contain required expressions")
    assert((TextHandler.prepareExpression(textNull).isEmpty), "list should be empty when argument is null")
    assert((TextHandler.prepareExpression(textToParseNegative).isEmpty), "list should be empty argument is empty")
  }

  test("testMakePair should return Map with (names => expressions)") {
    assertResult(Map("t" -> "xs(i)", "pivot" -> "xs((l + r) / 2)"),"Actual result doesn't matches expected")(TextHandler.makePair(requiredExpressionsWithTextPositive))
  }

  test("testMakePair should return empty Map() in case argument doesn't contain required expressions or it's null or empty") {
    assert(TextHandler.makePair(requiredExpressionsWithTextNegative).isEmpty, "list should be empty when argument is empty")
  }

}
