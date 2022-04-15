import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;

class emp_sal extends JFrame implements KeyListener,MouseListener,ActionListener {
    Random rand;
    JFrame j;
    String labels[] = {"Employee ID", "Employee Name", "Account Number",
            "Overtime", "Department Name", "Total Pay"};
    JLabel l[] = new JLabel[6];
    JTextField t[] = new JTextField[5];
    JTextField id;
    JLabel main, y, m, i;
    JComboBox year, month;
    String columns[] = {"pay_no", "emp_id", "name", "accno", "year", "month", "absence", "HRA", "Basic", "overtime",
            "Current_Department_ID", "season_bonus", "other_bonus", "medi_allow", "house_allow", "Actual_pay"};
    String years[] = {"Select", "2000", "2001", "2002", "2003", "2004",
            "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012", "2013", "2014", "2015", "2016",
            "2017", "2018", "2019", "2020", "2021"};
    String months[] = {"Select", "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "Decenber"};
    JTextArea area;
    int count,ab, over, sebonus, otbonus, Dep_id;
    JButton gen, receipt;
    Border border;

    emp_sal() throws SQLException, ClassNotFoundException {
        ImageIcon icon = new ImageIcon("images/reg_back.jpg");
        Image img = icon.getImage();
        Image temp = img.getScaledInstance(1050, 550, Image.SCALE_SMOOTH);
        i = new JLabel(new ImageIcon(temp));
        i.setVisible(true);

        j = new JFrame();
        j.setLocation(350,50);
        j.setContentPane(i);
        j.getContentPane();

        rand = new Random();

        main = new JLabel("Employee Salary");
        main.setSize(600, 50);
        main.setFont(new Font("Arial", Font.BOLD, 30));
        main.setForeground(Color.WHITE);
        main.setLocation(250, 0);
        main.setVisible(true);

        for (int i = 0; i < 6; i++) {
            l[i] = new JLabel(labels[i]);
            l[i].setSize(200, 30);
            l[i].setForeground(Color.WHITE);
            l[i].setLocation(200, 46 * (i + 1));
            l[i].setVisible(true);
            j.add(l[i]);
        }

        for (int k = 0; k < 5; k++) {
            t[k] = new JTextField();
            t[k].setSize(200, 25);
            t[k].setLocation(320, (int) (46 * ((k + 1) + 1.25)));
            t[k].setVisible(true);
            j.add(t[k]);
        }

        id = new JTextField();
        id.setSize(200, 25);
        id.addKeyListener(this);
        id.setLocation(320, 46);
        id.setVisible(true);
        //count = count + 1;

        year = new JComboBox(years);
        year.setSize(200, 25);
        year.setFont(new Font("Arial", Font.BOLD, 15));
        year.setLocation(320, 338);
        year.setVisible(true);

        month = new JComboBox(months);
        month.setSize(200, 25);
        month.setFont(new Font("Arial", Font.BOLD, 15));
        month.setLocation(320, 380);
        month.setVisible(true);

        gen = new JButton("Generate");
        gen.setSize(200, 50);
        gen.addMouseListener(this);
        gen.setLocation(400, 440);
        gen.setVisible(true);

        receipt = new JButton("Print");
        receipt.setSize(200, 40);
        receipt.addActionListener(this::actionPerformed);
        receipt.setLocation(750, 470);
        receipt.setVisible(true);

        area = new JTextArea();
        area.setSize(350, 450);
        area.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        area.setLocation(650, 20);
        area.setEditable(false);
        area.setVisible(true);

        y = new JLabel("Year");
        y.setSize(200, 30);
        y.setForeground(Color.WHITE);
        y.setLocation(210, 336);
        y.setVisible(true);

        m = new JLabel("Month");
        m.setSize(200, 30);
        m.setForeground(Color.WHITE);
        m.setLocation(210, 378);
        m.setVisible(true);

        border = BorderFactory.createLineBorder(Color.BLACK);
        area.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));


        j.add(main);
        j.add(m);
        j.add(y);
        j.add(gen);
        j.add(receipt);
        j.add(area);
        j.add(id);
        j.add(year);
        j.add(month);

