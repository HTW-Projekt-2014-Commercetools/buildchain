package common.sphere

import com.typesafe.config.{ConfigFactory, Config}
import io.sphere.sdk.client.{ScalaClientImpl, ScalaClient, PlayJavaClientImpl, PlayJavaClient}
import io.sphere.sdk.http.ClientRequest
import io.sphere.sdk.products.queries.ProductQuery
import play.api.Play
import play.libs.F.Promise

trait SphereClient {
  val client = createSphereClient()
  def createSphereClient(): PlayJavaClient

  def getProducts(page: Int, size: Int) = client.execute(new ProductQuery().withLimit(size).withOffset(page * size))
}

object MockSphereClient extends SphereClient {
  override def createSphereClient(): PlayJavaClient = new MockClient

  class MockClient extends PlayJavaClient {
    override def execute[T](p1: ClientRequest[T]): Promise[T] = ???

    override def close(): Unit = None
  }
}

object DevSphereClient extends SphereClient {
  override def createSphereClient(): PlayJavaClient = new PlayJavaClientImpl(Play.current.configuration.underlying)
}

object ProdSphereClient extends SphereClient {
  override def createSphereClient(): PlayJavaClient = {
    val config: Config = getConfigFromEnvVariables
    new PlayJavaClientImpl(config)
  }

  def getConfigFromEnvVariables: Config = ???
}