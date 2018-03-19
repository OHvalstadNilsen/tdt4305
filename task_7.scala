val tdd = sc.textFile("geotweets.tsv")
val sample = tdd.sample(false, 0.1, 5)
val stop_words = sc.textFile("stop_words.txt")

//Find the 5 cities in the US with the highest number of tweets, ordered:
val cityTweetsUS = sample.map(_.split('\t')).
	map(c => (c(4), c(2), c(3), c(10).toLowerCase)).
	filter(c => c._2 == "US" && c._3 == "city")

val cityTweetsMostTweets = cityTweetsUS.map(_._1).
	countByValue().
	toSeq.sortBy(x => (-x._2, x._1)).
	take(5)

val topCities = cityTweetsUS.map(_._1)

//TODO: Finish intersect statement, or find some alternative
//val cityTweetsTopFive = cityTweetsMostTweets.intersect()
//TODO: Implement code from task 6 to filter out stop words