import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;

class add_department extends JFrame implements ActionListener{

    JFrame j;
    JLabel l,l1,l2;
    JLabel i;
    JTextField t,t1;
    JButton b1,b2;

    add_department(){

        i = new JLabel(new ImageIcon("images/image.png"));
        i.setVisible(true);

        j = new JFrame();
        j.setContentPane(i);
        j.getContentPane();

        l = new JLabel("Add Department");
        l.setSize(300,40);
        l.setFont(new Font("Arial",Font.BOLD,24));
        l.setForeground(Color.WHITE);
        l.setLocation(250,30);
        l.setVisible(true);

        l1 = new JLabel("Department ID");
        l1.setSize(300,16);
        l1.setFont(new Font("Arial",Font.BOLD,14));
        l1.setForeground(Color.WHITE);
        l1.setLocation(200,100);
        l1.setVisible(true);

        l2 = new JLabel("Department Name");
        l2.setSize(300,16);
        l2.setFont(new Font("Arial",Font.BOLD,14));
        l2.setForeground(Color.WHITE);
        l2.setLocation(200,153);
        l2.setVisible(true);


        t = new JTextField();
        t.setSize(188,25);
        t.setLocation(354,100);
        t.setVisible(true);

        t1 = new JTextField();
        t1.setSize(188,25);
        t1.setLocation(354,150);
        t1.setVisible(true);

        b1 = new JButton("Add Department");
        b1.setSize(150,30);
        b1.setBackground(Color.GREEN);
        b1.addActionListener(this::actionPerformed);
        b1.setLocation(200,220);
        b1.setVisible(true);

        b2 = new JButton("Cancel");
        b2.setSize(150,30);
        b2.setBackground(Color.GREEN);
        b2.addActionListener(this::actionPerformed);
        b2.setLocation(380,220);
        b2.setVisible(true);

        j.add(b1);
        j.add(b2);
        j.add(t);
        j.add(t1);
        j.add(l);
        j.add(l1);
        j.add(l2);

        j.setSize(750,430);
        j.setLocation(350, 50);
        j.setLayout(null);
        j.setVisible(true);

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");

        } catch (Exception e) {
            e.printStackTrace();
        }
        new add_department();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        if (str.equals("Add Department")){
            int id = Integer.parseInt(t.getText());
            String name = t1.getText();
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/paroll","root","");

                PreparedStatement st = con.prepareStatement("Insert into department values(?,?)");
                st.setInt(1,id);
                st.setString(2,name);
                st.executeUpdate();

                JOptionPane.showMessageDialog(null,"Data Inserted");
                dispose();

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if(str.equals("Cancel")){
            t.setText(null);
            t1.setText(null);
        }
    }
}
