package contracts.rest

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    name "get all customers"
    description "REST endpoint to retrieve all customers"

    request {
        url "/customers"
        method GET()
    }

    response {
        status OK()
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        }
        body([customers: [[id: "1", name: "Chris", surname: "Froome"],
                          [id: "1", name: "Tadej", surname: "Pogacar"],
                          [id: "1", name: "Jonas", surname: "Vingegaard"]]])
    }
}