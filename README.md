#### Swagger Demo

### Setup
* Install scala 2.9.1 and put `scala` in your $PATH!  Scala is here:

```
http://www.scala-lang.org/sites/default/files/linuxsoft_archives/downloads/distrib/files/scala-2.9.1-1.tgz
```

This may work with newer versions of scala but please use 2.9.1 for this demo.

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

#### You can now generate the scalatra server

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

#### You can add encapsulate a service class, to avoid the codegen from wiping out business logic

There is no logic in the server, so you can enable the mustache `partial` under `// do something magic!`:

```
{{> service_call}}
```

Now regenerate the server and you have real functionality:

```
./bin/runscala.sh src/main/scripts/ScalatraServerGenerator.scala http://localhost:8000/api/resources.json
```

#### You can generate a scala client

```
./bin/runscala.sh com.wordnik.swagger.codegen.BasicScalaGenerator http://localhost:8000/api/resources.json
```

Which is written to `generated-files/scala` per the default scala template from the codegen, [here](https://github.com/wordnik/swagger-codegen/blob/master/src/main/scala/com/wordnik/swagger/codegen/BasicScalaGenerator.scala).

#### You can generate a server for node.js

```
./bin/runscala.sh src/main/scripts/NodeServerGenerator.scala http://localhost:8000/api/resources.json
```

Which can be run as follows:

```
cd nodejs
npm install
node Apps/main.js
```

Which is now available via `http://localhost:8002/api-docs.json`

#### A real server example

You can finally generate a wordnik-api server (disable the partial from above, since I didn't write all the service methods):

```
./bin/runscala.sh src/main/scripts/WordnikServerGenerator.scala -DfileMap=src/main/templates/wordnik-api/ http://localhost:8080/api/resources.json
```