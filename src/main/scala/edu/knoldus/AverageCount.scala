package edu.knoldus

import java.util.List

import edu.knoldus.InputValues._
import twitter4j.Status

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AverageCount {

  def showAverageTweet(totaltweets: Float): Future[Float] = Future{
    totaltweets / totaldays
  }

  def showAverageLikeCount(tweets: List[Status]): Future[Float] = Future{
    val totalLikes = (new TweetCount).showLikeCounts(tweets)
    totalLikes / totaldays
  }

  def showAverageRetweetCount(tweets: List[Status]): Future[Float] = Future{
    val totalRetweets = (new TweetCount).showRetweetCounts(tweets)
    totalRetweets / totaldays
  }

}
