import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

contract {
    name = "get all customers"
    description = "REST endpoint to retrieve all customers"

    request {
        url = url("/customers")
        method = GET
    }

    response {
        status = OK
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        }
        body = body(
            listOf(
                mapOf(
                    "id" to "1",
                    "name" to "Chirs",
                    "surname" to "Froome"
                ),
                mapOf(
                    "id" to "2",
                    "name" to "Tadej",
                    "surname" to "Pogacar"
                ),
                mapOf(
                    "id" to "3",
                    "name" to "Jonas",
                    "surname" to "Vingegaard"
                )
            )
        )
    }
}