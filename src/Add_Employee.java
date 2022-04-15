import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.util.Objects;

class Add_Employee extends JFrame implements ActionListener{
    JLabel name;
    JTextField nameField;
    JLabel eid;
    JTextField eidField;
    JLabel email ;
    JTextField emailField;
    JLabel phone ;
    JTextField phoneField;
    JLabel gender;
    JComboBox<? extends String> genderField;
    JLabel dob;
    JComboBox<? extends String> dob_date;
    JComboBox<? extends String> dob_month;
    JComboBox<? extends String> dob_year;
    JLabel doj;
    JComboBox<? extends String> doj_date;
    JComboBox<? extends String> doj_month;
    JComboBox<? extends String> doj_year;
    JLabel job_title;
    JTextField jtitle;
    JLabel add;
    JTextArea addField;
    JLabel deptid;
    JComboBox<String> dept;
    ImageIcon background;
    JLabel heading;
    String[] dptlist = {"Select","104","105","106","107","108"};
    int regn = 1000;
    String did = "108";

    Add_Employee(){
        JFrame fr = new JFrame();
        fr.setTitle("Employee Details");
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fr.setResizable(false);

        heading = new JLabel("Employee Details");
        heading.setSize(500, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 35));
        heading.setLocation(370, 50);
        heading.setForeground(Color.WHITE);
        heading.setVisible(true);

        name = new JLabel("Name");
        name.setSize(100 ,45);
        name.setFont(new Font("Arial", Font.BOLD, 16));
        name.setLocation(100 , 150);
        name.setForeground(Color.WHITE);
        name.setVisible(true);

        nameField =new JTextField();
        nameField.setSize(200 ,30);
        nameField.setFont(new Font("Arial", Font.BOLD, 15));
        nameField.setLocation(200 ,160);
        nameField.setVisible(true);

        eid = new JLabel("Emp ID");
        eid.setSize(100 ,45);
        eid.setFont(new Font("Arial", Font.BOLD, 16));
        eid.setLocation(500,150);
        eid.setForeground(Color.WHITE);
        eid.setVisible(true);

        dept = new JComboBox<>();
        dept.setSize(200,30);
        dept.addItem("SELECT");
        dept.setFont(new Font("Arial", Font.BOLD, 15));
        dept.setLocation(600, 360);
        dept.setVisible(true);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
            PreparedStatement s1 = cn.prepareStatement("SELECT Employee_id FROM employee ORDER BY Employee_id DESC LIMIT 1");
            ResultSet r1 = s1.executeQuery();
            if(r1.next() == true)
                regn = r1.getInt(1) +1;
            PreparedStatement s2 = cn.prepareStatement("SELECT Depart_name from department ");
            ResultSet r2 = s2.executeQuery();
            while (r2.next())
                dept.addItem(r2.getString(1));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        eidField = new JTextField();
        eidField.setSize(200,30);
        eidField.setText(String.valueOf(regn));
        eidField.setEditable(false);
        eidField.setFont(new Font("Arial", Font.BOLD, 15));
        eidField.setLocation(600,160);
        eidField.setVisible(true);

        email = new JLabel("Email");
        email.setSize(100,45);
        email.setFont(new Font("Arial", Font.BOLD, 16));
        email.setLocation(100,200);
        email.setVisible(true);
        email.setForeground(Color.WHITE);

        emailField = new JTextField();
        emailField.setSize(200,30);
        emailField.setFont(new Font("Arial", Font.BOLD, 15));
        emailField.setLocation(200,210);
        emailField.setVisible(true);

        phone = new JLabel("Phone (+91)");
        phone.setLocation(500,200);
        phone.setVisible(true);
        phone.setFont(new Font("Arial", Font.BOLD, 16));
        phone.setForeground(Color.WHITE);
        phone.setSize(100,45);

        phoneField = new JTextField();
        phoneField.setSize(200,30);
        phoneField.setFont(new Font("Arial", Font.BOLD, 15));
        phoneField.setLocation(600,210);
        phoneField.setVisible(true);

        gender = new JLabel("Gender");
        gender.setLocation(100,250);
        gender.setFont(new Font("Arial", Font.BOLD, 16));
        gender.setVisible(true);
        gender.setForeground(Color.WHITE);
        gender.setSize(100,45);

        String[] gndr = {"Select", "Male", "Female"};
        genderField = new JComboBox<>(gndr);
        genderField.setFont(new Font("Arial", Font.BOLD, 16));
        genderField.setVisible(true);
        genderField.setSize(200,30);
        genderField.setLocation(200,260);

        job_title = new JLabel("Job title");
        job_title.setLocation(500,250);
        job_title.setFont(new Font("Arial", Font.BOLD, 16));
        job_title.setSize(100,45);
        job_title.setVisible(true);
        job_title.setForeground(Color.WHITE);

