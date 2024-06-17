package parkyj3213.photograminsta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parkyj3213.photograminsta.domain.user.User;
import parkyj3213.photograminsta.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional	// Write(insert, Update, delete)
	public User 회원가입(User user) {
		
		//  password 암호화, 권한 설정
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		
		User userEntity = userRepository.save(user);
		return userEntity;
	}

}
