
package ac.kr.skuniv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ConnectDB {

	/************ ������ ���̽� �ν��Ͻ� ���� *************/
	private static ConnectDB connectDB = new ConnectDB();

	public static ConnectDB getConnectDB() {
		return connectDB;
	}

	/****************************************/

	private ConnectDB() {
	}
	public String savePath = "D:\\jsp/Web (2)/WebContent/OMG" +"/Imagefile";
	/******************** ȸ������ �޼ҵ� *************************/
	public String insertMember(String ID, String PASSWD, String NAME, String DEPARTMENT, String POSITION, String EMAIL, String URL) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String insert_msg = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("insert into employee values (?,?,?,?,?,?,?)");
			pstmt.setString(1, ID);
			pstmt.setString(2, PASSWD);
			pstmt.setString(3, NAME);
			pstmt.setString(4, DEPARTMENT);
			pstmt.setString(5, POSITION);
			pstmt.setString(6, EMAIL);
			pstmt.setString(7, URL);
			pstmt.executeUpdate();
			insert_msg = ":success:";
			System.out.println("id: " + ID + "  passwd : " + PASSWD + "  name : " + NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return insert_msg;
	}
	/*******************************************************/

	/************ ID �ߺ�Ȯ�� *************/
	public int confirmId(String ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = conn.prepareStatement("select id from employee Info where id = ?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			if (rs.next()) // ���̵� ����
				x = 1; // ���� ���̵� ����
			else
				x = -1;// ���� ���̵� ����
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
	/************************************/

	/********************* �α��� �޼ҵ� **********************/
	public int userCheck(String ID, String PASSWD) {
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check_pwd = null; // ��񿡼� ã�� password�� ��� ����
		int check = 0; // �ȵ���̵忡�� �ٽ� �����ϰ��� �� �޼����� ��� �ִ� ����
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select passwd from employee where id = ?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			// ��� ���ID�� ����Ʈ�� ����

			// id�� �ִ�.
			if (rs.next()) {
				check_pwd = rs.getString("passwd");
				if (check_pwd.equals(PASSWD)) {
					check = 1;
				} else
					check = -2; // ��й�ȣ ����
			}
			// id �� ����.
			else {
				check = -1; // ���̵� ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return check;
	}
	/****************************************************/

	/************** ���� ȸ�� ������ �������� �޼ҵ� **************/
	public DataModel getMember(String ID, String PASSWD) {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from employee where id = ?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����

				// ����ڰ� �Է��� ��й�ȣ�� ���̺��� ��й�ȣ�� ������ ����
				dataModel = new DataModel();// ����������� ��ü����
				dataModel.setId(rs.getString("id"));
				dataModel.setName(rs.getString("name"));
				dataModel.setPasswd(rs.getString("passwd"));
				dataModel.setEmail(rs.getString("email"));
				dataModel.setPosition(rs.getString("position"));
				dataModel.setDepartment(rs.getString("department"));
				dataModel.setURL(rs.getString("profile_url"));
				System.out.println("url : " + rs.getString("profile_url"));
				System.out.println("name: " + dataModel.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return dataModel;
	}
	/**************************************************/

	/************** ���� ȸ�� ������ �������� �޼ҵ� **************/
	public DataModel getTMember() {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		try {
			dataModel = new DataModel();// ����������� ��ü����
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from employee");

			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				dataModel.array_name.add(rs.getString("name"));
				dataModel.array_dept.add(rs.getString("department"));
				dataModel.array_position.add(rs.getString("position"));
				dataModel.array_email.add(rs.getString("email"));
				dataModel.array_url.add(rs.getString("profile_url"));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return dataModel;
	}
	/**************************************************/
	
	/************** �ش� ���ڷ� �����ϴ� �̸� ã�� **************/
	public DataModel searchName(String searchId) {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		try {
			dataModel = new DataModel();// ����������� ��ü����
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			System.out.println(searchId);
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select name,id,profile_url from employee where name like '%"+searchId+"%'");
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				dataModel.array_name.add(rs.getString("name"));
				dataModel.array_id.add(rs.getString("id"));
				dataModel.array_url.add(rs.getString("profile_url"));
				System.out.println("id:  " + dataModel.array_name.get(i));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return dataModel;
	}
	/**************************************************/

	/************* ȸ�� ���� ���� ó��(modifyPro.jsp)���� ȸ�� ���� ������ ó���ϴ� �޼ҵ� ********/
	public int updateMember(DataModel dataModel) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			System.out.println(dataModel.getPasswd());
			System.out.println(dataModel.getName());
			System.out.println(dataModel.getEmail());
			System.out.println(dataModel.getDepartment());
			System.out.println(dataModel.getPosition());
			// pstmt = conn.prepareStatement(
			// "select passwd from employee where id = ?");
			// pstmt.setString(1, dataModel.getId());
			// rs = pstmt.executeQuery();

			// if(rs.next()){//�ش� ���̵� ������ ����
			// String dbpasswd= rs.getString("passwd");
			// if(BCrypt.checkpw(shaPass,dbpasswd)){
			pstmt = conn.prepareStatement(
					"update employee set passwd = ?, name=?, email=?, department=?, position=?" + "where id=?");
			pstmt.setString(1, dataModel.getPasswd());
			pstmt.setString(2, dataModel.getName());
			pstmt.setString(3, dataModel.getEmail());
			pstmt.setString(4, dataModel.getDepartment());
			pstmt.setString(5, dataModel.getPosition());
			pstmt.setString(6, dataModel.getId());
			pstmt.executeUpdate();
			x = 1;// ȸ������ ���� ó�� ����
			// }else
			// x= 0;//ȸ������ ���� ó�� ����
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
	/**************************************************/

	/************* ȸ�� ���� ���� ó��(modifyPro.jsp)���� ȸ�� ���� ������ ó���ϴ� �޼ҵ� ********/
	public int finishEnrol(String encode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");

			pstmt = conn.prepareStatement("update enrol set state=? where encode=?");
			pstmt.setInt(1, 2);
			pstmt.setString(2, encode);
			pstmt.executeUpdate();
			System.out.println("pstmt : " + pstmt);
			x = 1;
		} catch (Exception ex) {
			x = 0;
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
	/**************************************************/
	
	   /**********ȸ��  �����ڿ� ���� �߰� *****************/
	   public int insertEnrol(String owner, String selectName[], String title, String month, String day, String hour, String minute)
	   {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String[] guest = new String[5];
	      ArrayList<String> guestName = new ArrayList<String>();
	      String contents = null;
	      String ownerName = null;
	      int j=0;
	      ownerName = selectName(owner);
	      contents = "O.M.G:"+ownerName +"���� ";
	      
	      System.out.println("length : " + selectName.length);
	      for (int i = 0; i < selectName.length; i++){
	    	  guest[i] = selectName[i];
	         if(!selectName[i].equals("")){
	        	 guestName.add(selectName(selectName[i]));
	        	 System.out.println("����");
	         }
	        
	      }
	      Date date1 = new Date();
	      String start = date1.getHours()+"�� " + date1.getMinutes()+"��";
	      String now = ""+date1.getDate()+date1.getHours()+date1.getMinutes()+date1.getSeconds();
	      
	      for (int i = 0 ; i < guestName.size(); i++)
	      {
	         System.out.println("print guest : " + guestName.get(i));
	         contents += guestName.get(i) +"��";
	         if(i!=(guestName.size()-1)) contents +=", ";
	         
	      }
	      contents +="�� ȸ�Ǹ� �����մϴ�." + "#" + start + "@logo.png<end>";
	      String date = month + "�� " + day + "�� " + hour + "�� " + minute + "��"; 
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = (Connection) DriverManager.getConnection(
	               "jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
	               "301013");

	         if (conn == null)
	            throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	         pstmt = (PreparedStatement) conn.prepareStatement("insert into enrol values (?,?,?,?,?,?,?,?,?,?,?)");
	         pstmt.setString(1, now);
	         pstmt.setString(2, owner);
	         pstmt.setString(3, guest[0]);
	         pstmt.setString(4, guest[1]);
	         pstmt.setString(5, guest[2]);
	         pstmt.setString(6, guest[3]);
	         pstmt.setString(7, guest[4]);
	         pstmt.setString(8, title);
	         pstmt.setString(9, date);
	         pstmt.setString(10, contents);
	         pstmt.setInt(11, 0);
	         pstmt.executeUpdate();
	      
	         System.out.println("guest: " + guest + "  date: " + date );
	      } catch (Exception ex) {
	         ex.printStackTrace();
	         return -1;
	      } finally {
	         if (rs != null)
	            try {
	               rs.close();
	            } catch (SQLException ex) {
	            }
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (SQLException ex) {
	            }
	         if (conn != null)
	            try {
	               conn.close();
	            } catch (SQLException ex) {
	            }
	      }
	      return 1;
	   }
	   /******************************************/
	
	/**********�ش� ȸ��  ���� *****************/
	public int deleteEnrol(String encode)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("�����Ϸ��� encode : " + encode);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("delete from enrol where encode=?");
			pstmt.setString(1, encode);
			
			pstmt.executeUpdate();
		
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return 1;
	}
	/******************************************/
	
	/********** ���̵� �ش��ϴ� ȸ�� ���� �������� ***************/
	public DataModel selectMyEnrol(String id) {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String guest = null;
		String guest2 = null;
		String guest3 = null;
		String guest4 = null;
		String guest5 = null;
		int i = 0;
		try {
			dataModel = new DataModel();// ����������� ��ü����
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			System.out.println("�α��� ���̵� : " + id);
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from enrol where owner=? || guest = ? || guest2 = ? || guest3 = ? || guest4 = ? || guest5 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				dataModel.array_encode.add(rs.getString("encode"));
				dataModel.array_title.add(rs.getString("title"));
				dataModel.array_date.add(rs.getString("date"));
				dataModel.array_owner.add(selectName(rs.getString("owner")));
				dataModel.array_guest.add(selectName(rs.getString("guest")));
				dataModel.array_guest2.add(selectName(rs.getString("guest2")));
				dataModel.array_guest3.add(selectName(rs.getString("guest3")));
				dataModel.array_guest4.add(selectName(rs.getString("guest4")));
				dataModel.array_guest5.add(selectName(rs.getString("guest5")));
				dataModel.array_state.add(rs.getInt("state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		
		return dataModel;
	}
	/**************************************************/
	
	/********** ���̵� �ش��ϴ� ȸ�� ���� �������� ***************/
	public String selectName(String guest) {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		int i = 0;
		try {
			dataModel = new DataModel();// ����������� ��ü����
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select name from employee where id = ?");
			pstmt.setString(1, guest);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		System.out.println("name : " + name);
		return name;
	}
	/**************************************************/
	
	
	
	/********** ��ϵ� ȸ�� �ð� select�ϴ� �޼ҵ�  ***************/
	public int[] selectTime(String id) {
		
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String date = null;
		int i = 0;
		String encode = null;
		int check[]= new int[100];
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = conn.prepareStatement("select date, encode from enrol where owner=? or guest = ? or guest2 = ? or guest3 = ? or guest4 = ? or guest5 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				date = rs.getString("date");
				encode = rs.getString("encode");
				check[i] = checkTime(date);
				System.out.println("check : " + check[i]);
				System.out.println("checkencode : " + encode);
				if (check[i] == 1)
				{
					System.out.println("check : " + check[i]);
					System.out.println("checkencode : " + encode);
					pstmt = conn.prepareStatement("update enrol set state = ? where encode=?");
					pstmt.setInt(1, 1);
					pstmt.setString(2, encode);
					System.out.println("pstmt : " + pstmt);
					pstmt.executeUpdate();
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return check;
		
	}
	/**************************************************/
	
	/****************����ð��� ȸ�� �ð��� �˻��ϴ� �޼ҵ� ******************/
	@SuppressWarnings("deprecation")
	public int checkTime(String date)
	{
		Date now = new Date();
		
		int month = (now.getMonth()+1);
		int day = now.getDate();
		
		int hour = now.getHours();
		int minute = now.getMinutes();
		String checkTime;
		if (day < 10)
			checkTime = "" + month + "�� " + "0" + day + "�� " + hour + "�� " ;
		else
			checkTime = "" + month + "�� " + day + "�� " + hour + "�� " ;
		if (minute < 10)
			checkTime += "0";
		checkTime += minute +"��";
		
		System.out.println("checkTime : " + checkTime);
		System.out.println("testTime : " + date);
		

		System.out.println("�� : " + month);
		System.out.println("�� : " + day);
		System.out.println("�� : " + hour);
		System.out.println("�� : " + minute);
		
		if (checkTime.equals(date)){
			
			System.out.println("equals����");
			return 1;
		}else{
			return -1;
		}
	}
	/********************************************************/
	
	/********** ȸ�� �ڵ忡 �ش��ϴ� ��ȭ ���� �������� ***************/
	public DataModel selectContents(String encode) {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String contents = null;
		
		int i = 0;
		try {
			dataModel = new DataModel();// ����������� ��ü����
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select contents from enrol where encode=?");
			pstmt.setString(1, encode);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				contents = rs.getString("contents");
				System.out.println("contents : " +contents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		System.out.println("notnull : " + contents);
		
		splitContent(contents, dataModel);
		
		return dataModel;
	}
	/**************************************************/
	
	/********* �����ͺ��̽��� ���� �� �̸��� �޼�����  �ڸ��� �޼ҵ� ****************/
	public void splitContent(String contents, DataModel dataModel)
	{
		String[] original = contents.split("<end>");
		
		for (int i = 0; i < original.length; i++)
		{
			String name[] = original[i].split(":");
			String msg[] = name[1].split("#");
			dataModel.chat_name.add(name[0]);		
			dataModel.chat_msg.add(msg[0].trim());
			String url[] = msg[1].split("@");
			dataModel.chat_time.add(url[0].trim());
			dataModel.chat_url.add(url[1].trim());
			System.out.println("name : " + dataModel.chat_name.get(i));
			System.out.println("msg: " + dataModel.chat_msg.get(i));
			System.out.println("msg: " + dataModel.chat_time.get(i));
		}
	}
	/********************************************************/
	
	/********************ä�� ������ ��� �����ϴ� �޼ҵ� *************************/
	public String insertMessage(String encode, String name, String msgText, String current_contents, String url) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date date = new Date();
		String day = date.getHours() +"�� " + date.getMinutes()+"��"; 
		String contents = "[" + name + "] : " + msgText+"#" + day + "@" + url + "<end>";
		current_contents += contents;
		System.out.println("contents : " + contents);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = conn.prepareStatement(
					"update enrol set contents=?" + "where encode=?");
			pstmt.setString(1, current_contents);
			pstmt.setString(2, encode);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return "";
	}
	/*******************************************************/
	
	/********** ���̵� �ش��ϴ� �̸� �������� ***************/
	public String selectUserName(String id) {
		
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select name from employee where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return name;
	}
	/**************************************************/
	
	/********** ���̵� �ش��ϴ� �̸� �������� ***************/
	public String selectURL(String id) {
		
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select profile_url from employee where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				url = rs.getString("profile_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return url;
	}
	/**************************************************/
	
	/********** ȸ�� ��ȭ����(contents) ��������  ***************/
	public String selectCurrentContents(String encode) {
		
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String contents = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select contents from enrol where encode = ?");
			pstmt.setString(1, encode);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				contents = rs.getString("contents");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		return contents;
	}
	/**************************************************/
	
	/********** ���̵� �ش��ϴ� ȸ�� ���� �������� ***************/
	public DataModel selectLastMeeting(String id) {
		DataModel dataModel = null;
		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String guest = null;
		String guest2 = null;
		String guest3 = null;
		String guest4 = null;
		String guest5 = null;
		int i = 0;
		try {
			dataModel = new DataModel();// ����������� ��ü����
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			System.out.println("�α��� ���̵� : " + id);
			if (conn == null)
				throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from enrol where owner=? || guest = ? || guest2 = ? || guest3 = ? || guest4 = ? || guest5 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// �ش� ���̵� ���� ���ڵ尡 ����
				int state=0;
				state = rs.getInt("state");
			
				if(state == 2){
				dataModel.array_encode.add(rs.getString("encode"));
				dataModel.array_title.add(rs.getString("title"));
				dataModel.array_date.add(rs.getString("date"));
				dataModel.array_owner.add(selectName(rs.getString("owner")));
				dataModel.array_guest.add(selectName(rs.getString("guest")));
				dataModel.array_guest2.add(selectName(rs.getString("guest2")));
				dataModel.array_guest3.add(selectName(rs.getString("guest3")));
				dataModel.array_guest4.add(selectName(rs.getString("guest4")));
				dataModel.array_guest5.add(selectName(rs.getString("guest5")));
				//dataModel.array_state.add(rs.getInt("state"));
				}	
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
		
		return dataModel;
	}
	/**************************************************/
}
