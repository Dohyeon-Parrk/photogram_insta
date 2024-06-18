package parkyj3213.photograminsta.web.dto.user;

import lombok.*;
import parkyj3213.photograminsta.domain.user.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileDto {

	private boolean pageOwnerState;
	private int imageCount;
	private boolean subscribeState;
	private int subscribeCount;
	private User user;
	
}








