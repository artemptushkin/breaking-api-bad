package io.github.artemptushkin.breakingbad.scc

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url = url("/heisenberg/v1/crystals") {
            queryParameters {
                parameter("amount", 2)
            }
        }
    }
    response {
        status 200
        body([
                blue: [
                        'amount': 20.0,
                        'id'    : 1
                ]
        ])
        headers {
            contentType('application/json')
        }
    }
}