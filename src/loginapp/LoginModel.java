package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        try {
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        if (this.connection == null){
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String username, String password, String division) throws Exception{
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql= "SELECT * FROM login WHERE username = ? and password = ? and division = ?";
        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, username);
            pr.setString(2, password);
            pr.setString(3, division);

            rs = pr.executeQuery();

            boolean boll1;
            if (rs.next()){

                return true;
            }
            return false;

        }catch (SQLException ex){

            return false;
        }
        finally {
            pr.close();
            rs.close();
        }
    }


}
