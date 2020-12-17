package frateoslogin;

import java.sql.*;

import static frateoslogin.LogInType.Manager;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/franchise";
    private static String user;
    private static String password;

    public static EmployeeFinance getFinancialValues(){
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       EmployeeFinance finance = new EmployeeFinance();
       try{
           con = DriverManager.getConnection(URL,user, password);
           ps = con.prepareStatement("select count(*) from absent inner join employee on employee.idEmployee = absent.idEmployee where username = ? and month(date) = month(curdate()) and year(date) = year(curdate())");
           ps.setString(1,user);
           rs = ps.executeQuery();
           if(rs.next()){
               finance.setAbsences(rs.getInt(1));
           }else{
               finance.setAbsences(0);
           }
           rs.close();
           ps.close();
           ps = con.prepareStatement("select SUM(amount) from cash_advance inner join employee on employee.idEmployee = cash_advance.idEmployee where username = ? and month(date) = month(curdate()) and year(date) = year(curdate())");
           ps.setString(1, user);
           rs = ps.executeQuery();
           if(rs.next()){
               finance.setCashAdvance(rs.getDouble(1));
           }else{
               finance.setCashAdvance((double)0);
           }
           rs.close();
           ps.close();
           ps = con.prepareStatement("select SUM(hours) from overtime inner join employee on employee.idEmployee = overtime.idEmployee where username = ? and month(date) = month(curdate()) and year(date) = year(curdate())");
           ps.setString(1, user);
           rs = ps.executeQuery();
           if(rs.next()){
               finance.setHours(rs.getInt(1));
           }else{
               finance.setHours(0);
           }
           rs.close();
           ps.close();
           ps = con.prepareStatement("select salary from employee inner join roles on employee.idRole = roles.idRole inner join basic_salary_updates on basic_salary_updates.idRole = roles.idRole where username = ? order by basic_salary_updates.date DESC;");
           ps.setString(1,user);
           rs = ps.executeQuery();
           if(rs.next()){
               finance.setSalary(rs.getDouble(1));
           }else{
               finance.setSalary((double)0);
           }
           rs.close();
           ps.close();
           ps = con.prepareStatement("select monthname(curdate()), year(curdate())");
           rs = ps.executeQuery();
           rs.next();
           finance.setMonth(rs.getString(1));
           finance.setYear(rs.getInt(2));
       }catch(SQLException e){
           e.printStackTrace();
       }finally {
           if(rs != null){
               try{
                   rs.close();
                   rs = null;
               }catch(SQLException e){
                   e.printStackTrace();
               }
           }
           if(ps != null){
               try{
                   ps.close();
                   ps = null;
               }catch(SQLException e){
                   e.printStackTrace();
               }
           }
           try{
               if(con != null && !con.isClosed()){
                   if(!con.getAutoCommit()){
                       con.commit();
                       con.setAutoCommit(true);
                   }
                   con.close();
                   con = null;
               }
           }catch(SQLException e){
               e.printStackTrace();
           }
       }
       return finance;
    }

    public static Absent[] getAbsences(){
        Absent[] absences = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select day(date), monthname(date),year(date) from employee inner join absent on employee.idEmployee = absent.idEmployee where username = ? order by date desc";
        try{
            con = DriverManager.getConnection(URL, user, password);
            ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1,user);
            rs = ps.executeQuery();
            if(rs.next()){
                rs.last();
                absences = new Absent[rs.getRow()];
                rs.beforeFirst();
                rs.next();
                int i = 0;
                do{
                    absences[i] = new Absent(rs.getInt(1),rs.getString(2),rs.getInt(3));
                    i++;
                }while(rs.next());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return absences;
    }

    public static CashAdvance[] getCashAdvances(){
        CashAdvance[] cashAdvances = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select day(date), monthname(date),year(date),amount from employee inner join cash_advance on employee.idEmployee = cash_advance.idEmployee where username = ? order by date desc";
        try{
            con = DriverManager.getConnection(URL, user, password);
            ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1,user);
            rs = ps.executeQuery();
            if(rs.next()){
                rs.last();
                cashAdvances = new CashAdvance[rs.getRow()];
                rs.beforeFirst();
                rs.next();
                int i = 0;
                do{
                    cashAdvances[i] = new CashAdvance(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4));
                    i++;
                }while(rs.next());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return cashAdvances;
    }

    public static Overtime[] getOvertimes(){
        Overtime[] overtimes = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select day(date), monthname(date),year(date),hours from employee inner join overtime on employee.idEmployee = overtime.idEmployee where username = ? order by date desc";
        try{
            con = DriverManager.getConnection(URL, user, password);
            ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1,user);
            rs = ps.executeQuery();
            if(rs.next()){
                rs.last();
                overtimes = new Overtime[rs.getRow()];
                rs.beforeFirst();
                rs.next();
                int i = 0;
                do{
                    overtimes[i] = new Overtime(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                    i++;
                }while(rs.next());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return overtimes;
    }

    public static Employee[] getEmployees(){
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        Employee[] employee = null;
        String sql = "select idEmployee as id, first_name, middle_name, last_name, title as role, contact_number as phone, concat(street, ', ', purok, ', ', barangay, ', ', city, ', ', province) as address from employee inner join address on employee.idAddress = address.idAddress inner join roles on employee.idRole = roles.idRole";
        try{
            con = DriverManager.getConnection(URL, user, password);
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery(sql);
            rs.last();
            employee = new Employee[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                employee[i] = new Employee();
                employee[i].setId(rs.getInt("id"));
                employee[i].setFirst_name(rs.getString("first_name"));
                employee[i].setMiddle_name(rs.getString("middle_name"));
                employee[i].setLast_name(rs.getString("last_name"));
                employee[i].setRole(rs.getString("role"));
                employee[i].setPhone(rs.getString("phone"));
                employee[i].setAddress(rs.getString("address"));
                i++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(s != null){
                try{
                    s.close();
                    s = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return employee;
    }

    public static String[] getRoles(){
        Connection con = null;
        Statement s = null;
        ResultSet rs = null;
        String sql = "select title from roles";
        String[] roles = null;
        try{
            con = DriverManager.getConnection(URL, user, password);
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery(sql);
            rs.last();
            roles = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                roles[i] = rs.getString(1);
                i++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(s != null){
                try{
                    s.close();
                    s = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return roles;
    }

    public static boolean addEmployee(String first_name, String middle_name, String last_name, String contact, int address, String role){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into Employee(first_name, middle_name, last_name, contact_number, idAddress, idRole) Values(?,?,?,?,?,(select idRole from roles where title = ?))";
        try{
            con = DriverManager.getConnection(URL, user, password);
            ps = con.prepareStatement(sql);
            ps.setString(1,first_name);
            ps.setString(2,middle_name);
            ps.setString(3,last_name);
            ps.setString(4,contact);
            ps.setInt(5,address);
            ps.setString(6,role);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    public static int checkAddress(String street, String purok, String barangay, String city, String province){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select idAddress from address where street = ? AND purok = ? AND barangay = ? AND city = ? AND province = ?";
        int idAddress = 0;
        try{
            con = DriverManager.getConnection(URL,user,password);
            ps = con.prepareStatement(sql);
            ps.setString(1,street);
            ps.setString(2,purok);
            ps.setString(3,barangay);
            ps.setString(4,city);
            ps.setString(5,province);
            rs = ps.executeQuery();
            if(rs.next()) {
                idAddress = rs.getInt(1);
            }
            else{
                idAddress = DBConnector.insertAddress(con,street,purok,barangay,city,province);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return idAddress;
    }

    public static int insertAddress(Connection con, String street, String purok, String barangay, String city, String province){
        PreparedStatement ps = null;
        String sql = "insert into Address(street, purok, barangay, city, province) values(?,?,?,?,?)";
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,street);
            ps.setString(2,purok);
            ps.setString(3,barangay);
            ps.setString(4,city);
            ps.setString(5,province);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return DBConnector.checkAddress(street, purok, barangay, city, province);
    }

    public static String getUserName(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select concat(first_name,' ',last_name) from employee where username = ?";
        String name = null;
        try{
            con = DriverManager.getConnection(URL,user,password);
            ps = con.prepareStatement(sql);
            ps.setString(1,user);
            rs = ps.executeQuery();
            rs.next();
            name = rs.getString(1);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            try{
                if(con != null && !con.isClosed()){
                    if(!con.getAutoCommit()){
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    con.close();
                    con = null;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return name;

    }

    public static LogInType checkUser(String user_, String password_){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LogInType type = null;
        String sql = "select idManager from employee inner join manager on employee.idEmployee = manager.idEmployee where username = ?";
        try{
            con = DriverManager.getConnection(URL,user_,password_);
            ps = con.prepareStatement(sql);
            ps.setString(1,user_);
            rs = ps.executeQuery();
            if (rs.next()){
                type = LogInType.Manager;
            }else{
                type = LogInType.Employee;
            }
        }catch(SQLException e){
            e.printStackTrace();
            type = LogInType.Invalid;
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        user = user_;
        password = password_;
        return type;
    }

    public static void logOut() {
        user = "";
        password = "";
    }
}