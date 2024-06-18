package parkyj3213.photograminsta.web.dto.subscribe;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscribeDto {

	private Integer id;
	private String username;
	private String profileImageUrl;
	private BigInteger subscribeState;
	private BigInteger equalUserState;
}
