package provider

import common.sphere.SphereClient
import io.sphere.sdk.queries.PagedQueryResult
import play.libs.F
import play.libs.F.Function

import scala.concurrent.Promise

class PagingProvider(client: SphereClient) {
//  def getProducts(page: Int, size: Int) = {
//    val products = client.getProducts(page, size)
//
//    val p = Promise[PagedQueryResult[Product]]()
//
//    products.map(
//      new F.Function[PagedQueryResult[Product], Promise[PagedQueryResult[Product]]]{ prods => p.success(prods)}
//    ).recover(error => p.failure(new Exception("Unknown error")))
//
//    p.future
//  }
}
