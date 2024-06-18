package parkyj3213.photograminsta.web.dto.image;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import parkyj3213.photograminsta.domain.image.Image;
import parkyj3213.photograminsta.domain.user.User;

@Getter
@Setter
public class ImageUploadDto {
	
	//@NotBlank  -> MultipartFile에 지원되지 않음 
	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}

}