        j.setSize(1050, 550);
        j.setLayout(null);
        j.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            int upper = 25;
            int lower = 10;
            int sbonus = 20000;
            int obonus = 10000;
            ab = rand.nextInt(upper);
            over = rand.nextInt(lower);
            sebonus = rand.nextInt(sbonus);
            otbonus = rand.nextInt(obonus);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
            PreparedStatement smt = con.prepareStatement("Select * from employee where Employee_id=?;");
            smt.setString(1, id.getText());
            ResultSet r = smt.executeQuery();
            while (r.next()) {
                t[0].setText(r.getString("Name"));
                t[1].setText(r.getString("b_accno"));
                t[2].setText(String.valueOf(over));
                t[3].setText(r.getString("Depart_id"));
                t[4].setText(r.getString("salary"));
                t[0].setEditable(false);
                t[1].setEditable(false);
                t[3].setEditable(false);
                t[4].setEditable(false);
            }

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

        } catch (Exception e) {
            e.printStackTrace();
        }
        new emp_sal();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");

            PreparedStatement smt = con.prepareStatement("Select * from employee where Employee_id=?;");
            smt.setString(1, id.getText());
            ResultSet r = smt.executeQuery();
            while (r.next()) {
                Dep_id = Integer.parseInt(r.getString("Depart_id"));
            }

            PreparedStatement stat = con.prepareStatement("Select * From payment where pay_no =?");
            stat.setInt(1, count);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                area.setText("*************** PaySlip ***************\n"+/*"Payment No - " + rs.getString("pay_no") + "\n" +*/
                        "Employee ID - " + rs.getString("emp_id") + "\n" +
                        "Name - " + rs.getString("name") + "\n" +
                        "Account No - " + rs.getString("accno") + "\n" +
                        "Year - " + rs.getString("year") + "\n" +
                        "Month - " + rs.getString("month") + "\n" +
                        "Absence - " + rs.getString("absence") + "\n" +
                        "HRA - " + rs.getString("HRA") + "\n" +
                        "Basic Salary - " + rs.getString("Basic") + "\n" +
                        "Overtime - " + rs.getString("overtime") + "\n" +
                        "Department ID - " + rs.getString("Current_Department_ID") + "\n" +
                        "Season Bonus - " + rs.getString("season_bonus") + "\n" +
                        "Other Bonus - " + rs.getString("other_bonus") + "\n" +
                        "Medical Allowance - " + rs.getString("medi_allow") + "\n" +
                        "House Allowance - " + rs.getString("house_allow") + "\n" +
                        "In Hand Pay - " + rs.getString("total_pay") + "\n");

            }
            area.setFont(new Font("Arial", Font.BOLD, 20));

        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            count = rand.nextInt(100);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");

            PreparedStatement smt = con.prepareStatement("Select * from employee where Employee_id=?;");
            smt.setString(1, id.getText());
            ResultSet r = smt.executeQuery();
            while (r.next()) {
                Dep_id = Integer.parseInt(r.getString("Depart_id"));
            }
            String query = "Insert into payment values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement st = con.prepareStatement(query);
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formattedDate = myDateObj.format(myFormatObj);

            st.setString(1, formattedDate);
            st.setFloat(2, count);
            st.setString(3, id.getText());
            st.setString(4, t[0].getText());
            st.setString(5, t[1].getText());
            st.setString(6, (String) year.getSelectedItem());
            st.setString(7, (String) month.getSelectedItem());
            st.setInt(8, ab);
            st.setFloat(9, (float) (Float.parseFloat(t[4].getText()) * 0.1));
            st.setFloat(10, Float.parseFloat(t[4].getText()) / 2);
            st.setFloat(11, over);
            st.setInt(12,Dep_id);
            st.setFloat(13, sebonus);
            st.setFloat(14, otbonus);
            st.setFloat(15, (float) ((Float.parseFloat(t[4].getText()) * 0.5) * 0.06));
            st.setFloat(16, (float) ((Float.parseFloat(t[4].getText()) * 0.5) * 0.01));
            st.setFloat(17, (float) (Float.parseFloat(t[4].getText()) - ((Float.parseFloat(t[4].getText()) * 0.1) + (Float.parseFloat(t[4].getText()) * 0.5) + ((Float.parseFloat(t[4].getText()) * 0.5) * 0.06) + ((Float.parseFloat(t[4].getText()) * 0.5) * 0.01)) + sebonus + otbonus));
            st.executeUpdate();

        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            area.print();
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }
}