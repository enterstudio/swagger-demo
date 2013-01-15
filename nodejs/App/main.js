var express = require("express")
 , url = require("url")
 , swagger = require("swagger-node-express")
 , db = false

var app = express();
app.use(express.bodyParser());

swagger.setAppHandler(app);  

// resources for the demo
var meetupApi = require("./apis/MeetupApi.js");
swagger.addModels(models)
  .addPOST(meetupApi.addMeetup)
  .addGET(meetupApi.findMeetups)
  ;

// configures the app
swagger.configure("http://localhost:8002", "0.1");

//  start the server
app.listen(8002);

