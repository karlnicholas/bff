package com.google.training.appdev;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Validates Firebase JWT tokens using the rules presented here at
 * <a href="https://firebase.google.com/docs/auth/admin/verify-id-tokens">https://firebase.google.com/docs/auth/admin/verify-id-tokens</a>.
 *
 * <p>This validator will check the following claims:
 *
 * <ul>
 *     <li>iat : Must be in the past</li>
 *     <li>aud : Must be the firebase project id</li>
 *     <li>auth_time : Must be in the past</li>
 *     <li>sub : Must not be empty</li>
 * </ul>
 *
 * @since 1.2.2
 */
@Component
public class FirebaseTokenValidator implements OAuth2TokenValidator<Jwt> {

  private final String projectId;

  private static final Duration DEFAULT_MAX_CLOCK_SKEW = Duration.of(60, ChronoUnit.SECONDS);

  private final Duration clockSkew;

  private final Clock clock = Clock.systemUTC();

  private static final String OAUTH2_ERROR_URI = "https://tools.ietf.org/html/rfc6750#section-3.1";
  public FirebaseTokenValidator() {
    this("merchloans", DEFAULT_MAX_CLOCK_SKEW);
  }

  public FirebaseTokenValidator(String projectId) {
    this(projectId, DEFAULT_MAX_CLOCK_SKEW);
  }

  public FirebaseTokenValidator(String projectId, Duration clockSkew) {
    Assert.notNull(projectId, "ProjectId can't be null");
    this.projectId = projectId;
    this.clockSkew = clockSkew;
  }

  @Override
  public OAuth2TokenValidatorResult validate(Jwt token) {
    List<OAuth2Error> errors = new LinkedList<>();
    validateAudience(errors, token);
    validateIssuedAt(errors, token);
    validateSubject(errors, token);
    validateAuthTime(errors, token);
    return OAuth2TokenValidatorResult.failure(errors);
  }

  private void validateIssuedAt(List<OAuth2Error> errors, Jwt token) {
    Instant issuedAt = token.getIssuedAt();
    if (issuedAt == null || Instant.now(this.clock).plus(clockSkew).isBefore(issuedAt)) {
      errors.add(
          new OAuth2Error(
              OAuth2ErrorCodes.INVALID_REQUEST,
              "iat claim header must be in the past",
              OAUTH2_ERROR_URI));
    }
  }

  private void validateSubject(List<OAuth2Error> errors, Jwt token) {
    String subject = token.getSubject();
    if (subject == null || subject.isEmpty()) {
      errors.add(
          new OAuth2Error(
              OAuth2ErrorCodes.INVALID_REQUEST, "sub claim can not be empty", OAUTH2_ERROR_URI));
    }
  }

  private void validateAuthTime(List<OAuth2Error> errors, Jwt token) {
    Instant authTime = token.getClaimAsInstant("auth_time");
    if (authTime == null || Instant.now(this.clock).plus(clockSkew).isBefore(authTime)) {
      errors.add(
          new OAuth2Error(
              OAuth2ErrorCodes.INVALID_REQUEST,
              "auth_time claim header must be in the past",
              OAUTH2_ERROR_URI));
    }
  }

  private void validateAudience(List<OAuth2Error> errors, Jwt token) {
    List<String> audiences = token.getAudience();
    if (audiences != null) {
      for (String audience : audiences) {
        if (audience.equals(projectId)) {
          return;
        }
      }
    }
    errors.add(
        new OAuth2Error(
            OAuth2ErrorCodes.INVALID_REQUEST,
            "This aud claim is not equal to the configured audience",
            OAUTH2_ERROR_URI));
  }
}
