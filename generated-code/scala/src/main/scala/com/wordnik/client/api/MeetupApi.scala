package com.wordnik.client.api

import com.wordnik.client.model.Meetup
import com.wordnik.client.common.ApiInvoker
import com.wordnik.client.common.ApiException
import scala.collection.mutable.HashMap

class MeetupApi {
  var basePath: String = "http://localhost:8000/api"
  var apiInvoker = ApiInvoker
  
  def addHeader(key: String, value: String) = apiInvoker.defaultHeaders += key -> value 

  def addMeetup (body: Meetup) = {
    // create path and map variables
    val path = "/meetup".replaceAll("\\{format\\}","json")// query params
    val queryParams = new HashMap[String, String]
    val headerParams = new HashMap[String, String]

    // verify required params are set
    (Set(body) - null).size match {
       case 1 => // all required values set
       case _ => throw new Exception("missing required params")
    }
    try {
      apiInvoker.invokeApi(basePath, path, "POST", queryParams.toMap, body, headerParams.toMap) match {
        case s: String =>
          case _ => None
      }
    } catch {
      case ex: ApiException if ex.code == 404 => None
      case ex: ApiException => throw ex
    }
  }
  def findMeetups (title: String, tag: String, active: Boolean) : Option[List[Meetup]]= {
    // create path and map variables
    val path = "/meetup".replaceAll("\\{format\\}","json")// query params
    val queryParams = new HashMap[String, String]
    val headerParams = new HashMap[String, String]

    if(String.valueOf(title) != "null") queryParams += "title" -> title.toString
    if(String.valueOf(tag) != "null") queryParams += "tag" -> tag.toString
    if(String.valueOf(active) != "null") queryParams += "active" -> active.toString
    try {
      apiInvoker.invokeApi(basePath, path, "GET", queryParams.toMap, None, headerParams.toMap) match {
        case s: String =>
          Some(ApiInvoker.deserialize(s, "List", classOf[Meetup]).asInstanceOf[List[Meetup]])
        case _ => None
      }
    } catch {
      case ex: ApiException if ex.code == 404 => None
      case ex: ApiException => throw ex
    }
  }
  }

