package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db._
import scala.collection.mutable.ListBuffer
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database, val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  val connection = db.getConnection()
  val pstm = connection.createStatement()

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def home() = Action { implicit request: Request[AnyContent] =>
    val query = "SELECT * FROM user"
    val rst = pstm.executeQuery(query)
    var allMembers = new ListBuffer[List[String]]()
    while(rst.next()){
      allMembers += List(rst.getString(1),rst.getInt(2).toString(),rst.getString(3))
    }
    Ok(views.html.home(allMembers.reverse))
  }

  connection.close()
}
