package com.semicolon.data.util

import com.semicolon.domain.exception.basic.*
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HttpHandler<T> {

    private lateinit var httpRequest: suspend () -> T
    private var onBadRequest: (message: String) -> Throwable = { BadRequestException() }
    private var onUnauthorized: (message: String) -> Throwable = { UnauthorizedException() }
    private var onForbidden: (message: String) -> Throwable = { ForbiddenException() }
    private var onNotFound: (message: String) -> Throwable = { NotFoundException() }
    private var onConflict: (message: String) -> Throwable = { ConflictException() }
    private var onServerError: (code: Int) -> Throwable = { ServerException() }
    private var onOtherHttpStatusCode: (code: Int, message: String) -> Throwable = { _, _ -> UnknownException() }

    fun httpRequest(httpRequest: suspend () -> T): HttpHandler<T> =
        this.apply { this.httpRequest = httpRequest }

    suspend fun sendRequest(): T =
        try {
            httpRequest()
        } catch (e: HttpException) {
            val code = e.code()
            val message = e.message()
            throw when (code) {
                400 -> onBadRequest(message)
                401 -> onUnauthorized(message)
                403 -> onForbidden(message)
                404 -> onNotFound(message)
                409 -> onConflict(message)
                500, 501, 502, 503 -> onServerError(code)
                else -> onOtherHttpStatusCode(code, message)
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException()
        } catch (e: SocketTimeoutException) {
            throw TimeoutException()
        } catch (e: Throwable) {
            throw UnknownException()
        }
}