package kr.co.shortenurlservice.application;

import org.springframework.stereotype.Service;

import kr.co.shortenurlservice.domain.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.NotFoundShortenUrlException;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.domain.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateResponseDto;
import kr.co.shortenurlservice.presentation.ShortenUrlInformationDto;

@Service
public class SimpleShortenUrlService {

	private final ShortenUrlRepository shortenUrlRepository;

	public SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
		this.shortenUrlRepository = shortenUrlRepository;
	}

	public ShortenUrlCreateResponseDto generateShortenUrl(
		ShortenUrlCreateRequestDto requestDto
	) {
		String originalUrl = requestDto.getOriginalUrl();
		String shortenUrlKey = getUniqueShortenUrlKey();

		ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
		shortenUrlRepository.saveShortenUrl(shortenUrl);

		return new ShortenUrlCreateResponseDto(shortenUrl);
	}

	private String getUniqueShortenUrlKey() {
		final int MAX_RETRY_COUNT = 5;
		int count = 0;

		while (count++ < MAX_RETRY_COUNT) {
			String shortenUrlKey = ShortenUrl.generateShortenUrlKey();
			ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

			if (shortenUrl == null) {
				return shortenUrlKey;
			}
		}

		throw new LackOfShortenUrlKeyException();
	}

	public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
		ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

		if (shortenUrl == null) {
			throw new NotFoundShortenUrlException();
		}

		return new ShortenUrlInformationDto(shortenUrl);
	}

	public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
		ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

		if (shortenUrl == null) {
			throw new NotFoundShortenUrlException();
		}

		shortenUrl.increaseRedirectCount();
		shortenUrlRepository.saveShortenUrl(shortenUrl);

		return shortenUrl.getOriginalUrl();
	}
}
