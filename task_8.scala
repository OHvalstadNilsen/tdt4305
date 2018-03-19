import org.apache.spark.sql.types._
//Read data file:
val tweetsDF = spark.read.option("sep", "\t").csv("geotweets.tsv").
				createOrReplaceTempView("tweets")

//Count number of tweets:
//FIXME:
val totalCount = spark.sql("SELECT COUNT(1) FROM tweets")
//TODO: ...
