import scala.io.Source

val rdd = sc.textFile("geotweets.tsv")
val sample = rdd.sample(false, 0.1, 5)
val stop_words = sc.textFile("stop_words.txt")

val UStweets = sample.map(_.split('\t')).
	map(x=> (x(2), x(10))).
	filter(x => x._1 =="US").
	map(x => x._2.toLowerCase)

//Remove words:
//FIXME: Remove words does not work properly
//FIXME: Very slow. Can this be done more effectively?
val stop_words_removed = UStweets.subtract(stop_words).
	flatMap(x => x.split(" ")).
	filter(w => w.length() > 1)

val word_freqiencies = stop_words_removed.countByValue().
	toSeq.sortBy(x => -x._2)
