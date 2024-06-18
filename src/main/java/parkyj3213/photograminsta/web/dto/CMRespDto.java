package parkyj3213.photograminsta.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CMRespDto<T> {

	private int code;		//1(성공) , -1(실패)
	private String message;
	private T data;
}
