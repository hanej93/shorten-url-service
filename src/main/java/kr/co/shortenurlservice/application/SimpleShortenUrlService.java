package kr.co.shortenurlservice.application;

import org.springframework.stereotype.Service;

import kr.co.shortenurlservice.domain.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateResponseDto;

@Service
public class SimpleShortenUrlService {

	private final ShortenUrlRepository shortenUrlRepository;

	public SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
		this.shortenUrlRepository = shortenUrlRepository;
	}

	public ShortenUrlCreateResponseDto generateShortenUrl(
		ShortenUrlCreateRequestDto requestDto
	) {
		return null;
	}
}
