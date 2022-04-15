import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;

public class Attendence_Details implements ActionListener {
    JLabel Header_s;
    ImageIcon background;
    JFrame f;
    DefaultTableModel model;
    JButton reset;
    JTable table;
    JButton submit;
    public Attendence_Details() {
        f=new JFrame();

        String[] column={"EmpID","Employee Name","Total Attendence","Percentage %"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);




        submit=new JButton("Take Attendence");
        submit.setBounds(410,600,180,40);
        submit.setFont(new Font("Arial",Font.BOLD,14));
        submit.setBackground(new Color(140, 50, 205));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        f.add(submit);

        reset =new JButton("Generate Report ");
        reset.setBounds(610,600,180,40);
        reset.setFont(new Font("Arial",Font.BOLD,14));
        reset.setBackground(Color.RED);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        f.add(reset);

        Header_s=new JLabel("Attendence Report");
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

        table=new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFont(new Font("Arial",Font.PLAIN,15));
        table.setBackground(new Color(255,214,200));
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
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
            PreparedStatement ps=cn.prepareStatement("Select Employee_id,Name from employee");
            ResultSet rs= ps.executeQuery();
            int i=0;

            while(rs.next()){
                String eid=rs.getString(1);
                String emp_name=rs.getString(2);
                PreparedStatement psm=cn.prepareStatement("select total from attendance where Emp_id = ?");
                psm.setString(1,eid);
                ResultSet rst = psm.executeQuery();
                float attot = 0;
                int ct = 0;
                while(rst.next()){
                    ct++;
                    attot+=rst.getFloat(1);
                }
                Float perc = (attot/ct)*100;

                model.addRow(new Object[]{eid,emp_name,attot,perc});
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        f.add(scroll);
        f.setBounds(350, 50,1100, 720);
        f.setLayout(null);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection cnn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                PreparedStatement st1 = cnn.prepareStatement("Select Employee_id from employee");
                ResultSet r1= st1.executeQuery();
                int enm = 0;
                while (r1.next()){
                    enm++;
                }
                PreparedStatement st2 = cnn.prepareStatement("Select Date from attendance where Date= CURDATE()");
                ResultSet r2 = st2.executeQuery();
                int dtn= 0;
                while (r2.next()){dtn++;}
                if(enm == dtn){
                    JOptionPane.showMessageDialog(null,"Attendance for today already recorded");}
                else{new Attendance_add();
                    f.dispose();

                }


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == reset) {
            MessageFormat header=new MessageFormat("Attendence Details");
            MessageFormat footer=new MessageFormat("Page{0,number,integer}");
            try {
                table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        new Attendence_Details();
    }
}