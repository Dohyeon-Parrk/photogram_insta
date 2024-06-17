package parkyj3213.photograminsta.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parkyj3213.photograminsta.domain.comment.Comment;
import parkyj3213.photograminsta.domain.likes.Likes;
import parkyj3213.photograminsta.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String caption;
	private String postImageUrl;		// 사진을 전송받아서 그 사진을 서버 특정 폴더에 저장 -DB에 저장된 경로를 insert
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	// 좋아요
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Likes> likes;
	
	//댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Comment> comments;
	
	@Transient		// db에 컬럼이 만들어지지 않음
	private boolean likeState;
	
	@Transient
	private int likeCount;
	
	private LocalDateTime createDate;

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl + ", createDate="
				+ createDate + "]";
	}
	
	
}
