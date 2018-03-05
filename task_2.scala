val tweetdb = sc.textFile("geotweets.tsv")
val tweets = tweetdb.map(_.split('\t'))

val countryTweets = tweets.map(x => x(1))
countryTweets.countByValue() //Returns a scala.collection.Map[String, Long]
// Sort by key
countryTweets.sortByKey()
// Sort by value