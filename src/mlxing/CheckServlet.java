package mlxing;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckServlet() {
        super();
    }
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1111");
		String userid=request.getParameter("userid");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 try {
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3366/test", "root", "");	
			String sql="select count(empno) from emp where empno=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, userid);
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()){
		    	if(rs.getInt(1)>0){
		    		out.print("1");
		    	}else{
		    		out.print("0");
		    	}
		    }
		    out.close();
		    conn.close();
		 } catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
