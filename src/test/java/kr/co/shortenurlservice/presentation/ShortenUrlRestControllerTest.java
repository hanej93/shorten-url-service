package kr.co.shortenurlservice.presentation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import kr.co.shortenurlservice.application.SimpleShortenUrlService;
import kr.co.shortenurlservice.domain.NotFoundShortenUrlException;

@WebMvcTest(controllers = ShortenUrlRestController.class)
class ShortenUrlRestControllerTest {

	@MockBean
	private SimpleShortenUrlService simpleShortenUrlService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("원래의 URL로 리다이렉트 되어야한다.")
	void redirectTest() throws Exception {
		String expectedOriginalUrl = "https://www.naver.com";

		when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any())).thenReturn(expectedOriginalUrl);

		mockMvc.perform(get("/any-key"))
			.andExpect(status().isMovedPermanently())
			.andExpect(header().string("Location", expectedOriginalUrl));
	}

	@Test
	@DisplayName(" 없는 단축 URL을 조회하는 경우 404 응답")
	void redirectNotFoundTest() throws Exception {
		when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any())).thenThrow(NotFoundShortenUrlException.class);

		mockMvc.perform(get("/any-key"))
			.andExpect(status().isNotFound())
			.andExpect(content().string("단축 URL을 찾지 못했습니다."))
			.andReturn();
	}
}