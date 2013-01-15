import com.wordnik.swagger.codegen.BasicObjcGenerator

object ObjcClientGenerator extends BasicObjcGenerator {
	def main(args: Array[String]) = generateClient(args)

	override def templateDir = "src/main/templates/objc-client"
  override def destinationDir = "client"

  // supporting classes
  override def supportingFiles =
    List(
      ("NIKSwaggerObject.h", destinationDir, "NIKSwaggerObject.h"),
      ("NIKSwaggerObject.m", destinationDir, "NIKSwaggerObject.m"),
      ("NIKApiInvoker.h", destinationDir, "NIKApiInvoker.h"),
      ("NIKApiInvoker.m", destinationDir, "NIKApiInvoker.m"),
      ("NIKDate.h", destinationDir, "NIKDate.h"),
      ("NIKDate.m", destinationDir, "NIKDate.m"))
}