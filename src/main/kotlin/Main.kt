import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val client = HttpClient.newBuilder().build();

    for (i in 1..1248) {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://asofterworld.com/index.php?id=$i")).build();

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        download(getFileName(response.body()))
    }
}

    fun getFileName(body: String) : String {
        val file: String
        val regex = """(?<=clean\/).*(?=.jpg)""".toRegex()
        return regex.find(body)?.value.orEmpty()
    }

    fun download(imageName: String) {
        val url: URL = URL("https://www.asofterworld.com/clean/$imageName.jpg")
        val directory: String = "/Users/chaymus.klang/asofterworld"
        url.openStream().use { Files.copy(it, Paths.get("$directory/$imageName.jpg")) }
    }