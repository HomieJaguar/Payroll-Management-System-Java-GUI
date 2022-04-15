import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Attendance_add extends JFrame implements ActionListener {

    JFrame f;
    JTable table;
    ImageIcon background;
    JComboBox first_half;
    JButton submit,back;
    JLabel Header_s;
    JButton reset;
    JLabel date_Label;
    JTextField date_text;
    JComboBox second_half;
    DefaultTableModel model;

    public Attendance_add() throws HeadlessException {
        f=new JFrame();
        String[] options={"ABSENT","PRESENT"};
        String[] columns ={"empID","Employee_name","1st half","2nd half"};

        submit=new JButton("Submit");
        submit.setBounds(450,600,110,40);
        submit.setFont(new Font("Arial",Font.BOLD,14));
        submit.setBackground(new Color(140, 50, 205));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        f.add(submit);

        back=new JButton("< Back");

        back.setBounds(10,90,110,30);
        back.setFont(new Font("Arial",Font.BOLD,14));
        back.setBackground(new Color(50, 205, 84));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        f.add(back);

        date_Label=new JLabel("Date:");
        date_Label.setBounds(900,90,110,30);
        date_Label.setFont(new Font("Arial",Font.BOLD,14));
        date_Label.setBackground(new Color(50, 205, 84));
        date_Label.setForeground(Color.BLACK);
        f.add(date_Label);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date now =new Date();


        date_text=new JTextField(df.format(now));
        date_text.setBounds(940,90,110,30);
        date_text.setFont(new Font("Arial",Font.BOLD,14));
        date_text.setBackground(new Color(255, 255, 255));
        date_text.setEditable(false);
        date_text.setForeground(Color.BLACK);
        f.add(date_text);


        reset =new JButton("Reset");
        reset.setBounds(650,600,110,40);
        reset.setFont(new Font("Arial",Font.BOLD,14));
        reset.setBackground(Color.RED);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        f.add(reset);
        
        
        



        Header_s=new JLabel("Attendence ");
        Header_s.setFont(new Font("Arial", Font.BOLD, 30));
        Header_s.setSize(700,80);
        Header_s.setForeground(new Color(255,255,255));
        Header_s.setLocation(450,12);
        f.add(Header_s);

        background=new ImageIcon("images/image.png");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(1050,75,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel backs=new JLabel(background);
        backs.setLayout(null);
        backs.setBounds(10,10,1050,75);
        f.add(backs);




        first_half=new JComboBox(options);
        second_half=new JComboBox(options);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        
        

        table=new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFont(new Font("Arial",Font.PLAIN,15));
        JScrollPane scroll=new JScrollPane(table);
        JTableHeader th = table.getTableHeader();
        th.setFont(new Font("Arial",Font.BOLD,12));
        th.setPreferredSize(new Dimension(0, 50));
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(10,130,1035,450);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        JTableHeader ta=table.getTableHeader();
        ta.setBackground(Color.PINK);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                c.setBackground(row%2==0 ? new Color(240, 240, 240) : Color.WHITE);
                return c;
            };
        });
        TableColumn tc1=table.getColumnModel().getColumn(2);
        TableColumn tc2=table.getColumnModel().getColumn(3);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
            PreparedStatement ps=cn.prepareStatement("Select Employee_id,Name from employee ");
            ResultSet rs= ps.executeQuery();
            int i=0;
            while(rs.next()){
                String eid=rs.getString(1);
                String emp_name=rs.getString(2);
                model.addRow(new Object[]{eid,emp_name});
                tc1.setCellEditor(new DefaultCellEditor(first_half));
                tc2.setCellEditor(new DefaultCellEditor(second_half));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        f.add(scroll);
        f.setBounds(350, 50,1100, 720);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){

                int confirm = JOptionPane.showOptionDialog(f,
                        "Are You Sure want submit the attendance?",
                        "Attendance Submission Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    try{
                        int rows=table.getRowCount();
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                        cn.setAutoCommit(false);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println(dtf.format(now));

                        PreparedStatement ps=cn.prepareStatement("Insert into attendance(date,Emp_id,Employee_name,first_half,second_half,total) values(?,?,?,?,?,?)");

                        for(int row=0;row<rows;row++){

                            String ename=(String)table.getValueAt(row,1);
                            String empid=(String)table.getValueAt(row,0);
                            String first_half=(String)table.getValueAt(row,2);
                            String second_half=(String)table.getValueAt(row,3);
                            if(second_half.equals("PRESENT")){
                                second_half="1";
                            }
                            else
                            {
                                second_half="0";
                            }
                            if(first_half.equals("PRESENT")){
                                first_half="1";
                            }
                            else
                            {
                                first_half="0";
                            }
                            float fh=Integer.parseInt(first_half);
                            float sh=Integer.parseInt(second_half);
                            float total=(fh+sh)/2;
                            PreparedStatement psm=cn.prepareStatement("select total from attendance where Emp_id = ?");
                            psm.setString(1,empid);
                            ResultSet rst = psm.executeQuery();
                            float atttot = 0;
                            while(rst.next()){
                                atttot+=rst.getFloat(1);
                            }
                            System.out.println(atttot);
                            ps.setString(1,(dtf.format(now)));
                            ps.setString(2,empid);
                            ps.setString(3,ename);
                            ps.setString(4,first_half);
                            ps.setString(5,second_half);
                            ps.setString(6,String.valueOf(total));
                            ps.addBatch();
                        }
                        ps.executeBatch();
                        cn.commit();

                        JOptionPane.showMessageDialog(null,"Attedence submitted successfully.");
                        dispose();

                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        JOptionPane.showMessageDialog(null,classNotFoundException.getMessage());
                    }
                }
        }
        if(e.getSource()==back){
            f.dispose();
        }

    }

    public static void main(String[] args) {
        new Attendance_add();
    }
}

