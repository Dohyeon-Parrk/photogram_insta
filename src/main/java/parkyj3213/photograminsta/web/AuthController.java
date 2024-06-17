package parkyj3213.photograminsta.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import parkyj3213.photograminsta.domain.user.User;
import parkyj3213.photograminsta.service.AuthService;
import parkyj3213.photograminsta.web.dto.auth.SignupDto;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller // 1. Ioc 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	private final AuthService authService;

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		// log.info(signupDto.toString());
			User user = signupDto.toEntity();
			log.info(user.toString());

			User userEntity = authService.회원가입(user);

			return "auth/signin";
	}
}
