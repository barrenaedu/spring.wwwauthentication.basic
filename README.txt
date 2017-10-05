mvn archetype:generate -DgroupId=spring.wwwauthentication -DartifactId=spring.wwwauthentication.basic -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

Readme
------
https://en.wikipedia.org/wiki/Basic_access_authentication

Application
-----------
https://localhost:8080/rest/application.wadl

1) Test on browser enabling insecure connections on localhost
2) Use parameter -k to turn off curl certificate verification
   $ curl -i -k -H "Accept: application/json" -H "Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==" https://localhost:8080/rest/messages

SSL & Certificate generation
----------------------------
1) Create Java Key Store with certificate
    $ keytool -genkeypair -alias localhost -keyalg RSA -keysize 2048 -keystore keystore.jks -storepass 123456 -validity 3650
2) Show details of the given keystore
    $ keytool -v -list -keystore keystore.jks

