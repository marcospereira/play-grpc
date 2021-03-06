/**
 * Copyright (C) 2009-2019 Lightbend Inc. <https://www.lightbend.com>
 */

// #using-client
package controllers

import example.myapp.helloworld.grpc.helloworld.{ GreeterServiceClient, HelloRequest }
import javax.inject.{ Inject, Singleton }
import play.api.mvc.{ AbstractController, ControllerComponents }

import scala.concurrent.ExecutionContext

@Singleton
class MyController @Inject() (implicit greeterClient: GreeterServiceClient, cc: ControllerComponents, exec: ExecutionContext) extends AbstractController(cc) {

  def sayHello(name: String) = Action.async {
    greeterClient.sayHello(HelloRequest(name))
      .map { reply =>
        Ok(s"response: ${reply.message}")
      }
  }

}
// #using-client
