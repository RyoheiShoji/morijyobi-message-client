# Message Client
This is for Morioka Business System trading school development session.

### Requirement
* Deploy this on Liferay DXP / 7.
* gradle 3.0 or above
* [blade tool](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli). Install blade according to the instruction of the link.
* Also need to set up [server](https://github.com/yasuflatland-lf/morijyobi-message-server) on [wedeploy](https://wedeploy.com/). Please see [server's README](https://github.com/yasuflatland-lf/morijyobi-message-server) for more details.

### How to deploy
1. Clone this repository with ```git clone https://github.com/yasuflatland-lf/morijyobi-message-client```
2. Start up Liferay bundle with  ```${tomcat_home}/bin/catalina.sh run```
3. After the Liferay bundle is up and running, at this repository's root, run ```blade deploy```, or ```gradle assemble``` and place the generated jar into ```${liferay_home}/deploy``` folder manually.
4. Deploy server according to the instruction on the [server](https://github.com/yasuflatland-lf/morijyobi-message-server) page, then open up the [server page](https://ui-msgsvc.wedeploy.io/)
5. Place shout out portlet on Liferay and type username and contents, then hit shout out button. You'll see your message will be displayed. 