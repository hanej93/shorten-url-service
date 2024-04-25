package kr.co.shortenurlservice.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenUrlRestController {

	@PostMapping("/shortenUrl")
	public ResponseEntity<?> createShortenUrl() {
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/{shortenUrlKey}")
	public ResponseEntity<?> redirectShortenUrl() {
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/shortenUrl/{shortenUrlKey}")
	public ResponseEntity<?> getShortenUrlInformation() {
		return ResponseEntity.ok().body(null);
	}
}
