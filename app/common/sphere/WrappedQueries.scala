package common.sphere

import io.sphere.sdk.products.Product
import io.sphere.sdk.products.queries.ProductQuery
import io.sphere.sdk.queries.QueryDsl

trait WrappedQueries {
  def getProductPage(page: Int, size: Int): QueryDsl[Product] =
    new ProductQuery()//.withLimit(size).withOffset(page * size)
}