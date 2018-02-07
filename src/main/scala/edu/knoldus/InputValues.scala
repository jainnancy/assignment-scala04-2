package edu.knoldus
import java.util.Scanner
import org.apache.log4j.Logger
import twitter4j.conf.ConfigurationBuilder

object InputValues {

  // scalastyle:off
  val log = Logger.getLogger(this.getClass)
  val scan = new Scanner(System.in)
  val maximumCount = 100
  val totaldays = 10
  val cb: ConfigurationBuilder = new ConfigurationBuilder()
  val cb2: ConfigurationBuilder = new ConfigurationBuilder()
  //scalastyle:on
}
