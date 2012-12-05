package apis

import models.Meetup
import services._
import com.wordnik.util.perf._
import com.wordnik.swagger.core.ApiPropertiesReader

import org.scalatra.{ TypedParamSupport, ScalatraServlet }
import org.scalatra.swagger._
import org.json4s._
import org.json4s.JsonDSL._
import org.scalatra.json.{JValueResult, NativeJsonSupport}

import scala.collection.JavaConverters._

class MeetupApi (implicit val swagger: Swagger) extends ScalatraServlet 
    with TypedParamSupport 
    with NativeJsonSupport 
    with JValueResult 
    with SwaggerSupport
    with SwaggerDatatypeConversionSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "MeetupApi"
  override protected val applicationName: Option[String] = Some("meetup")

  def swaggerToModel(cls: Class[_]) = {
    val docObj = ApiPropertiesReader.read(cls)
    val name = docObj.getName
    val fields = for (field <- docObj.getFields.asScala.filter(d => d.paramType != null))
      yield (field.name -> ModelField(field.name, field.notes, DataType(field.paramType)))

    Model(name, name, fields.toMap)
  }

  models = Map(swaggerToModel(classOf[Meetup]))
  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  delete("/:name",
    summary("removes a meetup"),
    nickname("removeMeetup"),
    responseClass("void"),
    endpoint("{name}"),
    notes("only if you really want to!"),
    parameters(
      Parameter(name = "name", 
        description = "the meetup to remove",
        dataType = DataType.String,
        defaultValue = None,
        paramType = ParamType.Path)
      ))
  {
    // do something magic!
    val name = StringDataType(params.contains("name") match {
      case true  => params("name")
      case false => halt(400)
    })
    Profile("meetup/:name (delete)", MeetupApiService.removeMeetup(name), true)
  }

  post("/",
    summary("creates a meetup"),
    nickname("addMeetup"),
    responseClass("void"),
    endpoint(""),
    notes("you need to be authenticated to do this!"),
    parameters(
      Parameter(name = "body",
        description = "the meetup to add",
        dataType = DataType("Meetup"),
        defaultValue = None,
        paramType = ParamType.Body)
      ))
  {
    // do something magic!
    val body = ({
      val str = parsedBody match {
        case e: JValue => e
        case _ => JNothing}

      str.extract[Meetup] match {
        case e: Meetup => e
        case _ => halt(400)}
    })
    Profile("meetup/ (post)", MeetupApiService.addMeetup(body), true)
  }

  get("/",
    summary("searches meetups"),
    nickname("findMeetups"),
    responseClass("List[Meetup]"),
    endpoint(""),
    notes("you will find great meetups here"),
    parameters(
      Parameter(name = "title", 
        description = "title of meetup to search for",
        paramType = ParamType.Query,
        required = false,
        allowMultiple = false,
        defaultValue = None,
        dataType = DataType("String"))
      ,Parameter(name = "tag", 
        description = "tag of meetup to search for",
        paramType = ParamType.Query,
        required = false,
        allowMultiple = true,
        defaultValue = None,
        dataType = DataType("String"))
      ,Parameter(name = "active", 
        description = "searches for active meetups only",
        paramType = ParamType.Query,
        required = false,
        allowMultiple = false,
        allowableValues = AllowableValues(true,false),defaultValue = None,
        dataType = DataType("Boolean"))
      ))
  {
    // do something magic!
    val title = StringDataType(params.contains("title") match {
      case true  => Some(params("title"))
      case false => None
    })
    val tag = StringDataType(params.contains("tag") match {
      case true  => Some(params("tag"))
      case false => None
    })
    val active = BooleanDataType(params.contains("active") match {
      case true  => Some(params("active"))
      case false => None
    })
    Profile("meetup/ (get)", MeetupApiService.findMeetups(title, tag, active), true)
  }
}
