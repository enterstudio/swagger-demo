#### Swagger Demo

### Setup
* Get swagger-codegen and other dependencies via maven:

```
mvn package
```

Now you can start a webserver to host the api docs and the swagger-ui:

```
mvn jetty:run
```

This starts Jetty on port 8000.  You can now browse to [http://localhost:8000](http://localhost:8000) and see the swagger ui.  Of course you can't execute GET/POST methods since there's no server to handle them...

Now you can generate the objective-c client:

```
./bin/runscala.sh src/main/scripts/ObjectiveCClientGenerator.scala http://localhost:8000/api/resources.json
```

You will see the client written to the `client` folder.  You can modify the ObjectiveCClientGenerator script to change the behavior of the generator, or the templates, which live in the `src/main/templates/client` folder.

You can now generate the scalatra server:

```
./bin/runscala.sh src/main/scripts/ScalatraServerGenerator.scala http://localhost:8000/api/resources.json
```

This generates the server in the `server` folder.  You can start the server as follows:

```
cd server
sbt
container:start
```

The server is listening on port 8080, you can update the discovery URL in the swagger-ui (jetty is still running!) and execute commands against the server.

There is no logic in the server, so you can enable the mustache `partial` under `// do something magic!`:

```
{{> service_call}}
```

Now regenerate the server and you have real functionality:

You can finally generate a wordnik-api server:

```
./bin/runscala.sh src/main/scripts/WordnikServerGenerator.scala -DfileMap=src/main/templates/wordnik-api/ http://localhost:8080/api/resources.json
```