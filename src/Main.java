import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            Statement st = con.createStatement();
            //st.addBatch("drop table peeps");
            st.addBatch("create table peeps (id int not null generated always as identity constraint peeps_PK primary key, jmeno varchar(20))");
            st.addBatch("insert into peeps (jmeno) values ('Marin')");
            st.executeBatch();
            ResultSet res = st.executeQuery("select * from peeps");
            while(res.next()){
                System.out.println(res.getString("jmeno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
