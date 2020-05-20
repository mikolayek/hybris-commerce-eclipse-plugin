#  Development

## Continuous Integration

folder contains docker configuration for sonarqube installation.
To execute sonar check it is required to run docker installation first

> docker-compose

 or by running shell script in this directory

> ./start-sonar.sh

After running that website will be available on default sonar port [localhost:9000](http://localhost:9000)

## Running Quality Check

To check plugin by sonar, run maven task:

> mvn clean verify sonar:sonar

Results will be available on your local website.


Releasing New Plugin Version
-------------------------------

New versions should be released by using Maven Tycho standard functionality.
When e.g. development version is `1.0.4-SNAPSHOT`, plugin should be tested. When all tests finished, plugin version should be set to release one by command:

`mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=1.0.4.RELEASE`

target plugin version should be released by command execution:

`mvn clean install`

Outcome should be copied to folder:

`cp -r com.hybris.hyeclipse.site/target/repository updatesite/neon`

After plugin build, version should be incremented by command:

`mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=1.0.5-SNAPSHOT`
