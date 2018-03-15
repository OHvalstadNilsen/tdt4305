import java.util.Date

val tweetdb = sc.textFile("geotweets.tsv")
val tweets = tweetdb.map(_.split('\t'))
//local UTC time in seconds:
//val localTimesUTC = tweets.map(x => (x(1), (x(0).toFloat/1000 + x(8).toFloat)%24))
val localTimesUTC = tweets.map(x => (x(1), x(0).toFloat/1000 + x(8).toFloat))
localTimesUTC.take(6)
println('\n')
tweets.first()


/*
1. Find local tweet times
2. Find hourly time intervals (modulo 24)
3. Categorize tweets in their time intevals
4. Sort time intervals from most to fewest tweets
5. Write to output file 
*/

//mapValue