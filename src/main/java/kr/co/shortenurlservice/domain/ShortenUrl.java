package kr.co.shortenurlservice.domain;

import java.util.Random;

public class ShortenUrl {
	private String originalUrl;
	private String shortenUrlKey;
	private Long redirectCount;

	public ShortenUrl(String originalUrl, String shortenUrlKey) {
		this.originalUrl = originalUrl;
		this.shortenUrlKey = shortenUrlKey;
		this.redirectCount = 0L;
	}

	public static String generateShortenUrlKey() {
		String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
		Random random = new Random();
		StringBuilder shortenUrlKey = new StringBuilder();

		for (int count = 0; count < 8; count++) {
			int base56CharacterIndex = random.nextInt(0, base56Characters.length());
			char base56Character = base56Characters.charAt(base56CharacterIndex);
			shortenUrlKey.append(base56Character);
		}
		return shortenUrlKey.toString();
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public String getShortenUrlKey() {
		return shortenUrlKey;
	}

	public Long getRedirectCount() {
		return redirectCount;
	}

	public void increaseRedirectCount() {
		redirectCount++;
	}
}
