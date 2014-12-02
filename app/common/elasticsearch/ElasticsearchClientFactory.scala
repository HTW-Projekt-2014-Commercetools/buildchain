package common.elasticsearch

import play.api.Mode.Mode
import play.api.{Mode, Play}

object ElasticsearchClientFactory {
  lazy val instance = getInstance

  def apply(): ElasticsearchClient = instance

  def returnClientForMode(mode: Mode) = mode match {
    case Mode.Dev => LocalEsClient
    case Mode.Prod => LocalEsClient
    case Mode.Test => LocalEsClient
  }

  def getInstance = returnClientForMode(Play.current.mode)
}
