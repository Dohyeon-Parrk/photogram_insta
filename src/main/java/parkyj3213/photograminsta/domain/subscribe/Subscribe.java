package parkyj3213.photograminsta.domain.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parkyj3213.photograminsta.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(
				name = "subscribe_uk",
				columnNames = {"fromUserId", "toUserId"}
		) 
	}
)
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "fromUserId")
	@ManyToOne
	private User fromUser;

	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser;

	private LocalDateTime createDate;

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
