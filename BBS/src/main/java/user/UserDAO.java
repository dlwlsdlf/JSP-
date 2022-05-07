package user;
//컨트롤 쉬프트 O 임포트
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;//커넥션은 디비에 접근할수 있는 하나의 객체
	private PreparedStatement pstmt;//
	private ResultSet rs; //어떠한 정보를 담을수있는 객체
	
	//생성자 만듬
	//객체를 만들었을때 자동으로 커넥션 할수 있게
	public UserDAO() {
		try {
			//내 db유알엘
			//String dbURL = "jdbc:mysql://localhost:3306/BBS?useUnicode=true&characterEncoding=UTF-8";
			String dbURL = "jdbc:mysql://localhost:3306/bbs?useUnicode=true&characterEncoding=UTF-8";
			String dbID="root";
			String dbPassword="root";
			
			//mysql 드라이버를 찾을수있도록 Driver는 mysql에 접속할수 있도혹 해주는 하나의 매개체
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			
		}catch (Exception e) {
			e.printStackTrace();//오류가 먼지 출력 
		}
	}
	
	public int login(String userID,String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1;//로그인 성공
				}else {
					return 0;//비밀번호가 불일치
				}
			}
			return -1;//아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());

			
			
			return pstmt.executeUpdate();//insert 문 실행하면 0이상의 숫자 반환
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터 베이스 오류
	}
}
