package edu.knoldus
import java.util.List
import scala.concurrent.ExecutionContext.Implicits.global
import twitter4j.Status
import scala.concurrent.Future
import scala.annotation.tailrec
import scala.collection.JavaConverters._

class TweetCount {

  def showTweetCount(tweets: List[Status]): Future[Int]= Future {
    tweets.size()
  }

  def get10DaysTweetCount(tweets: List[Status]): Future[Int] = Future{
    tweets.size()
  }

  def showRetweetCounts(tweets: List[Status]): Float = {

    @tailrec
    def showTotalRetweetCounts(tweets: scala.List[Status], retweetCount: Int): Int = {
      tweets match {
        case head :: tail => showTotalRetweetCounts(tail, retweetCount + head.getRetweetCount)
        case _ => retweetCount
      }
    }

    showTotalRetweetCounts(tweets.asScala.toList, 0).toFloat
  }

  def showLikeCounts(tweets: List[Status]): Float = {

    def showTotalLikeCounts(tweets: scala.List[Status], likeCount: Int): Int = {
      tweets match {
        case head :: tail => showTotalLikeCounts(tail, likeCount + head.getFavoriteCount)
        case _ => likeCount
      }
    }

    showTotalLikeCounts(tweets.asScala.toList, 0).toFloat
  }

}
