package dev.kavrin.koverse.domain.util

sealed class ApiResponse<out SUCCESS, out ERROR> {
    /**
     * Represents successful network responses (2xx).
     */
    data class Success<SUCCESS>(val body: SUCCESS) : ApiResponse<SUCCESS, Nothing>()

    sealed class Error<ERROR> : ApiResponse<Nothing, ERROR>() {
        /**
         * Represents server (50x) and client (40x) errors.
         */
        data class HttpError<ERROR>(val code: Int, val errorBody: ERROR?) : Error<ERROR>()

        /**
         * Represent IOExceptions and connectivity issues.
         */
        data object NetworkError : Error<Nothing>()

        /**
         * Represent SerializationExceptions.
         */
        data object SerializationError : Error<Nothing>()
    }
}