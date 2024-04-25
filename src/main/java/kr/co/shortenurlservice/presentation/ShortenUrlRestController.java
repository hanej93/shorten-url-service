package kr.co.shortenurlservice.presentation;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.shortenurlservice.application.SimpleShortenUrlService;

@RestController
public class ShortenUrlRestController {

	private final SimpleShortenUrlService simpleShortenUrlService;

	public ShortenUrlRestController(SimpleShortenUrlService simpleShortenUrlService) {
		this.simpleShortenUrlService = simpleShortenUrlService;
	}

	@PostMapping("/shortenUrl")
	public ResponseEntity<ShortenUrlCreateResponseDto> createShortenUrl(
		@Valid @RequestBody ShortenUrlCreateRequestDto requestDto
	) {
		ShortenUrlCreateResponseDto responseDto =
			simpleShortenUrlService.generateShortenUrl(requestDto);
		return ResponseEntity.ok().body(responseDto);
	}

	@GetMapping("/{shortenUrlKey}")
	public ResponseEntity<?> redirectShortenUrl(
		@PathVariable(name = "shortenUrlKey") String shortenUrlKey
	) throws URISyntaxException {
		String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

		URI redirectUrl = new URI(originalUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUrl);

		return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
	}

	@GetMapping("/shortenUrl/{shortenUrlKey}")
	public ResponseEntity<ShortenUrlInformationDto> getShortenUrlInformation(
		@PathVariable String shortenUrlKey
	) {
		ShortenUrlInformationDto responseDto =
			simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
		return ResponseEntity.ok().body(responseDto);
	}
}
