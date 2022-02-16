package io.jmix.tests.ui.test.smoke.withoutdDBactions.rest

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.addonscreen.RESTScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.junit.Assert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class RESTSmokeUITest extends BaseUiTest implements UiHelper {

    public static final String REQUEST_URL = "http://localhost:8080/oauth/token?grant_type=password&username=admin&password=admin"
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization"
    public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type"
    public static final String AUTHORIZATION_HEADER_VALUE = "Basic Y2xpZW50OnNlY3JldA=="
    public static final String CONTENT_TYPE_HEADER_VALUE = "application/x-www-form-urlencoded"

    @Test
    @DisplayName("REST smoke test")
    void checkREST() {
        HttpUriRequest request = new HttpPost(REQUEST_URL)
        request.addHeader(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
        request.addHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
        HttpResponse response = HttpClientBuilder.create().build().execute(request)

        HttpEntity httpEntity = response.getEntity()
        Assert.assertNotNull(httpEntity)
        String result = EntityUtils.toString(httpEntity)

        int posBeg = result.indexOf(":\"") + 2
        int posEnd = result.indexOf("\",")

        String token = result.substring(posBeg, posEnd)

        $j(MainScreen).openRESTScreen()
        $j(RESTScreen).with {
            restField.setValue(token)
            Selenide.sleep(2000)
            restField.setValue("")
        }
    }
}
