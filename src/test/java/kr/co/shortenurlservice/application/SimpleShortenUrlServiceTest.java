package kr.co.shortenurlservice.application;


import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.shortenurlservice.domain.NotFoundShortenUrlException;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateResponseDto;

@SpringBootTest
class SimpleShortenUrlServiceTest {

	@Autowired
	private SimpleShortenUrlService simpleShortenUrlService;

	@Test
	@DisplayName("URL을 단축한 후 단축된 URL 키로 조회하면 원래 URL이 조회되어야 한다.")
	void shortenUrlAddTest() {
		// given
		String expectedOriginalUrl = "https://www.naver.com";
		ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(expectedOriginalUrl);

		// when
		ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
		String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();

		String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

		// then
		assertThat(originalUrl.equals(expectedOriginalUrl)).isTrue();
	}

	// 존재하지 않는 단축 URL을 조회하는 경우는 여러분들이 테스트 작성 해보기
	@Test
	@DisplayName("존재하지 않는 단축 URL을 조회하는 경우 NotFoundShortenUrlException 예외가 발생한다.")
	void shortenUrlExceptionTest() {
		String shortenUrlKey = "ABCDEFGHJ";

		assertThatThrownBy(() -> {
			simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
		}).isInstanceOf(NotFoundShortenUrlException.class);
	}
}