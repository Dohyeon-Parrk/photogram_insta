package parkyj3213.photograminsta.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parkyj3213.photograminsta.domain.image.Image;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // .IDENTITY: autoincrement가 DB에 맞게 적용됨
	private int id;

	@Column(length = 20, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	private String website;
	private String bio;

	@Column(nullable = false)
	private String email;

	private String phone;
	private String gender;

	private String profileImageUrl;
	private String role;

	// mappedBy => 연관관계의 주인 x -> 테이블 컬럼 생성 x
	// User를 select 할 때 해당 User id로 등록된 image 다 가져옴
	// LAZY = User를 select 할 때 해당 User id로 등록된 image 안 가져옴 -> 대신에 getImages() 함수의
	// image들이 호출될 때만 가져옴
	// Eager = User를 select 할 때 해당 User id로 등록된 image 전부 join해서 가져옴
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "user" })
	private List<Image> images;

	private LocalDateTime createDate;

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate=" + createDate + "]";
	}

}
