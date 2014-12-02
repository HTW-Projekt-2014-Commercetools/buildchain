package common.elasticsearch

import play.api.Mode.Mode
import play.api.{Mode, Play}
import org.elasticsearch.client.Client
import org.elasticsearch.node.NodeBuilder

object ElasticsearchClientFactory {
  lazy val instance = getInstance

  def apply(): Client = instance

  def returnClientForMode(mode: Mode) = mode match {
    case Mode.Dev => NodeBuilder.nodeBuilder().node().client()
    case Mode.Prod => NodeBuilder.nodeBuilder().node().client()
    case Mode.Test => NodeBuilder.nodeBuilder().node().client()
  }

  def getInstance = returnClientForMode(Play.current.mode)
}
