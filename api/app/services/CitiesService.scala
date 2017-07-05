package services

import javax.inject._
import javax.inject.{Inject, Singleton}
import play.api.libs.ws._
import scala.concurrent._
import scala.util.Properties

@Singleton
class CitiesService (ws: WSClient, baseUrl: String) {
  @Inject() def this(ws: WSClient) = this(ws, "https://maps.googleapis.com")
  val API_KEY = Properties.envOrElse("GOOGLE_API_KEY", "GOOGLE_API_KEY");

  def getCities(city: String): Future[WSResponse] = {
    ws.url(s"${baseUrl}/maps/api/place/autocomplete/json?input=${city}&types=geocode&key=${API_KEY}").get()
  }
}
