package parkyj3213.photograminsta.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import parkyj3213.photograminsta.config.auth.PrincipalDetails;
import parkyj3213.photograminsta.domain.user.User;
import parkyj3213.photograminsta.service.SubscribeService;
import parkyj3213.photograminsta.service.UserService;
import parkyj3213.photograminsta.web.dto.CMRespDto;
import parkyj3213.photograminsta.web.dto.subscribe.SubscribeDto;
import parkyj3213.photograminsta.web.dto.user.UserUpdateDto;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;
	private final SubscribeService subscribeService;

	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
		principalDetails.setUser(userEntity);
		return new ResponseEntity<>(new CMRespDto<>(1, "프로필사진변경 성공", null), HttpStatus.OK);
	}

	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		List<SubscribeDto> subscribeDto = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
		return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 리스트 가져오기 성공", subscribeDto), HttpStatus.OK);
	}

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult,
							   @AuthenticationPrincipal PrincipalDetails principalDetails) {

		User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		principalDetails.setUser(userEntity);
		return new CMRespDto<>(1, "회원수정완료", userEntity); // 응답 시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답
	}
}
