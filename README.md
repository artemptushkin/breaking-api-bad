# Breaking the API bad

How to mantain a contract between your microservices.
There is a few approaches to do so:
- write and share client library for your API
- contract tests
- specification tighten to code
  - code-first
  - spec-first

In this repo we have prepared examples of each approach.

---
## Client Library
Explore the code of 3 modules:
- [Datamodel](./client-lib/datamodel) - common data types for server and client
- [Server](./client-lib/server) - server application
- [Client](./client-lib/client) - client library for distribution between your clients


---
## Contract testing with Pact

### Consumer

```shell
./gradlew :contract-testing:pact:jesse-pinkman-pact-consumer:contractTest

./gradlew :contract-testing:pact:jesse-pinkman-pact-consumer:pactPublish -Ppactbroker.auth.token=$SAUL_GOODMAN_BROKER_TOKEN -Ppact.publish.tags=main -Ppact.publish.consumerVersion=jesse-season1
```

### Provider

```shell
./gradlew contractTest :contract-testing:pact:heisenberg-pact-service:contractTest \
  -Ppactbroker.auth.token=$SAUL_GOODMAN_BROKER_TOKEN \
  -Ppactbroker.consumerversionselectors.tags=main \
  -Ppact.verifier.publishResults=true \
  -Ppact.provider.tag=main \
  -Ppact.provider.version=heisenberg-season1 -i -s
```

### Can-i-deploy

```shell
pact-broker can-i-deploy --pacticipant=jesse-pinkman --version=jesse-season1 --to=prod --broker-base-url=https://saul-goodman.pactflow.io/ -k=$SAUL_GOODMAN_BROKER_TOKEN
```


---
## Openapi

### Code-First

Start application with:
```shell
./gradlew :openapi:code-first:bootRun
```

Open generated swagger-ui in browser by this [link](http://localhost:8080/swagger-ui.html).

Download generated OpenAPI specification with the [link](http://localhost:8080/v3/api-docs.yaml).
Also, OpenAPI specification is generating in a build-time while tests are running.
You can find `openapi.yaml` file in a [build dir](./openapi/code-first/build) after a test run. 
