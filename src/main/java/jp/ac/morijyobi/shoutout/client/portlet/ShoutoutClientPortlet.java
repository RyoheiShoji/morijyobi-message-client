package jp.ac.morijyobi.shoutout.client.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.rmi.AccessException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import jp.ac.morijyobi.shoutout.client.constants.ShoutoutClientPortletKeys;

/**
 * Shout Out Portlet
 * 
 * @author Yasuyuki Takeo
 */
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Shoutout Client Portlet",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=" + ShoutoutClientPortletKeys.ShoutoutClient,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class ShoutoutClientPortlet extends MVCPortlet {

    final static private String SERVICE_URL = "https://app-msgsvc.wedeploy.io/message/add";

    /**
     * Shout Out Action
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    public void createShoutOut(ActionRequest request, ActionResponse response) throws IOException {
        String username;
        String contents;

        // Fetch data
        username = ParamUtil.getString(request, "username", "");
        contents = ParamUtil.getString(request, "contents", "");

        // Error Check
        if (Validator.isNull(username) || Validator.isNull(contents)) {
            SessionErrors.add(request, InvalidParameterException.class);
            hideDefaultSuccessMessage(request);
            return;
        }

        String json = null;
        try {
            json = accessTarget(username, contents);
        } catch (Exception e) {
            if (e instanceof ClientProtocolException || e instanceof IOException || e instanceof AccessException) {
                e.printStackTrace();
                SessionErrors.add(request, e.getClass());
                hideDefaultSuccessMessage(request);
                return;
            }
        }

        if (Validator.isNull(json)) {
            hideDefaultSuccessMessage(request);
            return;
        }

        System.out.println(new JSONObject(json).toString());

    }

    /**
     * Access to the target URL
     * 
     * @param username
     * @param contents
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private String accessTarget(String username, String contents)
            throws ClientProtocolException, IOException, AccessException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(SERVICE_URL);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("contents", contents));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        // Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity == null || response.getStatusLine().getStatusCode() == 404) {
            throw new AccessException("Can't access to the end point <" + SERVICE_URL + ">");
        }

        return IOUtils.toString(entity.getContent(), Charset.defaultCharset());
    }

}