package common.sphere

import java.util
import com.typesafe.config.{ConfigFactory, Config}
import io.sphere.sdk.client.{PlayJavaClientImpl, PlayJavaClient}
import io.sphere.sdk.http.ClientRequest
import play.api.Play
import play.libs.F.Promise

trait SphereClient {
  val client = createSphereClient()
  def createSphereClient(): PlayJavaClient


}

object MockSphereClient extends SphereClient {
  override def createSphereClient(): PlayJavaClient = new MockClient

  class MockClient extends PlayJavaClient {
    override def execute[T](p1: ClientRequest[T]): Promise[T] = ???

    override def close(): Unit = None
  }
}

object RemoteSphereClient extends SphereClient {
  override def createSphereClient(): PlayJavaClient = createRemoteClient()

  def createRemoteClient() = {
    val defaultValuesFromClasspath: Config = ConfigFactory.load
    val values: util.HashMap[String, Object] = new util.HashMap
    values.put("sphere.project", Play.current.configuration.getString("sphere.project").getOrElse(""))
    values.put("sphere.clientId", Play.current.configuration.getString("sphere.clientId").getOrElse(""))
    values.put("sphere.clientSecret", Play.current.configuration.getString("sphere.clientSecret").getOrElse(""))
    val config: Config = ConfigFactory.parseMap(values).withFallback(defaultValuesFromClasspath)

    new PlayJavaClientImpl(config)
  }
}
