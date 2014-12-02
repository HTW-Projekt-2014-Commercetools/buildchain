package common.elasticsearch
import org.elasticsearch.action.{ActionListener, ActionRequestBuilder, ActionResponse}
import scala.concurrent.{Future, Promise}

class ActionListenerAdapter[T <: ActionResponse] extends ActionListener[T] {
  val promise = Promise[T]()

  def onResponse(response: T) {
    promise.success(response)
  }

  def onFailure(e: Throwable) {
    promise.failure(e)
  }

  def execute[RB <: ActionRequestBuilder[_,T,_, _]](request: RB): Future[T] = {
    request.execute(this)
    promise.future
  }
}
