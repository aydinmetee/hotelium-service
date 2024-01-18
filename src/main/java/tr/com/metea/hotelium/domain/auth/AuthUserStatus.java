package tr.com.metea.hotelium.domain.auth;

/**
 * @author meteaydin
 * @since 26.12.2023
 */
public enum AuthUserStatus {
	ACTIVE,
	PASSIVE,
	PASSWORD_EXPIRED,
	WAITING_OTP
}
