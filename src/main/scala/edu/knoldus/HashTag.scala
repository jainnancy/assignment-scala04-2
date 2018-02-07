package edu.knoldus

import java.util._
import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.ConfigFactory
import edu.knoldus.InputValues._
import twitter4j.{Query, Status, TwitterFactory}
import scala.concurrent.Future

class HashTag {
  val consumerKey = ConfigFactory.load().getString("twitter.consumer.key")
  val consumerSecret = ConfigFactory.load().getString("twitter.consumer.secret")
  val accessToken = ConfigFactory.load().getString("twitter.access.token")
  val accessTokenSecret = ConfigFactory.load().getString("twitter.access.tokenSecret")

  def showTweets(hashtag: String): Future[List[Status]] = Future{
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)

    val twitter = (new TwitterFactory(cb.build)).getInstance()
    val query: Query = new Query(hashtag)
    query.setCount(maximumCount)
    val result = twitter.search(query)
    val tweets = result.getTweets
    tweets
  }

  def getTenDaysTweets(hashtag: String): Future[List[Status]] = Future{
    cb2.setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)

    val twitter = (new TwitterFactory(cb2.build)).getInstance()
    val query: Query = new Query(hashtag)
    query.setCount(maximumCount)
    query.setSince(java.time.LocalDate.now.minusDays(totaldays).toString)
    query.setUntil(java.time.LocalDate.now.toString)
    val result = twitter.search(query)
    val tweets = result.getTweets
    tweets
  }

}
