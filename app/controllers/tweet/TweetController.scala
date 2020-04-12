package controllers.tweet

import javax.inject.{Inject, Singleton}
import play.api.mvc._
import models.Tweet
import play.api.i18n.I18nSupport
import play.api.data._
import play.api.data.Forms._

case class TweetFormData(content: String)

@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with I18nSupport {
  
  val tweets = scala.collection.mutable.ArrayBuffer((1L to 10L).map(i => Tweet(Some(i), s"test tweet${i.toString}")): _*)

  val form: Form[TweetFormData] = Form(
    mapping(
      "content" -> nonEmptyText(maxLength = 140)
    )(TweetFormData.apply)(TweetFormData.unapply)
  )

  def list() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tweet.list(tweets.toSeq))
  }

  def show(id: Long) = Action { implicit request: Request[AnyContent] =>
    tweets.find(_.id.exists(_ == id)) match {
      case Some(tweet) => Ok(views.html.tweet.show(tweet))
      case None        => NotFound(views.html.error.page404())
    }
  }

  def register() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tweet.store(form))
  }

  def store() = Action { implicit request: Request[AnyContent] =>
    form.bindFromRequest().fold(
      (formWithErrors: Form[TweetFormData]) => {
        BadRequest(views.html.tweet.store(formWithErrors))
      },
      (tweetFormData: TweetFormData) => {
        tweets += Tweet(Some(tweets.size + 1L), tweetFormData.content)
        Redirect(routes.TweetController.list())
      }
    )
  }
}
