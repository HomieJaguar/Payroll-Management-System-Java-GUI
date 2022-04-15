import TextPrompt.TextPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.util.Objects;

public class Update_Dept extends JFrame
{
    JLabel title,DID,depname;
    JTextField depField;
    JComboBox<String> did;
    String dname;
   public Update_Dept()
   {
       JFrame F = new JFrame();
       F.setTitle("Update Department");
       F.setDefaultCloseOperation(EXIT_ON_CLOSE);
       F.setResizable(false);

       title = new JLabel("Update Department");
       title.setSize(500, 40);
       title.setFont(new Font("Arial", Font.BOLD, 35));
       title.setLocation(370, 50);
       title.setForeground(Color.WHITE);
       title.setVisible(true);

       DID = new JLabel("Department ID");
       DID.setSize(150 ,45);
       DID.setFont(new Font("Arial", Font.BOLD, 16));
       DID.setLocation(50,245);
       DID.setForeground(Color.WHITE);
       DID.setVisible(true);

       did = new JComboBox<String>();
       did.setSize(200,30);
       did.addItem("SELECT");
       did.setEditable(false);
       did.setFont(new Font("Arial", Font.BOLD, 15));
       did.setLocation(200,250);
       did.setVisible(true);

       depname = new JLabel("Department Name");
       depname.setSize(200 ,45);
       depname.setFont(new Font("Arial", Font.BOLD, 16));
       depname.setLocation(470 , 245);
       depname.setForeground(Color.WHITE);
       depname.setVisible(true);


       depField =new JTextField();
       depField.setSize(200 ,30);
       depField.setFont(new Font("Arial", Font.BOLD, 15));
       depField.setLocation(630 ,250);
       depField.setVisible(true);

       new TextPrompt("Update department Name",depField);

       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
           PreparedStatement s1 = cn.prepareStatement("SELECT Depart_id FROM department ");
           ResultSet r1 = s1.executeQuery();
           while (r1.next()) {
               did.addItem(r1.getString("Depart_id"));
           }
           PreparedStatement s2 = cn.prepareStatement("SELECT Depart_name from department ");
           ResultSet r2 = s2.executeQuery();
           while (r2.next()){

           }

       } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
       }

       

       did.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               if(!(did.getSelectedIndex()==0))
                   try {
                       Class.forName("com.mysql.cj.jdbc.Driver");
                       Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                       Statement stmt = cn.createStatement();
                       String qry = "SELECT Depart_name FROM department where Depart_id=" + Objects.requireNonNull(did.getSelectedItem());
                       ResultSet rs = stmt.executeQuery(qry);
                       if (rs.next()) {
                           depField.setText(rs.getString("Depart_name"));
                       }

                   } catch (ClassNotFoundException | SQLException classNotFoundException) {
                       classNotFoundException.printStackTrace();
                   }
           }
       });
       ImageIcon background=new ImageIcon("images/reg_back.jpg");
       Image img=background.getImage();
       Image temp=img.getScaledInstance(1050,850,Image.SCALE_SMOOTH);
       background=new ImageIcon(temp);
       JLabel backs=new JLabel(background);
       backs.setLayout(null);
       backs.setBounds(0,0,900,600);

       JButton submit = new JButton("Submit");
       submit.setFont(new Font("Arial", Font.BOLD, 16));
       submit.setSize(100, 30);
       submit.setBackground(new Color(50,205,50));
       submit.setForeground(new Color(255,255,255));
       submit.setLocation(450, 400);
       F.add(submit);

       submit.addActionListener(e -> {

           if (e.getSource() == submit) {
               dname = depField.getText();
               if (dname.isEmpty()) {
                   JOptionPane.showMessageDialog(null, "Name field empty", "Error", JOptionPane.INFORMATION_MESSAGE);
               } else {
                   int confirm = JOptionPane.showOptionDialog(F,
                           "Are You Sure want to Update?",
                           "Update Confirmation", JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE, null, null, null);
                   if (confirm == JOptionPane.YES_OPTION) {
                       try {
                           Class.forName("com.mysql.cj.jdbc.Driver");
                           Connection con = DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                           PreparedStatement pst = con.prepareStatement("UPDATE department SET Depart_name=? Where Depart_id=" + Objects.requireNonNull(did.getSelectedItem()).toString());
                           pst.setString(1, dname);
                           pst.executeUpdate();
                           JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                       } catch (ClassNotFoundException | SQLException classNotFoundException) {
                           JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.INFORMATION_MESSAGE);
                           classNotFoundException.printStackTrace();
                       }
                   }
               }
           }
       });

       JButton back= new JButton("Back");
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
                   F.setVisible(false);
               }
           }});

       F.add(back);
       F.add(title);
       F.add(DID);
       F.add(depname);
       F.add(depField);
       F.add(did);
       F.setSize(900, 600);
       F.setLocation(350,50);
       F.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       F.setLayout(null);
       F.setVisible(true);
       F.add(backs);
   }



    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        }catch(Exception e) {
            e.printStackTrace();
        }
        new Update_Dept();
    }

}

