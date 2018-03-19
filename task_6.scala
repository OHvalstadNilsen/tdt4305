import scala.io.Source
import java.io._

val rdd = sc.textFile("geotweets.tsv")
val sample = rdd.sample(false, 0.1, 5)
val stop_words = sc.textFile("stop_words.txt")

val UStweets = sample.map(_.split('\t')).
	map(x=> (x(2), x(10))).
	filter(x => x._1 =="US").
	map(x => x._2.toLowerCase)

//Remove words:
val stop_words_removed = UStweets.flatMap(x => x.split(" ")).
	filter(w => w.length > 1).
	subtract(stop_words)

var word_freqiencies = stop_words_removed.countByValue().
	toSeq.sortBy(x => -x._2)
println('\n')
word_freqiencies = word_freqiencies.take(10)

//Print values:
val pw = new PrintWriter(new File("result_6.tsv"))
pw.write(word_freqiencies.mkString("\n"))
pw.close()

//TODO: Change output format to tsv.
