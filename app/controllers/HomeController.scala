package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db._
import play.api.libs.json._
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

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def home() = Action { implicit request: Request[AnyContent] =>
    val connection = db.getConnection()
    val query = "SELECT * FROM user"
    val pstm = connection.createStatement()
    val rst = pstm.executeQuery(query)
    var allMembers = new ListBuffer[List[String]]()
    while(rst.next()){
      allMembers += List(rst.getString(1),rst.getInt(2).toString(),rst.getString(3))
    }
    connection.close()
    Ok(views.html.home(allMembers.reverse,List("","","")))
  }

  def save(fullname:String, index:String, reg:String) = Action { implicit request =>
    val connection = db.getConnection()
    val query ="INSERT INTO user(name, indexNo, regNo) VALUES(?,?,?)"
    val pstm = connection.prepareStatement(query)
    pstm.setString(1, fullname);
    pstm.setInt(2, Integer.parseInt(index))
    pstm.setString(3, reg);
    val rst = pstm.execute()
    connection.close()
    Redirect("/home")
  }

  def delete(index:String) = Action { implicit request =>
    val connection = db.getConnection()
    val query ="DELETE FROM user WHERE indexNo = ?"
    val pstm = connection.prepareStatement(query)
    pstm.setInt(1, Integer.parseInt(index));
    val rst = pstm.execute()
    connection.close()
    Redirect("/home")
  }

  def get(index:String) = Action { implicit request =>
    val connection = db.getConnection()
    val query = "SELECT * FROM user WHERE indexNo = ?"
    val pstm = connection.prepareStatement(query)
    pstm.setInt(1, Integer.parseInt(index));
    val rst = pstm.executeQuery()
    var searchmember = List("","","");
    while(rst.next()){
      searchmember = List(rst.getString(1),rst.getInt(2).toString(),rst.getString(3))
    }
    connection.close()
    Ok(views.html.home(new ListBuffer[List[String]](),searchmember))
  }

  def fill(index:String) = Action { implicit request =>
    val connection = db.getConnection()

    var query = "SELECT * FROM user WHERE indexNo = ?"
    var pstm = connection.prepareStatement(query)
    pstm.setInt(1, Integer.parseInt(index));
    var rst = pstm.executeQuery()
    var searchmember = List("","","");
    while(rst.next()){
      searchmember = List(rst.getString(1),rst.getInt(2).toString(),rst.getString(3))
    }

    query = "SELECT * FROM user"
    pstm = connection.prepareStatement(query)
    rst = pstm.executeQuery()
    var allMembers = new ListBuffer[List[String]]()
    while(rst.next()){
      allMembers += List(rst.getString(1),rst.getInt(2).toString(),rst.getString(3))
    }

    connection.close()

    Ok(views.html.home(allMembers, searchmember))
  }

}


