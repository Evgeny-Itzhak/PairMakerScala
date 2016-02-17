val text: String = "def sort(xs: Array[Int]) {\n  def swap(i: Int, j: Int) {\n    val t = xs(i); //hjkjhkjh\n    xs(i) = xs(j);\n    xs(j) = t\n  }\n  def sort1(l: Int, r: Int) {\n    val pivot = xs((l + r) / 2)\n    var i = l;\n    var j = r\n    while (i <= j) {\n      while (xs(i) < pivot) i += 1\n      while (xs(j) > pivot) j -= 1\n      if (i <= j) {\n        swap(i, j)\n        i += 1\n        j -= 1\n      }\n    }\n    if (l < j) sort1(l, j)\n    if (j < r) sort1(i, r)\n  }\n  sort1(0, xs.length - 1)\n}"
var requiredExpressions: List[String] = List()

/*
 * Preparing separate expressions
 */
var expressions: Array[String] = text.split("\n")
  text match {
    case text if text.isEmpty => println("Input text is empty.")
    case text if text == null => println("Input text is Null")
    case _ => expressions = text.split("\n")
  }
expressions = expressions.map(_.trim).map(_.replaceAll(";","")).map(_.replaceAll("\\/\\/.*",""))
requiredExpressions = expressions.toList.filter(_.startsWith("val")).map(_.replaceFirst("val ",""))

/******************************************************/

/*
 * Divide expressions on key and values and put it into Map
 */
val pairMap =
for(i<- requiredExpressions) yield(
{
  val currentExpression = i.split("=", 2).map(_.trim)
  (currentExpression(0) -> currentExpression(1))
})
println("pairMap: ")
pairMap match {
  case pairMapData if pairMapData.isEmpty => print("There is no any required expressions!")
  case _ => pairMap.foreach(tuple => println(tuple._1 +" => " + tuple._2))
}
/******************************************************/
