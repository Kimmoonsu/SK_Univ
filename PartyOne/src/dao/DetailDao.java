package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DetailDao {
	// Ȩ �Խù��� List���·� ��ȯ �ޱ� ���� �޼ҵ�
	// ContextDaoImpl���� ����
	List getDetailList(int context_id) throws DataAccessException;
}
