import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

contract {
    name = "get customer by id"
    description = "REST endpoint to retrieve specific customer by id"

    request {
        url = url("/customers/1")
        method = GET
    }

    response {
        status = OK
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        }
        body = body(
            mapOf(
                "id" to "1",
                "name" to "Chirs",
                "surname" to "Froome"
            )
        )
    }
}