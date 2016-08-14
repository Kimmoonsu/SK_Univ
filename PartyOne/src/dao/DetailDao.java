package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DetailDao {
	// 홈 게시물을 List형태로 반환 받기 위한 메소드
	// ContextDaoImpl에서 구현
	List getDetailList(int context_id) throws DataAccessException;
}
