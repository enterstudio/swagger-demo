import com.wordnik.swagger.codegen.BasicScalaGenerator

import scala.collection.mutable.{ HashMap, ListBuffer }

object NodeServerGenerator extends BasicScalaGenerator {
  def main(args: Array[String]) = generateClient(args)

  override def templateDir = "src/main/templates/nodejs"

  val outputFolder = "nodejs"
    
  // where to write generated code
  override def destinationDir = outputFolder + "/App"

  // template used for apis
  apiTemplateFiles ++= Map("api.mustache" -> ".js")
  
  modelTemplateFiles.clear

  override def apiPackage = Some("apis")
  
  // supporting classes
  override def supportingFiles = List(
    ("package.json", outputFolder, "package.json"),
    ("README.mustache", outputFolder, "README.md"),
    ("main.mustache", destinationDir, "main.js"),
    ("models.mustache", destinationDir, "models.js"))
}
