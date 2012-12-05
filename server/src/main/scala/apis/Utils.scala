package apis

import com.wordnik.util.perf.Profile

case class ApiResponse(msg: String, code: Int)

trait SwaggerDatatypeConversionSupport {
	def IntDataType(obj: String): Int = obj.toInt

	def IntDataType(obj: Option[String]): Option[Int] = obj match {
		case Some(i) => Some(i.toInt)
		case _ => None
	}

	def BooleanDataType(obj: String): Boolean = obj.toBoolean

	def BooleanDataType(obj: Option[String]): Option[Boolean] = obj match {
		case Some(i) => Some(i.toBoolean)
		case _ => None
	}

	def StringDataType(obj: String): String = obj

	def StringDataType(obj: Option[String]): Option[String] = obj
}
