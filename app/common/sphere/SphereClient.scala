package common.sphere

import io.sphere.sdk.client.{SphereRequestExecutorTestDouble, ScalaClientImpl, ScalaClient}
import io.sphere.sdk.http.{ClientRequest}
import play.api.Play

trait SphereClient extends WrappedQueries {
  val client = createSphereClient()
  def createSphereClient(): ScalaClient

  def execute[T](req: ClientRequest[T]) = client.execute(req)
}

object RemoteSphereClient extends SphereClient {
  override def createSphereClient(): ScalaClient = new ScalaClientImpl(Play.current.configuration.underlying)
}
