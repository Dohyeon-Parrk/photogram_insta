package parkyj3213.photograminsta.web.dto.comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// NotNull : Null값 체크
// NotEmpty: 빈값이거나 null 체크
// NotBlank : 빈값이거나 null 체크 그리고 빈 공백 체크

@Getter
@Setter
public class CommentDto {

	@NotBlank
	private String content;
	@NotNull
	private Integer imageId;
}
