package jp.ac.morijyobi.shoutout.client.test

import static com.github.tomakehurst.wiremock.client.WireMock.*
import jp.ac.morijyobi.shoutout.client.portlet.ShoutoutClientPortlet

import com.github.tomakehurst.wiremock.junit.WireMockRule

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.junit.runner.Result

import spock.lang.Specification
import spock.lang.Unroll

class ShoutoutClientPortletTest extends Specification {
	@Rule
	public TemporaryFolder temp = new TemporaryFolder()

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8080); // No-args constructor defaults to port 8080

	@Unroll("Smoke test")
	def "Smoke test"() {
		when:
		stubFor(get(urlEqualTo("/my/resource"))
				.withHeader("Accept", equalTo("text/xml"))
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader("Content-Type", "text/xml")
				.withBody("<response>Some content</response>")));

		ShoutoutClientPortlet scp = new ShoutoutClientPortlet();
		
		def ret = scp.accessTarget("aa","bb");
		
		then:
		ret == 1
	}
}
