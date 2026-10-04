package Learn.io.HelloWorld;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldApplicationTests {

	@Autowired
	private DataSource dataSource;

	@LocalServerPort
	private int port;

	@Test
	void connectsToConfiguredH2Database() throws Exception {
		try (var connection = dataSource.getConnection();
			 var statement = connection.createStatement();
			 var result = statement.executeQuery("SELECT 1")) {
			assertThat(connection.getMetaData().getURL()).isEqualTo("jdbc:h2:mem:tododb");
			assertThat(connection.getMetaData().getUserName()).isEqualToIgnoringCase("admin");
			assertThat(result.next()).isTrue();
			assertThat(result.getInt(1)).isEqualTo(1);
		}
	}

	@Test
	void servesH2ConsoleInBrowser() throws Exception {
		var client = HttpClient.newBuilder()
			.followRedirects(HttpClient.Redirect.NORMAL)
			.build();
		var request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/h2-console/"))
			.GET().build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());

		assertThat(response.statusCode()).isEqualTo(200);
		// H2 redirects to its login form using JavaScript rather than HTTP.
		var redirect = Pattern.compile("location.href = '([^']+)'").matcher(response.body());
		assertThat(redirect.find()).isTrue();
		var loginUri = request.uri().resolve(redirect.group(1));
		response = client.send(HttpRequest.newBuilder(loginUri).GET().build(),
			HttpResponse.BodyHandlers.ofString());
		assertThat(response.statusCode()).isEqualTo(200);
		assertThat(response.body()).contains("JDBC URL", "org.h2.Driver", "login.do");

		var loginRequest = HttpRequest.newBuilder(loginUri.resolve("login.do?" + loginUri.getQuery()))
			.header("Content-Type", "application/x-www-form-urlencoded")
			.POST(HttpRequest.BodyPublishers.ofString(
				"driver=org.h2.Driver&url=jdbc%3Ah2%3Amem%3Atododb&user=admin&password=1234"))
			.build();
		var loginResponse = client.send(loginRequest, HttpResponse.BodyHandlers.ofString());
		assertThat(loginResponse.statusCode()).isEqualTo(200);
		assertThat(loginResponse.body()).contains("frame", "query.jsp");
	}

}
