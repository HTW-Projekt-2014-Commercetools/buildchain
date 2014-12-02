package common.elasticsearch

import org.elasticsearch.client.Client
import org.elasticsearch.node.NodeBuilder
import org.elasticsearch.node.Node

sealed trait ElasticsearchClient {
  lazy val client = createElasticsearchClient()
  def close(): Unit
  def createElasticsearchClient(): Client
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

