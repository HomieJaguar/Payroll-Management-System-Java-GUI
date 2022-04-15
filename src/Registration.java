import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;
import java.sql.Connection;
import java.util.Objects;

class Registration  extends JFrame implements ActionListener
{
    private Container c;
    String[] gend={"Male","Female","Others"};
    private JLabel head;
    private JLabel name;
    private JTextField tname;
    private JLabel mno;
    private JTextField tmno;
    private JLabel gender;
    JButton back;
    JComboBox<String> genderCombox;
    private JLabel dob;
    private JComboBox<String> date;
    private JComboBox<String> month;
    private JComboBox<String> year;
    private JLabel add;
    private JTextArea tadd;
    private JLabel pass;
    private JPasswordField ptext;
    private JPasswordField conf_text;
    private JLabel pconf;
    private JLabel user;
    private JTextArea usrnam;
    private JButton sub;
    private JButton reset;



    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "16",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12" };
    private String years[]
            = {
            "1975", "1976", "1977", "1978",
            "1979", "1980", "1981", "1982",
            "1983", "1984", "1985", "1986",
            "1987", "1988", "1989", "1990",
            "1991", "1992", "1993", "1994",
            "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019","2020","2021" };
    public Registration () throws PrinterException {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);



        c = getContentPane();
        c.setLayout(null);
        c.setSize(1050,850);
        c.setLayout(null);
        c.setVisible(true);

