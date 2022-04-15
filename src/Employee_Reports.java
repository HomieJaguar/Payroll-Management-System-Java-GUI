import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;


class Employee_Reports extends JFrame implements ActionListener {
    JLabel L1, L2;
    JButton Search;
    JTextField TF1;
    JFrame J, f2;
    JPanel panel3;
    JTextArea area2;
    JLabel EID, Name, Address, Phone, Email, DOJ, DOB, Gender, Salary, DID, BACC, Jtitle;
    JTextField  TFNAME, TFADDRESS, TFPHONE, TFEMAIL, TFDOB, TFDOJ, TFGENDER, TFSALARY, TFDID, TFBACC, TFJTITLE;
    JButton Receipt;
    String empid;
    String name;
    String email;
    String phone_no;
    String jobtitle;
    String doj;
    String dob;
    String dept;
    String salary;
    String add;
    String sex;
    String acc;
    String textfiled_text;

    public Employee_Reports() {
        J = new JFrame("EMPLOYEE REPORTS");
        J.setSize(750, 430);
        J.setLayout(null);

        J.setLocation(350,50);

        J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        L1 = new JLabel("EMPLOYEE REPORT", JLabel.CENTER);
        L1.setSize(800, 40);
        L1.setFont(new Font("Arial", Font.BOLD, 35));
        L1.setForeground(Color.WHITE);
        L1.setVisible(true);
        J.add(L1);


        L2 = new JLabel("Employee ID:");
        L2.setBounds(50, 150, 250, 20);
        L2.setFont(new Font("Arial", Font.BOLD, 20));
        L2.setForeground(Color.WHITE);

        TF1 = new JTextField();
        TF1.setBounds(250, 150, 250, 20);

        Search = new JButton("SEARCH");
        Search.setBounds(250, 250, 250, 20);
        Search.setFont(new Font("Arial", Font.BOLD, 14));
        Search.setBackground(Color.RED);
        Search.setForeground(Color.WHITE);
        Search.addActionListener(this);
        J.add(Search);


        Receipt = new JButton("Generate Report");
        Receipt.setBounds(600, 490, 150, 30);
        Receipt.addActionListener(this::actionPerformed);
        Receipt.setBackground(Color.RED);
        Receipt.setForeground(Color.WHITE);
        JButton b2 = new JButton("Reset");
        b2.setBounds(750, 490, 150, 30);
        JButton Print = new JButton("Print");
        Print.setBounds(900, 490, 150, 30);

        JLabel img=new JLabel();
        img.setIcon(new ImageIcon("images/reg_back.jpg"));
        img.setSize(750,430);
        J.add(L2);
        J.add(TF1);
        J.add(img);
        J.setVisible(true);
        J.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void Search() {

        textfiled_text = TF1.getText();
        f2 = new JFrame("REPORTS");
        f2.setSize(1180, 600);
        f2.setLocation(350,50);
        f2.setLayout(null);
        f2.setVisible(true);
        f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        JButton kback = new JButton("Back");
        kback = new JButton("Back");
        kback.setBounds(10, 15, 110, 30);
        kback.setFont(new Font("Arial", Font.BOLD, 14));
        kback.setForeground(Color.WHITE);
        kback.setBackground(new Color(75, 0, 130));
        kback.addActionListener(this);
        kback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(true);
                f2.dispose();
            }
        });

        f2.add(kback);
        f2.add(Receipt);

        panel3 = new JPanel();

        f2.add(panel3);

        L1 = new JLabel("EMPLOYEE REPORT", JLabel.CENTER);
        L1.setSize(800, 40);
        L1.setLocation(300,50);
        L1.setFont(new Font("Arial", Font.BOLD, 35));
        L1.setForeground(Color.BLACK);
        L1.setVisible(true);
        f2.add(L1);


        EID = new JLabel("Employee ID:");
        EID.setBounds(50, 150, 250, 20);
        EID.setFont(new Font("Arial", Font.BOLD, 20));


        Name = new JLabel("Employee Name:");
        Name.setBounds(50, 200, 250, 20);
        Name.setFont(new Font("Arial", Font.BOLD, 20));


        Address = new JLabel("Address:");
        Address.setBounds(50, 250, 250, 20);
        Address.setFont(new Font("Arial", Font.BOLD, 20));


        Phone = new JLabel("Phone No:");
        Phone.setBounds(50, 300, 250, 20);
        Phone.setFont(new Font("Arial", Font.BOLD, 20));


        Email = new JLabel("Email:");
        Email.setBounds(50, 350, 250, 20);
        Email.setFont(new Font("Arial", Font.BOLD, 20));


        DOJ = new JLabel("DOJ:");
        DOJ.setBounds(50, 400, 250, 20);
        DOJ.setFont(new Font("Arial", Font.BOLD, 20));


        DOB = new JLabel("DOB:");
        DOB.setBounds(50, 450, 250, 20);
        DOB.setFont(new Font("Arial", Font.BOLD, 20));


        Gender = new JLabel("Gender:");
        Gender.setBounds(700, 150, 250, 20);
        Gender.setFont(new Font("Arial", Font.BOLD, 20));

        Salary = new JLabel("Job Title:");
        Salary.setBounds(700, 250, 250, 20);
        Salary.setFont(new Font("Arial", Font.BOLD, 20));



        Jtitle = new JLabel("Department ID:");
        Jtitle.setBounds(700, 300, 250, 20);
        Jtitle.setFont(new Font("Arial", Font.BOLD, 20));


        DID = new JLabel("Bank Acc No:");
        DID.setBounds(700, 350, 250, 20);
        DID.setFont(new Font("Arial", Font.BOLD, 20));


        BACC = new JLabel("Salary");
        BACC.setBounds(700, 200, 250, 20);
        BACC.setFont(new Font("Arial", Font.BOLD, 20));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
            PreparedStatement ps = con.prepareStatement("Select Employee_id,Name,Address,Phone_no,Email,Start_date,d_birth,gender,salary,jobtitle,Depart_id,b_accno from employee where Employee_id=? or Name=?");
            ps.setString(1, textfiled_text);
            ps.setString(2, textfiled_text);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empid = rs.getString("Employee_id");
                name = rs.getString("Name");
                email = rs.getString("Email");
                phone_no = rs.getString("Phone_no");
                jobtitle = rs.getString("jobtitle");
                doj = rs.getString("Start_date");
                dob = rs.getString("d_birth");
                dept = rs.getString("Depart_id");
                salary = rs.getString("salary");
                add = rs.getString("Address");
                sex = rs.getString("gender");
                acc = rs.getString("b_accno");


                JTextArea areaemp = new JTextArea();
                areaemp.setText(empid);
                areaemp.setBounds(250, 150, 250, 20);
                f2.add(areaemp);

                area2 = new JTextArea();
                f2.add(area2);

                TFNAME = new JTextField(name);
                TFNAME.setBounds(250, 200, 250, 20);
                TFNAME.setVisible(true);

                TFADDRESS = new JTextField();
                TFADDRESS.setText(add);
                TFADDRESS.setBounds(250, 250, 250, 20);
                TFADDRESS.setVisible(true);


                TFPHONE = new JTextField(phone_no);
                TFPHONE.setBounds(250, 300, 250, 20);

                TFEMAIL = new JTextField(email);
                TFEMAIL.setBounds(250, 350, 250, 20);

                TFDOJ = new JTextField(doj);
                TFDOJ.setBounds(250, 400, 250, 20);

                TFDOB = new JTextField(dob);
                TFDOB.setBounds(250, 450, 250, 20);

                TFGENDER = new JTextField(sex);
                TFGENDER.setBounds(900, 150, 250, 20);

                TFSALARY = new JTextField(salary);
                TFSALARY.setBounds(900, 200, 250, 20);

                TFJTITLE = new JTextField(jobtitle);
                TFJTITLE.setBounds(900, 250, 250, 20);

                TFDID = new JTextField(dept);
                TFDID.setBounds(900, 300, 250, 20);

                TFBACC = new JTextField(acc);
                TFBACC.setBounds(900, 350, 250, 20);

                f2.add(TFNAME);
                f2.add(TFADDRESS);
                f2.add(TFPHONE);
                f2.add(TFEMAIL);
                f2.add(TFDOJ);
                f2.add(TFDOB);
                f2.add(TFGENDER);
                f2.add(TFSALARY);
                f2.add(TFJTITLE);
                f2.add(TFDID);
                f2.add(TFBACC);
            }

            con.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }



        f2.add(EID);
        f2.add(Name);
        f2.add(Address);
        f2.add(Phone);
        f2.add(Email);
        f2.add(DOJ);
        f2.add(DOB);
        f2.add(Gender);
        f2.add(Salary);
        f2.add(Jtitle);
        f2.add(DID);
        f2.add(BACC);




        if (!(textfiled_text.isEmpty())) {

            JButton dback = new JButton("Back");
            dback = new JButton("Back");
            dback.setBounds(10, 15, 110, 30);
            dback.setFont(new Font("Arial", Font.BOLD, 14));
            dback.setForeground(Color.WHITE);
            dback.setBackground(new Color(75, 0, 130));
            dback.addActionListener(this);
            dback.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f2.setVisible(false);
                }
            });
            f2.add(dback);

        } else {
            JOptionPane.showMessageDialog(null, "No Records Found!");
        }



    }
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        }catch(Exception e) {
            e.printStackTrace();
        }
        new Employee_Reports();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Search) {

            Search();
        }
        if (e.getSource() == Receipt) {


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                PreparedStatement ps = conn.prepareStatement("Select Employee_id,Name,Address,Phone_no,Email,Start_date,d_birth,gender,salary,jobtitle,Depart_id,b_accno from employee where Employee_id=? or Name=?");
                ps.setString(1, textfiled_text);
                ps.setString(2, textfiled_text);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    area2.setText(
                            "--------------------------------"
                                    + "-----------EMPLOYEE REPORT---------"
                                    + "--------------------------"
                                    + "--------------------------"
                                    + "-------------------\n");

                    area2.setText(area2.getText() + "EMPLOYEE ID: "+ empid+"\n\n");//1

                    area2.setText(area2.getText() + "EMPLOYEE NAME: " + name+"\n\n");//2

                    area2.setText(area2.getText() + "ADDRESS: " + add+"\n\n");//3

                    area2.setText(area2.getText() + "EMAIL ID: " + email+"\n\n");//4

                    area2.setText(area2.getText()+ "PHONE NO: "+ phone_no+"\n\n");//5

                    area2.setText(area2.getText() + "GENDER: " + sex+"\n\n");//6

                    area2.setText(area2.getText()+ "DATE OF BIRTH: "+dob+"\n\n");//7

                    area2.setText(area2.getText()+ "DATE OF JOINING: "+ doj+"\n\n");//8

                    area2.setText(area2.getText()+ "SALARY: "+ salary + "\n\n");//9

                    area2.setText(area2.getText()+"JOB TITLE: "+jobtitle+"\n\n");//10

                    area2.setText(area2.getText()+ "DEPARTMENT ID: "+dept+"\n\n");//11

                    area2.setText(area2.getText()+ "BANK ACC NO: "+acc+"\n\n");//12
                }
                conn.close();


            }
            catch (Exception exception) {
                exception.printStackTrace();
            }

            try {
                area2.print();
            } catch (Exception ae) {
                System.out.println(ae);
            }
        }

    };
}