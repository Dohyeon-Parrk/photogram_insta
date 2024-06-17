package parkyj3213.photograminsta.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parkyj3213.photograminsta.domain.user.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

	private boolean pageOwnerState;
	private int imageCount;
	private boolean subscribeState;
	private int subscribeCount;
	private User user;
	
}








