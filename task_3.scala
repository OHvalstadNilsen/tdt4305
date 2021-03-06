import java.io._

//Get tweets
val cdd = sc.textFile("geotweets.tsv")

//Split by tabs, and get country, latitude, longitude
val countryCoors = cdd.
	map(_.split('\t')).
	map(c => (c(1), (c(11).toFloat, c(12).toFloat)))

//Sum lats and longs and count tweets by country, filter out <10 tweet countries and calculate average position
val avgCoors = countryCoors.
	aggregateByKey((0.0, 0.0, 0))(
		(accum, v) => (accum._1 + v._1, accum._2 + v._2, accum._3 + 1),
		(v1, v2) => (v1._1 + v2._1, v1._2 + v2._2, v1._3 + v2._3)).
	filter{case (key, (value1, value2, value3)) => value3 > 10}.
	mapValues(c => (1.0 * c._1 / c._3, 1.0 * c._2 / c._3)).take(2000)

val pw = new PrintWriter(new File("result_3.tsv"))
avgCoors.foreach(x => pw.write(x._1 + '\t' + x._2._1 + '\t' + x._2._2 + '\n'))
pw.close()
