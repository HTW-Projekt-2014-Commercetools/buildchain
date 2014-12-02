package common.elasticsearch

import common.domain.{TypeName, IndexName}
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.action.{ActionListener, ListenableActionFuture}
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.node.NodeBuilder
import org.elasticsearch.node.Node
import play.api.libs.json.JsValue

import scala.concurrent.{Promise, Future}

sealed trait ElasticsearchClient {
  implicit def convertListenableActionFutureToScalaFuture[T](x: ListenableActionFuture[T]): Future[T] = {
    val p = Promise[T]()
    x.addListener(new ActionListener[T] {
      def onFailure(e: Throwable) = p.failure(e)
      def onResponse(response: T) = p.success(response)
    })
    p.future
  }

  lazy val client = createElasticsearchClient()
  def close(): Unit
  def createElasticsearchClient(): Client

  def indexDocument(esIndex: IndexName, esType: TypeName, doc: JsValue): Future[IndexResponse] =
    client.prepareIndex(esIndex.value, esType.value).setSource(doc.toString()).execute()
  def search(esIndex: IndexName, esType: TypeName, query: QueryBuilder): Future[SearchResponse] =
    client.prepareSearch(esIndex.value).setTypes(esType.value).setQuery(query).execute()
}


object LocalEsClient extends ElasticsearchClient {
  lazy val node: Node = NodeBuilder.nodeBuilder().node()
  override def createElasticsearchClient(): Client = node.client()
  override def close() = node.close()
}


object RemoteEsClient extends ElasticsearchClient {
  // TODO implement nodeclient that connects to external cluster when we have one
  lazy val node: Node = NodeBuilder.nodeBuilder().node()
  override def createElasticsearchClient(): Client = NodeBuilder.nodeBuilder().node().client()
  override def close() = node.close()
}