        back= new JButton("< Back");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setSize(100, 30);
        back.setLocation(20, 30);
        back.setBackground(new Color(220,20,60));
        back.setForeground(new Color(255,255,255));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    dispose();
                }
            }
        });
        c.add(back);


        head = new JLabel("Registration Form");
        head.setFont(new Font("Arial", Font.BOLD, 30));
        head.setSize(300, 30);
        head.setLocation(300, 30);
        head.setForeground(Color.WHITE);
        c.add(head);


        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.BOLD, 14));
        name.setSize(100, 22);
        name.setLocation(100, 100);
        name.setForeground(Color.white);
        c.add(name);


        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.BOLD, 13));
        tname.setSize(230, 30);
        tname.setLocation(250, 100);
        c.add(tname);


        mno = new JLabel("Mobile (+91)");
        mno.setFont(new Font("Arial", Font.BOLD, 14));
        mno.setSize(100, 20);
        mno.setLocation(100, 150);
        mno.setForeground(Color.white);
        c.add(mno);

        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.BOLD, 13));
        tmno.setSize(230, 30);
        tmno.setLocation(250, 150);
        c.add(tmno);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.BOLD, 13));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        gender.setForeground(Color.white);
        c.add(gender);

        genderCombox =new JComboBox<>(gend);
        genderCombox.setFont(new Font("Arial", Font.BOLD, 14));
        genderCombox.setSize(85, 25);
        genderCombox.setLocation(250, 200);
        c.add(genderCombox);


        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.BOLD, 14));
        dob.setSize(100, 20);
        dob.setLocation(100, 250);
        dob.setForeground(Color.white);
        c.add(dob);

        date = new JComboBox<>(dates);
        date.setFont(new Font("Arial", Font.BOLD, 14));
        date.setSize(60, 25);
        date.setLocation(250, 250);
        c.add(date);

        month = new JComboBox<>(months);
        month.setFont(new Font("Arial", Font.BOLD, 14));
        month.setSize(60, 25);
        month.setLocation(300, 250);
        c.add(month);

        year = new JComboBox<>(years);
        year.setFont(new Font("Arial", Font.BOLD, 14));
        year.setSize(60, 25);
        year.setLocation(360, 250);
        c.add(year);

        add = new JLabel("Address (Optional) ");
        add.setFont(new Font("Arial", Font.BOLD, 14));
        add.setSize(150, 20);
        add.setLocation(100, 300);
        add.setForeground(Color.white);
        c.add(add);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.BOLD, 13));
        tadd.setSize(230, 50);
        tadd.setLocation(250, 300);
        tadd.setLineWrap(true);
        c.add(tadd);

        user = new JLabel("Username");
        user.setFont(new Font("Arial", Font.BOLD, 14));
        user.setSize(100, 20);
        user.setLocation(100, 375);
        user.setForeground(Color.WHITE);
        c.add(user);




        usrnam = new JTextArea();
        usrnam.setFont(new Font("Arial", Font.BOLD, 13));
        usrnam.setSize(230, 27);
        usrnam.setLocation(250, 375);
        usrnam.setLineWrap(true);
        c.add(usrnam);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.BOLD, 14));
        pass.setSize(100, 20);
        pass.setLocation(100, 425);
        pass.setForeground(Color.WHITE);
        c.add(pass);

        ptext = new JPasswordField();
        ptext.setFont(new Font("Arial", Font.BOLD, 14));
        ptext.setSize(230, 27);
        ptext.setLocation(250, 425);
        c.add(ptext);

        pconf = new JLabel("Confirm Password");
        pconf.setFont(new Font("Arial", Font.BOLD, 14));
        pconf.setSize(200, 20);
        pconf.setLocation(100, 475);
        pconf.setForeground(Color.WHITE);
        c.add(pconf);

        conf_text = new JPasswordField();
        conf_text.setFont(new Font("Arial", Font.BOLD, 14));
        conf_text.setSize(230, 27);
        conf_text.setLocation(250, 475);
        c.add(conf_text);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.BOLD, 16));
        sub.setSize(100, 30);
        sub.setBackground(new Color(50,205,50));
        sub.setForeground(new Color(255,255,255));
        sub.setLocation(150, 525);
        c.add(sub);



        JTextArea o= new JTextArea();
        o.setSize(300,400);
        o.setLocation(550,100);
        o.setLineWrap(true);
        o.setEditable(false);
        o.setFont(new Font("Arial", Font.BOLD, 16));
        o.setVisible(false);

        c.add(o);


        ImageIcon background=new ImageIcon("images/reg_back.jpg");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(1050,850,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel backs=new JLabel(background);
        backs.setLayout(null);
        backs.setBounds(0,0,900,600);
        c.add(backs);





        setVisible(true);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==sub) {
                    String name = tname.getText();
                    String phone = tmno.getText();
                    String Gender = Objects.requireNonNull(genderCombox.getSelectedItem()).toString();
                    String address = tadd.getText();
                    String username = usrnam.getText();
                    String password = ptext.getText();
                    String confirm_password = conf_text.getText();
                    String Date = Objects.requireNonNull(date.getSelectedItem()).toString();
                    String Month = Objects.requireNonNull(month.getSelectedItem()).toString();
                    String Year = Objects.requireNonNull(year.getSelectedItem()).toString();
                    String da = Year + "/" + Month + "/" + Date;

                    if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(null,

                                "Username field is empty!");
                    } else if (password.isEmpty()) {
                        JOptionPane.showMessageDialog(null,

                                "Password field is empty!");
                    } else if (confirm_password.isEmpty()) {
                        JOptionPane.showMessageDialog(null,

                                " Confirm Password field is empty!");
                    } else if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null,

                                " Please Enter Name!");
                    } else if (phone.isEmpty()) {
                        JOptionPane.showMessageDialog(null,

                                " Please Enter phone number!");
                    }
                    else{

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                            PreparedStatement ps = con.prepareStatement("insert into users values(id,?,?,?,?,?,?,?)");

                            ps.setString(1, name);
                            ps.setString(2, phone);
                            ps.setString(3, Gender);
                            ps.setString(4, address);
                            ps.setString(5, username);
                            ps.setString(6, password);
                            ps.setString(7, da);

                            if (password.equalsIgnoreCase(confirm_password) && phone.length()<=10) {

                                ps.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                                if(address.isEmpty()) {
                                    String welcome_message = "**************Registered Data*************" + "\n" + "\n";
                                    String oname = "   Name: " + name + "\n";
                                    String omob = "   Mobile: " + phone + "\n";
                                    String ogender = "   Gender: " + Gender + "\n";
                                    String ousername = "   Username: " + username + "\n";
                                    String odob = "   Date: " + da + "\n";
                                    String opassword = "   Password: " + password + "\n";

                                    o.setText(welcome_message + oname + omob + ogender + ousername + odob + opassword);
                                    o.setVisible(true);
                                    o.setEditable(false);
                                }
                                else{
                                    String welcome_message = "**************Registered Data*************" + "\n" + "\n";
                                    String oname = "   Name: " + name + "\n";
                                    String omob = "   Mobile: " + phone + "\n";
                                    String ogender = "   Gender: " + Gender + "\n";
                                    String oaddress = "   Address: " + address + "\n";
                                    String ousername = "   Username: " + username + "\n";
                                    String odob = "   Date: " + da + "\n";
                                    String opassword = "   Password: " + password + "\n";

                                    o.setText(welcome_message + oname + omob + ogender+ oaddress + ousername + odob + opassword);
                                    o.setVisible(true);
                                    o.setEditable(false);

                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "password did not match or Mobile number contains digits more than 10");
                            }

                        } catch (ClassNotFoundException | SQLException classNotFoundException) {
                            JOptionPane.showMessageDialog(null, classNotFoundException.getMessage());

                        }

                    }
                }
            }
        });
        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.BOLD, 16));
        reset.setSize(100, 30);
        reset.setLocation(270, 525);
        reset.setBackground(new Color(255,0,0));
        reset.setForeground(new Color(255,255,255));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datesa=date.getSelectedItem().toString();
                tname.setText("");
                tmno.setText("");
                tadd.setText("");

                usrnam.setText("");
                ptext.setText("");
                conf_text.setText("");
                o.setText("");
            }
        });
        reset.setVisible(true);
        c.add(reset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) throws Exception
    {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

        } catch (Exception e) {
            e.printStackTrace();
        }
        new Registration ();
    }
}