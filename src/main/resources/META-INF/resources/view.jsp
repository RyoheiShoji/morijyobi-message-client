<%@ include file="/init.jsp"%>

<portlet:actionURL name="createShoutOut" var="createShoutOutURL" />

<div class="container-fluid-1280"
	id="<portlet:namespace />formContainer">
	<liferay-ui:error exception="<%=InvalidParameterException.class%>"
		message="Username and Contents are reqired." />
	<liferay-ui:error exception="<%=AccessException.class%>"
		message="Endpoint has not found" />
	<aui:form action="<%=createShoutOutURL.toString()%>" method="post" name="fm">

		<aui:input name="username" type="text" value=""
			placeholder="Type your username here">
			<aui:validator name="required" />
		</aui:input>
		<aui:input name="contents" type="text" value=""
			placeholder="Type message here">
			<aui:validator name="required" />
		</aui:input>

		<aui:button-row cssClass="button-row">
			<aui:button cssClass="btn-lg" type="submit" primary="<%=false%>"
				value="Shout Out!" />
		</aui:button-row>
	</aui:form>
</div>