import java.util.Date
import java.io._
/*
Sorterer timesintervallene etter antall tweets (høy til lav) globalt.

TODO: Gjøre dette landspesifikt og printe topintervallet.
Nåværende output til fil: "timeintervall 	antall"
Ønsket output til fil: "land 	timeintervall 	antall"
*/

val tweetdb = sc.textFile("geotweets.tsv")
val tweets = tweetdb.map(_.split('\t'))
//local UTC time divided in hours after midnight
val localTimesUTC = tweets.map(x => (x(1), ((x(0).toFloat/1000 + x(8).toFloat)/3600) % 24)).
					map(x => scala.math.floor(x._2).toInt).
					countByValue().
					toSeq.sortBy(x => -x._2).
					take(1)

val pw = new PrintWriter(new File("result_4.tsv"))
localTimesUTC.foreach(x => pw.write(x._1 + "\t" + x._2 + "\n"))
pw.close()

/*
1. Find local tweet times
2. Find hourly time intervals (modulo 24)
3. Categorize tweets in their time intevals --> groupBy()
4. Sort time intervals from most to fewest tweets
5. Write to output file 
*/
