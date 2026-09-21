.PHONY: build test reuse wrapper

build:
	./mvnw clean install

test:
	./mvnw clean verify -pl com.hybris.hyeclipse.tests

reuse:
	pipx run reuse lint

wrapper:
	mvn -N wrapper:wrapper -Dmaven=3.9.15