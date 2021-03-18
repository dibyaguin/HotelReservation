import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return true if the booking is available"

    request {
        method GET()
        url("/reservation/getAvailableReservation") {
            queryParameters {
                parameter("roomId", "16")
                parameter("fromDate", "2021-04-12")
                parameter("toDate", "2021-04-15")

            }
        }
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
            true
        )
    }
}