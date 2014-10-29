import common.WireDependencies
import play.api.GlobalSettings
import com.softwaremill.macwire.{MacwireMacros, Macwire}

object Global extends GlobalSettings with Macwire {
  import MacwireMacros._
  lazy val wired = wiredInModule(new WireDependencies {})

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    wired.lookupSingleOrThrow(controllerClass)
  }
}
