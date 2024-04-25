package kr.co.shortenurlservice.application;

import org.springframework.stereotype.Service;

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
		String shortenUrlKey = ShortenUrl.generateShortenUrlKey();

		ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
		shortenUrlRepository.saveShortenUrl(shortenUrl);

		return new ShortenUrlCreateResponseDto(shortenUrl);
	}

	public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
		ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
		return new ShortenUrlInformationDto(shortenUrl);
	}
}
