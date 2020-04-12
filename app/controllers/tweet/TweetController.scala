package controllers.tweet

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import models.Tweet

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  
  val tweets: Seq[Tweet] = (1L to 10L).map(i => Tweet(Some(i), s"test tweet${i.toString}"))

  def list() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tweet.list(tweets))
  }

  def show(id: Long) = Action { implicit request: Request[AnyContent] =>
    val tweet = tweets.find(_.id.get == id) match {
      case Some(t) => t
    }
    Ok(views.html.tweet.show(tweet))
  }
}
