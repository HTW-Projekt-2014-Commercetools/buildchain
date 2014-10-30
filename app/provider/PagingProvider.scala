package provider

import common.sphere.SphereClient
import io.sphere.sdk.queries.PagedQueryResult
import play.api.mvc.Result
import play.libs.F

class PagingProvider(client: SphereClient) {
  def getProducts(page: Int, size: Int) = {
    val products = client.getProducts(page, size)

  //  products.map(pagedProductResult: PagedQueryResult[Product] => pagedProductResult.getResults)
  }
}
