package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ContextDao {
	// Ȩ �Խù��� List���·� ��ȯ �ޱ� ���� �޼ҵ�
	// ContextDaoImpl���� ����
	List getContextList() throws DataAccessException;
}
