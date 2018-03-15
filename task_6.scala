import scala.io.Source

val rdd = sc.textFile("geotweets.tsv")
val sample = rdd.sample(false, 0.1, 5)
//Open stop words-file:
//val stop_words = Source.fromFile("stop_words.txt").getLines.toList

val mostFrequentWords = sample.map(_.split('\t')).
						map(x => (x(2), x(10)).
						filter(x => x._2 == "US")


/*
	Filter(countrycode) --> split tweets into words --> filter out short words
	and stop words --> sort by occurence frequency --> Return top ten
*/
