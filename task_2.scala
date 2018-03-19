import java.io._

val tweetdb = sc.textFile("geotweets.tsv")
val tweets = tweetdb.map(_.split('\t'))
//Extract country information
val countryTweets = tweets.map(_(1)).countByValue()
println('\n')
// Sort by value
val countriesAlphabeticallyByValue = countryTweets.toSeq.sortBy(x => (-x._2, x._1))
println('\n')
//Write values to file:
val pw = new PrintWriter(new File("result_2.tsv"))
countriesAlphabeticallyByValue.foreach(x => pw.write(x._1 + '\t' + x._2 + "\n"))
pw.close()
