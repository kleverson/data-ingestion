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

suspend fun sendToApi(endpointUrl: String, body: List<JsonElement>) : HttpStatusCode{
    val response = httpClient.post(endpointUrl) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }

    return response.status
}
