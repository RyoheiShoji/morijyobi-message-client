package jp.ac.morijyobi.shoutout.client.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

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

	}
}