package edu.knoldus

import edu.knoldus.InputValues._

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object TweetOperation extends App {

  log.info("Enter Hashtag: ")
  //scalastyle:off
  val hashtag = "#" + scan.next()
  log.info(s"\nHashtag = $hashtag:\n")
  val listOfTweets = (new HashTag).showTweets(hashtag)

  listOfTweets onComplete {
    case Success(tweetList) =>
      tweetList.asScala.toList.map(tweet => log.info(s"\n$tweet \n"))

      //total tweet count
      val tweetCount = (new TweetCount).showTweetCount(tweetList)
      tweetCount onComplete {
        case Success(count) => log.info(s"\nTotal No. of Tweets of $hashtag = $count\n")
        case Failure(msg) => log.info(msg)
      }

      Thread.sleep(500)
    case Failure(msg) => log.info(msg)
  }

  val tweetsfor10Days = (new HashTag).getTenDaysTweets(hashtag)
  tweetsfor10Days onComplete {
    case Success(tweetList) =>

      //tweet count for 10 days
      val tweetCount10days = (new TweetCount).get10DaysTweetCount(tweetList)

      tweetCount10days onComplete {
        case Success(tweetCount10days) =>
          log.info(s"\n\nTotal no.of tweets of last 10 days: $tweetCount10days")

          //Average tweet count for 10 days
          val averageTweetCount10Days = (new AverageCount).showAverageTweet(tweetCount10days)
          averageTweetCount10Days onComplete {
            case Success(averageOf10Days) => log.info(s"\n\nAverage no. of Tweets of last 10 days: $averageOf10Days\n")
            case Failure(msg) => log.info(msg)
          }

          //Average likes for 10 days
          val averageLikes = (new AverageCount).showAverageLikeCount(tweetList)
          averageLikes onComplete{
            case Success(averageLikes) => log.info(s"\n\nAverage likes on last 10 days tweets for $hashtag = $averageLikes\n")
            case Failure(msg) => log.info(msg)
          }

          //Average retweet count
          val averageRetweet = (new AverageCount).showAverageRetweetCount(tweetList)
          averageRetweet onComplete{
            case Success(averageRetweet) => log.info(s"\n\nAverage Reweets on last 10 days tweets for $hashtag = $averageRetweet\n")
            case Failure(msg) => log.info(msg)
          }

        case Failure(msg) => log.info(msg)
      }

    case Failure(msg) => log.info(msg)
  }
  Thread.sleep(20000)
  // scalastyle:on

}
