package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface FindDao {
	// 홈 게시물을 List형태로 반환 받기 위한 메소드
	// ContextDaoImpl에서 구현
	List getContextList() throws DataAccessException;
	List getWriterList(String keyword) throws DataAccessException;
	List getTitleList(String keyword) throws DataAccessException;
	List getSubList(String keyword) throws DataAccessException;
}
