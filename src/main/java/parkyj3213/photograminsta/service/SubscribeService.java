package parkyj3213.photograminsta.service;

import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parkyj3213.photograminsta.domain.subscribe.SubscribeRepository;
import parkyj3213.photograminsta.handler.ex.CustomApiException;
import parkyj3213.photograminsta.web.dto.subscribe.SubscribeDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em;		// Repository는 EntityManager를 구현해서 만들어져 있는 구현체
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, ");		// ? -> principalId
		sb.append("if((? = u.id), 1, 0) equalUserState ");		// ? -> principalId
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ? ");		// ? -> pageUserId
		
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2,  principalId)
				.setParameter(3, pageUserId);
		
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto>subscribeDtos = result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
	}
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);			
		} catch(Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
