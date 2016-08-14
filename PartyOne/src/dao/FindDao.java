package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface FindDao {
	// Ȩ �Խù��� List���·� ��ȯ �ޱ� ���� �޼ҵ�
	// ContextDaoImpl���� ����
	List getContextList() throws DataAccessException;
	List getWriterList(String keyword) throws DataAccessException;
	List getTitleList(String keyword) throws DataAccessException;
	List getSubList(String keyword) throws DataAccessException;
}