        jtitle = new JTextField();
        jtitle.setLocation(600,260);
        jtitle.setFont(new Font("Arial", Font.BOLD, 15));
        jtitle.setFont(new Font("Arial", Font.BOLD, 15));
        jtitle.setSize(200,30);
        jtitle.setVisible(true);

        dob = new JLabel("DOB");
        dob.setLocation(100,300);
        dob.setFont(new Font("Arial", Font.BOLD, 16));
        dob.setSize(100,45);
        dob.setVisible(true);
        dob.setForeground(Color.WHITE);

        String[] dates = {"DD", "01", "02", "03", "04", "05",
                "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};
        dob_date = new JComboBox<>(dates);
        dob_date.setFont(new Font("Arial", Font.BOLD, 16));
        dob_date.setSize(60, 30);
        dob_date.setLocation(200, 310);
        dob_date.setVisible(true);

        String[] months = {"MM", "01", "02", "03", "04",
                "05", "06", "07", "08",
                "09", "10", "11", "12"};
        dob_month = new JComboBox<>(months);
        dob_month.setFont(new Font("Arial", Font.BOLD, 16));
        dob_month.setSize(70,30);
        dob_month.setVisible(true);
        dob_month.setLocation(260,310);

        String[] yrs = {"YYYY", "1980", "1981", "1982", "1983",
                "1984", "1985", "1986", "1987",
                "1988", "1989", "1990", "1991",
                "1992", "1993", "1994", "1995",
                "1996", "1997", "1998", "1999",
                "2000", "2001", "2002",
                "2003", "2004"};
        dob_year = new JComboBox<>(yrs);
        dob_year.setFont(new Font("Arial", Font.BOLD, 16));
        dob_year.setSize(70, 30);
        dob_year.setVisible(true);
        dob_year.setLocation(330,310);

        doj = new JLabel("DOJ");
        doj.setFont(new Font("Arial", Font.BOLD, 16));
        doj.setLocation(500,300);
        doj.setVisible(true);
        doj.setForeground(Color.WHITE);
        doj.setSize(100,45);

        doj_date = new JComboBox<>(dates);
        doj_date.setFont(new Font("Arial", Font.BOLD, 16));
        doj_date.setSize(60, 30);
        doj_date.setLocation(600, 310);
        doj_date.setVisible(true);

        doj_month = new JComboBox<>(months);
        doj_month.setFont(new Font("Arial", Font.BOLD, 16));
        doj_month.setSize(70,30);
        doj_month.setVisible(true);
        doj_month.setLocation(660,310);

        String[] years = {"YYYY", "1998", "1999", "2000", "2001", "2002",
                "2003", "2004", "2005", "2006",
                "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014",
                "2015", "2016", "2017", "2018",
                "2019", "2020", "2021"};
        doj_year = new JComboBox<>(years);
        doj_year.setFont(new Font("Arial", Font.BOLD, 16));
        doj_year.setSize(70, 30);
        doj_year.setVisible(true);
        doj_year.setLocation(730,310);

        add = new JLabel("Address");
        add.setFont(new Font("Arial", Font.BOLD, 16));
        add.setVisible(true);
        add.setLocation(100,350);
        add.setSize(100,45);
        add.setForeground(Color.WHITE);

        addField =new JTextArea();
        addField.setVisible(true);
        addField.setFont(new Font("Arial", Font.BOLD, 15));
        addField.setLocation(200,360);
        addField.setLineWrap(true);
        addField.setSize(200,70);

        deptid= new JLabel("Dept. Name");
        deptid.setFont(new Font("Arial", Font.BOLD, 16));
        deptid.setForeground(Color.WHITE);
        deptid.setSize(100,45);
        deptid.setVisible(true);
        deptid.setLocation(500,350);


        JLabel sal = new JLabel("Salary");
        sal.setSize(200,30);
        sal.setFont(new Font("Arial", Font.BOLD, 15));
        sal.setLocation(500,400);
        sal.setForeground(Color.WHITE);
        sal.setVisible(true);
        fr.add(sal);

        JTextField salary = new JTextField();
        salary.setSize(200,30);
        salary.setFont(new Font("Arial", Font.BOLD, 15));
        salary.setLocation(600,410);
        salary.setVisible(true);
        fr.add(salary);



        JButton sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.BOLD, 16));
        sub.setSize(100, 30);
        sub.setBackground(new Color(50,205,50));
        sub.setForeground(new Color(255,255,255));
        sub.setLocation(300, 500);
        fr.add(sub);

        JButton back= new JButton("<Back");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setSize(100, 20);
        back.setLocation(20, 30);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setBorder(null);
        back.setFocusable(false);
        back.setForeground(new Color(255,255,255));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    fr.dispose();
                    new dashboard();
                }
            }
        });

        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.BOLD, 16));
        reset.setSize(100, 30);
        reset.setLocation(550,500);
        reset.setBackground(new Color(255,0,0));
        reset.setForeground(new Color(255,255,255));
        reset.addActionListener(e -> {
            nameField.setText("");
            phoneField.setText("");
            dob_month.setSelectedIndex(0);
            dob_year.setSelectedIndex(0);
            dob_date.setSelectedIndex(0);
            doj_year.setSelectedIndex(0);
            doj_month.setSelectedIndex(0);
            doj_date.setSelectedIndex(0);
            jtitle.setText("");
            emailField.setText("");
            addField.setText("");
            dept.setSelectedIndex(0);
            genderField.setSelectedIndex(0);

        });
        fr.add(reset);

        sub.addActionListener(e -> {
            try{Class.forName("com.mysql.cj.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                PreparedStatement smt = cn.prepareStatement("SELECT Depart_id from department where Depart_name = ?");
                String depid = Objects.requireNonNull(dept.getSelectedItem()).toString();
                smt.setString(1,depid);
                ResultSet r3 = smt.executeQuery();
                r3.next();
                did = r3.getString(1);

            } catch (ClassNotFoundException | SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            if(e.getSource() == sub) {
                String name = nameField.getText();
                String email = emailField.getText();
                String gender = Objects.requireNonNull(genderField.getSelectedItem()).toString();
                String address = addField.getText();
                String phone = phoneField.getText();
                String job = jtitle.getText();
                //String eid = eidField.getText();
                //String accno = acc.getText();
                String depid = Objects.requireNonNull(dept.getSelectedItem()).toString();
                String bdate = Objects.requireNonNull(dob_date.getSelectedItem()).toString();
                String bmonth = Objects.requireNonNull(dob_month.getSelectedItem()).toString();
                String byear = Objects.requireNonNull(dob_year.getSelectedItem()).toString();
                String dob = byear + "/" + bmonth + "/" + bdate;
                String jdate = Objects.requireNonNull(doj_date.getSelectedItem()).toString();
                String jmonth = Objects.requireNonNull(doj_month.getSelectedItem()).toString();
                String jyear = Objects.requireNonNull(doj_year.getSelectedItem()).toString();
                String doj = jyear + "/" + jmonth + "/" + jdate;
                String slr = salary.getText();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name field empty", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else /*if (eid.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Employee ID", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else*/ if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter email", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Phone no.", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (phone.length() > 10 || phone.length() < 10) {
                    JOptionPane.showMessageDialog(null, "Enter correct phone no.", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (gender == "Select") {
                    JOptionPane.showMessageDialog(null, "Enter Gender", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (job.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Job title", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (depid== "Select") {
                    JOptionPane.showMessageDialog(null, "Select Department ID", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else /*if (accno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter account number", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else */if (bdate == "DD") {
                    JOptionPane.showMessageDialog(null, "Select DOB", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (bmonth == "MM") {
                    JOptionPane.showMessageDialog(null, "Select DOB", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (byear == "YYYY") {
                    JOptionPane.showMessageDialog(null, "Select DOB", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (jdate == "DD") {
                    JOptionPane.showMessageDialog(null, "Select DOJ", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (jmonth == "MM") {
                    JOptionPane.showMessageDialog(null, "Select DOJ", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (jyear == "YYYY") {
                    JOptionPane.showMessageDialog(null, "Select DOJ", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if(address.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter address", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (slr.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter Salary", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                        PreparedStatement pst = con.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?,b_accno)");
                        pst.setString(1, String.valueOf(regn));
                        pst.setString(2, name);
                        pst.setString(3, address);
                        pst.setString(4, phone);
                        pst.setString(5, email);
                        pst.setString(6, doj);
                        pst.setString(7, dob);
                        pst.setString(8, gender);
                        pst.setString(9, slr);
                        pst.setString(10,job);
                        pst.setString(11,did);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                        regn++;
                        eidField.setText(String.valueOf(regn));
                        reset.doClick();

                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.INFORMATION_MESSAGE);
                        classNotFoundException.printStackTrace();
                    }
                }
            }
        });

        background=new ImageIcon("images/image.png");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(1050,850,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel backs=new JLabel(background);
        backs.setLayout(null);
        backs.setBounds(0,0,1000,600);


        fr.add(back);
        fr.add(deptid);
        fr.add(dept);
        fr.add(addField);
        fr.add(add);
        fr.add(heading);
        fr.add(name);
        fr.add(nameField);
        fr.add(eid);
        fr.add(eidField);
        fr.add(email);
        fr.add(emailField);
        fr.add(gender);
        fr.add(genderField);
        fr.add(phone);
        fr.add(phoneField);
        fr.add(job_title);
        fr.add(jtitle);
        fr.add(dob);
        fr.add(dob_month);
        fr.add(dob_date);
        fr.add(dob_year);
        fr.add(doj);
        fr.add(doj_date);
        fr.add(doj_month);
        fr.add(doj_year);
        fr.setSize(1000, 600);
        fr.setLocation(350,50);
        fr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fr.add(backs);
        fr.setLayout(null);
        fr.setVisible(true);

    }
    public static void main(String[] args) {
        new Add_Employee();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}