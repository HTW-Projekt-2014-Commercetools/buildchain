package common.sphere

import io.sphere.sdk.client.{ScalaClientImpl, ScalaClient}
import io.sphere.sdk.http.{ClientRequest}
import play.api.Play

trait SphereClient extends WrappedQueries {
  val client = createSphereClient()
  def createSphereClient(): ScalaClient

  def execute[T](req: ClientRequest[T]) = client.execute(req)
}

object MockSphereClient extends SphereClient {
  override def createSphereClient(): ScalaClient = ??? //new ScalaClientImpl(Play.current.configuration.underlying, new SphereRequestExecutorTestDouble {
//
//    override def result[T](requestable: ClientRequest[T]) {
//      requestable.httpRequest().getPath match {
//        case products if products.contains("/product") {
//        }
//      }
//    }
//  } ss
}

object RemoteSphereClient extends SphereClient {
  override def createSphereClient(): ScalaClient = new ScalaClientImpl(Play.current.configuration.underlying)
}
