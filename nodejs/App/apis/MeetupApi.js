var swagger = require("swagger-node-express");
var url = require("url");
var errors = swagger.errors;

/* add model includes */

function writeResponse (response, data) {
  response.header('Access-Control-Allow-Origin', "*");
  response.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
  response.header("Access-Control-Allow-Headers", "Content-Type");
  response.header("Content-Type", "application/json; charset=utf-8");
  response.send(JSON.stringify(data));
}

exports.models = models = require("../models.js");

exports.addMeetup = {
  'spec': {
    "description" : "Operations about pets",
    "path" : "/meetup.{format}",
    "notes" : "you need to be authenticated to do this!",
    "summary" : "creates a meetup at a BASE event",
    "method": "POST",
    "params" : [].concat([]).concat([]).concat([swagger.postParam("Meetup", "the meetup to add", true)
    ]),
    "responseClass" : "",
    "errorResponses" : [errors.invalid('id'), errors.notFound('')],
    "nickname" : "addMeetup"
  },
  'action': function (req,res) {
    if (!req.params.body) {
      throw errors.invalid('body');
    }
    writeResponse(res, {message: "how about implementing addMeetup as a POST method?"});    
  }
};
exports.findMeetups = {
  'spec': {
    "description" : "Operations about pets",
    "path" : "/meetup.{format}",
    "notes" : "you will find great meetups here",
    "summary" : "searches meetups",
    "method": "GET",
    "params" : [swagger.queryParam("title", "title of meetup to search for", "string", false, false, ""),swagger.queryParam("tag", "tag of meetup to search for", "string", false, true, ""),swagger.queryParam("active", "searches for active meetups only", "boolean", false, false, "LIST[true,false]")].concat([]).concat([]).concat([]),
    "responseClass" : "List[Meetup]",
    "errorResponses" : [errors.invalid('id'), errors.notFound('List[Meetup]')],
    "nickname" : "findMeetups"
  },
  'action': function (req,res) {
    writeResponse(res, {message: "how about implementing findMeetups as a GET method?"});    
  }
};

