import java.util.Date
import java.io._
/*
Sorts the time intervals according to their number og tweets globally.

TODO: Include country in output - it should not be global.
Current outout to file: "timeintervall 	antall"
Correct output to file: "land 	timeintervall 	antall"
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

