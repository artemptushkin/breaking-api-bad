# Breaking the API bad

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
pact-broker can-i-deploy --pacticipant=jesse-pinkman --version=jesse-season1 --to=main --broker-base-url=https://saul-goodman.pactflow.io/ -k=$SAUL_GOODMAN_BROKER_TOKEN
```