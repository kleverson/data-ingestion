package remote

import data.TimeLogItem
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.JsonElement
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val trustAllCertificates = object : X509TrustManager {
    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}

val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
    engine {
        https {
            trustManager = trustAllCertificates
        }
    }
}

suspend fun sendToApi(endpointUrl: String,verbHttp: String = "POST", body: List<JsonElement>, retries: Int = 3) : HttpStatusCode{
    repeat(retries){attempt ->
        try{
            var response = when(verbHttp.uppercase()){
                "POST" ->  httpClient.post(endpointUrl) {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                }
                "PUT" -> httpClient.put(endpointUrl) {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                }
                "PATCH" -> httpClient.patch(endpointUrl) {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                }
                "GET" -> httpClient.get(endpointUrl) {
                    contentType(ContentType.Application.Json)
                }
                else -> throw IllegalArgumentException("HTTP verb $verbHttp not supported.")
            }

            return response.status
        }catch (e: Exception) {
            println("Request failed on attempt ${attempt + 1}: ${e.message}")
            if (attempt == retries - 1) throw e
        }
    }
    return HttpStatusCode.RequestTimeout
}
