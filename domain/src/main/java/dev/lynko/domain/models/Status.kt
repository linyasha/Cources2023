package dev.lynko.domain.models

data class Status(
    val isSuccess: Boolean,
    val error: String?
) {

    companion object {
        const val STATUS_CANCELED = "Canceled"
    }
}
