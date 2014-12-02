package common.elasticsearch

import common.domain.{TypeName, IndexName}
import org.elasticsearch.client.Client
import org.elasticsearch.node.NodeBuilder
import org.elasticsearch.node.Node
import play.api.libs.json.JsValue

sealed trait ElasticsearchClient {
  lazy val client = createElasticsearchClient()
  def close(): Unit
  def createElasticsearchClient(): Client

  def prepareIndex(esIndex: IndexName, esType: TypeName, doc: JsValue) =
    client.prepareIndex(esIndex.value, esType.value).setSource(doc.toString())
  def prepareSearch(esIndex: IndexName, esType: TypeName) = client.prepareSearch(esIndex.value).setTypes(esType.value)
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

