
package ac.kr.skuniv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ConnectDB {

	/************ 데이터 베이스 인스턴스 생성 *************/
	private static ConnectDB connectDB = new ConnectDB();

	public static ConnectDB getConnectDB() {
		return connectDB;
	}

	/****************************************/

	private ConnectDB() {
	}
	public String savePath = "D:\\jsp/Web (2)/WebContent/OMG" +"/Imagefile";
	/******************** 회원가입 메소드 *************************/
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
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
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

	/************ ID 중복확인 *************/
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
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = conn.prepareStatement("select id from employee Info where id = ?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			if (rs.next()) // 아이디 존재
				x = 1; // 같은 아이디 있음
			else
				x = -1;// 같은 아이디 없음
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

	/********************* 로그인 메소드 **********************/
	public int userCheck(String ID, String PASSWD) {
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check_pwd = null; // 디비에서 찾은 password를 담는 변수
		int check = 0; // 안드로이드에게 다시 전송하고자 할 메세지를 담고 있는 변수
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select passwd from employee where id = ?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			// 모든 등록ID를 리스트로 묶음

			// id가 있다.
			if (rs.next()) {
				check_pwd = rs.getString("passwd");
				if (check_pwd.equals(PASSWD)) {
					check = 1;
				} else
					check = -2; // 비밀번호 실패
			}
			// id 가 없다.
			else {
				check = -1; // 아이디 실패
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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

	/************** 기존 회원 정보를 가져오는 메소드 **************/
	public DataModel getMember(String ID, String PASSWD) {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from employee where id = ?");
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 해당 아이디에 대한 레코드가 존재

				// 사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				dataModel = new DataModel();// 데이터저장빈 객체생성
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
		} // 커넥션이 제대로 연결되면 수행된다.
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

	/************** 기존 회원 정보를 가져오는 메소드 **************/
	public DataModel getTMember() {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		try {
			dataModel = new DataModel();// 데이터저장빈 객체생성
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from employee");

			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				dataModel.array_name.add(rs.getString("name"));
				dataModel.array_dept.add(rs.getString("department"));
				dataModel.array_position.add(rs.getString("position"));
				dataModel.array_email.add(rs.getString("email"));
				dataModel.array_url.add(rs.getString("profile_url"));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/************** 해당 글자로 시작하는 이름 찾기 **************/
	public DataModel searchName(String searchId) {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		try {
			dataModel = new DataModel();// 데이터저장빈 객체생성
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			System.out.println(searchId);
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select name,id,profile_url from employee where name like '%"+searchId+"%'");
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				dataModel.array_name.add(rs.getString("name"));
				dataModel.array_id.add(rs.getString("id"));
				dataModel.array_url.add(rs.getString("profile_url"));
				System.out.println("id:  " + dataModel.array_name.get(i));
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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

	/************* 회원 정보 수정 처리(modifyPro.jsp)에서 회원 정보 수정을 처리하는 메소드 ********/
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
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			System.out.println(dataModel.getPasswd());
			System.out.println(dataModel.getName());
			System.out.println(dataModel.getEmail());
			System.out.println(dataModel.getDepartment());
			System.out.println(dataModel.getPosition());
			// pstmt = conn.prepareStatement(
			// "select passwd from employee where id = ?");
			// pstmt.setString(1, dataModel.getId());
			// rs = pstmt.executeQuery();

			// if(rs.next()){//해당 아이디가 있으면 수행
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
			x = 1;// 회원정보 수정 처리 성공
			// }else
			// x= 0;//회원정보 수정 처리 실패
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

	/************* 회원 정보 수정 처리(modifyPro.jsp)에서 회원 정보 수정을 처리하는 메소드 ********/
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
				throw new Exception("데이터베이스에 연결할 수 없습니다.");

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
	
	   /**********회의  참여자와 내용 추가 *****************/
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
	      contents = "O.M.G:"+ownerName +"님이 ";
	      
	      System.out.println("length : " + selectName.length);
	      for (int i = 0; i < selectName.length; i++){
	    	  guest[i] = selectName[i];
	         if(!selectName[i].equals("")){
	        	 guestName.add(selectName(selectName[i]));
	        	 System.out.println("들어옴");
	         }
	        
	      }
	      Date date1 = new Date();
	      String start = date1.getHours()+"시 " + date1.getMinutes()+"분";
	      String now = ""+date1.getDate()+date1.getHours()+date1.getMinutes()+date1.getSeconds();
	      
	      for (int i = 0 ; i < guestName.size(); i++)
	      {
	         System.out.println("print guest : " + guestName.get(i));
	         contents += guestName.get(i) +"님";
	         if(i!=(guestName.size()-1)) contents +=", ";
	         
	      }
	      contents +="과 회의를 시작합니다." + "#" + start + "@logo.png<end>";
	      String date = month + "월 " + day + "일 " + hour + "시 " + minute + "분"; 
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = (Connection) DriverManager.getConnection(
	               "jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
	               "301013");

	         if (conn == null)
	            throw new Exception("데이터베이스에 연결할 수 없습니다.");
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
	
	/**********해당 회의  삭제 *****************/
	public int deleteEnrol(String encode)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("삭제하려는 encode : " + encode);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
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
	
	/********** 아이디에 해당하는 회의 내용 가져오기 ***************/
	public DataModel selectMyEnrol(String id) {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String guest = null;
		String guest2 = null;
		String guest3 = null;
		String guest4 = null;
		String guest5 = null;
		int i = 0;
		try {
			dataModel = new DataModel();// 데이터저장빈 객체생성
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			System.out.println("로그인 아이디 : " + id);
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from enrol where owner=? || guest = ? || guest2 = ? || guest3 = ? || guest4 = ? || guest5 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
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
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/********** 아이디에 해당하는 회의 내용 가져오기 ***************/
	public String selectName(String guest) {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		int i = 0;
		try {
			dataModel = new DataModel();// 데이터저장빈 객체생성
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select name from employee where id = ?");
			pstmt.setString(1, guest);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	
	
	/********** 등록된 회의 시간 select하는 메소드  ***************/
	public int[] selectTime(String id) {
		
		Connection conn = null; // null로 초기화 한다.
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
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = conn.prepareStatement("select date, encode from enrol where owner=? or guest = ? or guest2 = ? or guest3 = ? or guest4 = ? or guest5 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
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
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/****************현재시간과 회의 시간을 검사하는 메소드 ******************/
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
			checkTime = "" + month + "월 " + "0" + day + "일 " + hour + "시 " ;
		else
			checkTime = "" + month + "월 " + day + "일 " + hour + "시 " ;
		if (minute < 10)
			checkTime += "0";
		checkTime += minute +"분";
		
		System.out.println("checkTime : " + checkTime);
		System.out.println("testTime : " + date);
		

		System.out.println("월 : " + month);
		System.out.println("일 : " + day);
		System.out.println("시 : " + hour);
		System.out.println("분 : " + minute);
		
		if (checkTime.equals(date)){
			
			System.out.println("equals같다");
			return 1;
		}else{
			return -1;
		}
	}
	/********************************************************/
	
	/********** 회의 코드에 해당하는 대화 내용 가져오기 ***************/
	public DataModel selectContents(String encode) {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String contents = null;
		
		int i = 0;
		try {
			dataModel = new DataModel();// 데이터저장빈 객체생성
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select contents from enrol where encode=?");
			pstmt.setString(1, encode);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				contents = rs.getString("contents");
				System.out.println("contents : " +contents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/********* 데이터베이스에 저장 된 이름과 메세지를  자르는 메소드 ****************/
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
	
	/********************채팅 내용을 디비에 저장하는 메소드 *************************/
	public String insertMessage(String encode, String name, String msgText, String current_contents, String url) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date date = new Date();
		String day = date.getHours() +"시 " + date.getMinutes()+"분"; 
		String contents = "[" + name + "] : " + msgText+"#" + day + "@" + url + "<end>";
		current_contents += contents;
		System.out.println("contents : " + contents);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");

			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
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
	
	/********** 아이디에 해당하는 이름 가져오기 ***************/
	public String selectUserName(String id) {
		
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String name = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select name from employee where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/********** 아이디에 해당하는 이름 가져오기 ***************/
	public String selectURL(String id) {
		
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select profile_url from employee where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				url = rs.getString("profile_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/********** 회의 대화내용(contents) 가져오기  ***************/
	public String selectCurrentContents(String encode) {
		
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String contents = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select contents from enrol where encode = ?");
			pstmt.setString(1, encode);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				contents = rs.getString("contents");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 커넥션이 제대로 연결되면 수행된다.
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
	
	/********** 아이디에 해당하는 회의 내용 가져오기 ***************/
	public DataModel selectLastMeeting(String id) {
		DataModel dataModel = null;
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String guest = null;
		String guest2 = null;
		String guest3 = null;
		String guest4 = null;
		String guest5 = null;
		int i = 0;
		try {
			dataModel = new DataModel();// 데이터저장빈 객체생성
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=utf8", "root",
					"301013");
			System.out.println("로그인 아이디 : " + id);
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from enrol where owner=? || guest = ? || guest2 = ? || guest3 = ? || guest4 = ? || guest5 = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 해당 아이디에 대한 레코드가 존재
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
		} // 커넥션이 제대로 연결되면 수행된다.
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
