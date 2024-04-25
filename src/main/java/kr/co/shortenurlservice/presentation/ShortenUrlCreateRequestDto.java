package kr.co.shortenurlservice.presentation;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;

public class ShortenUrlCreateRequestDto {

	@NotNull
	@URL(regexp = "(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
	private String originalUrl;

	public String getOriginalUrl() {
		return originalUrl;
	}
}
