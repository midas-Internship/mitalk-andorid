package com.example.domain.exception

class BadRequestException(
    override val message: String?,
) : RuntimeException()

/**
 * 인증이 실패 할 경우 발생하는 RuntimeException
 * Http Status가 401일 경우 발생합니다.
 */
class UnAuthorizedException(
    override val message: String?,
) : RuntimeException()

/**
 * 권한이 없을 경우 발생하는 RuntimeException
 * Http Status가 403일 경우 발생합니다.
 */
class ForBiddenException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청받은 리소스를 찾을 수 없을 때 발생하는 RuntimeException
 * Http Status가 404일 경우 발생합니다.
 */
class NotFoundException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청이 지연될 경우 발생하는 RuntimeException
 * Http Status가 408일 경우 발생합니다.
 */
class TimeOutException(
    override val message: String?,
) : RuntimeException()

/**
 * 권한이 없을 경우 발생하는 RuntimeException
 * Http Status가 409일 경우 발생합니다.
 */
class ConflictException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청 횟수 제한이 초과될 경우 발생하는 RuntimeException
 * HttpStatus가 429일 경우 발생합니다.
 */
class TooManyRequestsException(
    override val message: String?,
) : RuntimeException()
/**
 * 서버에서 에러가 발생 할 경우 발생하는 RuntimeException
 * Http Status가 50X일 경우 발생합니다
 */
class ServerException(
    override val message: String?,
) : RuntimeException()

/**
 * 예상치 못한 HttpException 발생할 경우의 RuntimeException
 */
class OtherHttpException(
    val code: Int,
    override val message: String?,
) : RuntimeException()

class UnknownException(
    override val message: String?,
) : RuntimeException()

class NoInternetException : RuntimeException() {
    override val message: String
        get() = "네트워크가 불안정합니다. 데이터나 와이파이 연결 상태를 확인해주세요."
}

/**
 * 인터넷이 없을 경우 발생하는 RuntimeException
 */
class NoConnectivityException : RuntimeException() {
    override val message: String
        get() = "네트워크가 불안정합니다. 데이터나 와이파이 연결 상태를 확인해주세요."
}

class NeedLoginException : RuntimeException() {
    override val message: String
        get() = "토큰이 만료되어 로그인이 필요합니다"
}

/**
 * RefreshToken 이 존재하지 않아 발생하는 Exception
 */
class RefreshTokenNotFound : RuntimeException() {
    override val message: String
        get() = "토큰이 존재하지 않습니다 로그인이 필요합니다."
}

