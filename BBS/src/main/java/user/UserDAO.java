package user;
//��Ʈ�� ����Ʈ O ����Ʈ
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;//Ŀ�ؼ��� ��� �����Ҽ� �ִ� �ϳ��� ��ü
	private PreparedStatement pstmt;//
	private ResultSet rs; //��� ������ �������ִ� ��ü
	
	//������ ����
	//��ü�� ��������� �ڵ����� Ŀ�ؼ� �Ҽ� �ְ�
	public UserDAO() {
		try {
			//�� db���˿�
			//String dbURL = "jdbc:mysql://localhost:3306/BBS?useUnicode=true&characterEncoding=UTF-8";
			String dbURL = "jdbc:mysql://localhost:3306/bbs?useUnicode=true&characterEncoding=UTF-8";
			String dbID="root";
			String dbPassword="root";
			
			//mysql ����̹��� ã�����ֵ��� Driver�� mysql�� �����Ҽ� �ֵ�Ȥ ���ִ� �ϳ��� �Ű�ü
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			
		}catch (Exception e) {
			e.printStackTrace();//������ ���� ��� 
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
					return 1;//�α��� ����
				}else {
					return 0;//��й�ȣ�� ����ġ
				}
			}
			return -1;//���̵� ����
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

			
			
			return pstmt.executeUpdate();//insert �� �����ϸ� 0�̻��� ���� ��ȯ
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;//������ ���̽� ����
	}
}
