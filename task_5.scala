val tdd = sc.textFile("geotweets.tsv")
val sample = tdd.sample(false, 0.1, 5)

val cityTweetsUS = sample.map(_.split('\t')).
	map(c => (c(4), c(2), c(3))).
	filter(c => c._2 == "US" && c._3 == "city").
	map(_._1).
	countByValue()

println('\n')

val tweetsSorted = cityTweetsUS.toSeq.sortBy(x => (-x._2, x._1))

//val tweetCounts = cityTweetsUS

