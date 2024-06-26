package parkyj3213.photograminsta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parkyj3213.photograminsta.domain.comment.Comment;
import parkyj3213.photograminsta.domain.comment.CommentRepository;
import parkyj3213.photograminsta.domain.image.Image;
import parkyj3213.photograminsta.domain.user.User;
import parkyj3213.photograminsta.domain.user.UserRepository;
import parkyj3213.photograminsta.handler.ex.CustomApiException;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public Comment 댓글쓰기(String content, int imageId, int userId) {
		
		Image image = new Image();
		image.setId(imageId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("유저 아이디를 찾을 수 없습니다."));
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(userEntity);
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제(int id) {
		try {
			commentRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
	}
}
