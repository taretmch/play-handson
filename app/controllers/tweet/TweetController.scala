package controllers.tweet

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import models.Tweet

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def list() = Action { implicit request: Request[AnyContent] =>
    val tweets: Seq[Tweet] = (1L to 10L).map(i => Tweet(Some(i), s"test tweet${i.toString}"))
    Ok(views.html.tweet.list(tweets))
  }
}
