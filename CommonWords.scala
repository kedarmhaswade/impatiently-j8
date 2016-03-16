:q
import scala.io.Source
/* find most used words in a text */
/* Created by sapodaca@natera.com 3/10/16 */

object CommonWords extends App {
  /* given a filename, get all words from the file */
  def words(filename: String) = Source.fromFile(filename).mkString.toLowerCase.split("\\P{L}+")
  /* get top n most used words and the count of their usages */
  def topWords(file: String, n: Int) = words(file).groupBy(w => w).mapValues(_.size).toList.sortBy(-_._2).take(n)
  println (topWords("warandpeace.txt", 10))
}
