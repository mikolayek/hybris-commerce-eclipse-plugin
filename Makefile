.PHONY: build test package reuse wrapper

build:
	./mvnw clean install

test:
	./mvnw clean verify -pl com.hybris.hyeclipse.tests

package:
	./mvnw clean package -pl com.hybris.hyeclipse.site --also-make

reuse:
	pipx run reuse lint

wrapper:
	mvn -N wrapper:wrapper -Dmaven=3.9.15