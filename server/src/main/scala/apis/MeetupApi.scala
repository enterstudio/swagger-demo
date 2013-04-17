package apis

import models.Meetup
import services._
import com.wordnik.swagger.core.ApiPropertiesReader

import org.scalatra.{ TypedParamSupport, ScalatraServlet }
import org.scalatra.swagger._
import org.json4s._
import org.json4s.JsonDSL._
import org.scalatra.json.{ JValueResult, JacksonJsonSupport }

import scala.collection.JavaConverters._

class MeetupApi (implicit val swagger: Swagger) extends ScalatraServlet 
    with JacksonJsonSupport
    with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "MeetupApi"
  override protected val applicationName: Option[String] = Some("meetup")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }


  val addMeetupOperation = (apiOperation[Unit]("addMeetup")
      summary "creates a meetup at a BASE event"
      parameters(
        bodyParam[Meetup]("body").description(""))
  )
  

  post("/",operation(addMeetupOperation)) {
        
    val body = parsedBody.extract[Meetup]
    MeetupApiService.addMeetup(body)
  }



  val findMeetupsOperation = (apiOperation[List[Meetup]]("findMeetups")
      summary "searches meetups"
      parameters(
        queryParam[String]("title").description("").optional,queryParam[String]("tag").description("").optional,queryParam[Boolean]("active").description("").optional)
  )
  

  get("/",operation(findMeetupsOperation)) {
        
    val title = params.getAs[String]("title")
    val tag = params.getAs[String]("tag")
    val active = params.getAs[Boolean]("active")
    MeetupApiService.findMeetups(title, tag, active)
  }

}
