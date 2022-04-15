import TextPrompt.TextPrompt;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.MessageFormat;

public class paymentHistory extends JFrame implements ActionListener {
    JButton prnt;
    JTextField search;
    JButton search_button;
    JLabel Header_s;
    ImageIcon background;
    JFrame f;
    DefaultTableModel model;
    JButton reset;
    JTable table;
    JButton submit;
    String[] column={"Date","PayID","empID","Employee Name","A/c no.","year of salary paid","month of salary paid","Total Pay"};
    JFrame f2;
    JPanel panel3;
    JTable table2;

    public paymentHistory() {
        f=new JFrame();


        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);


        search=new JTextField();
        search.setFont(new Font("Arial",Font.BOLD,13));
        search.setBounds(780,90,150,30);
        new TextPrompt("Search by Payno.", search);
        f.add(search);

        search_button=new JButton("Search");
        search_button.setBounds(950,90,110,30);
        search_button.setFont(new Font("Arial",Font.BOLD,14));
        search_button.setBackground(new Color(140, 50, 205));
        search_button.setForeground(Color.WHITE);
        search_button.addActionListener(this);
        f.add(search_button);


        prnt =new JButton("Print ");
        prnt.setBounds(480,600,180,40);
        prnt.setFont(new Font("Arial",Font.BOLD,14));
        prnt.setBackground(Color.RED);
        prnt.setForeground(Color.WHITE);
        prnt.addActionListener(this);
        f.add(prnt);

        Header_s=new JLabel("Payment History");
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
        table.getColumnModel().getColumn(0).setPreferredWidth(110);
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
            PreparedStatement ps=cn.prepareStatement("Select date,pay_no,emp_id,name,accno,year,month,total_pay from payment order by date DESC");
            ResultSet rs= ps.executeQuery();
            int i=0;

            while(rs.next()){
                String date=rs.getString(1);
                String payno=rs.getString(2);
                String emp_id=rs.getString(3);
                String name=rs.getString(4);
                String accno=rs.getString(5);
                String year=rs.getString(6);
                String month=rs.getString(7);
                float totalpay=rs.getFloat(8);

                model.addRow(new Object[]{date,payno,emp_id,name,accno,year,month,totalpay});
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
            new Attendance_add();
            f.dispose();
        }
        if (e.getSource() == prnt) {
            MessageFormat header=new MessageFormat("Attendence Details");
            MessageFormat footer=new MessageFormat("Page{0,number,integer}");
            try {
                table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
        if(e.getSource()==search_button){
            SearchDisplayResult();
        }

    }

    private void SearchDisplayResult() {
        String textfiled_text = search.getText();

        if(!(textfiled_text.isEmpty())) {
            f2 = new JFrame("Transaction History Search Result");
            f2.setLayout(null);
            panel3 = new JPanel();
            panel3.setBounds(10, 50, 1000, 400);
            panel3.setLayout(null);
            panel3.setBorder(BorderUIResource.getEtchedBorderUIResource());

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(column);

            JButton dback=new JButton("Back");
            dback =new JButton("Back");
            dback.setBounds(10,15,110,30);
            dback.setFont(new Font("Arial",Font.BOLD,14));
            dback.setForeground(Color.WHITE);
            dback.setBackground(new Color(75,0,130));
            dback.addActionListener(this);
            dback.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f2.dispose();
                }
            });
            f2.add(dback);




            table2=new JTable(model);
            table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table2.setFont(new Font("Arial",Font.PLAIN,15));
            table2.setBackground(new Color(255,214,200));
            JScrollPane scroll=new JScrollPane(table2);
            JTableHeader th = table2.getTableHeader();
            th.setFont(new Font("Arial",Font.BOLD,12));
            th.setPreferredSize(new Dimension(0, 50));
            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setBounds(10,10,1000,450);
            table2.setRowHeight(30);
            table2.getColumnModel().getColumn(1).setPreferredWidth(40);
            table2.getColumnModel().getColumn(0).setPreferredWidth(110);
            table2.getColumnModel().getColumn(2).setPreferredWidth(40);
            JTableHeader ta=table2.getTableHeader();
            ta.setBackground(Color.PINK);
            table2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                PreparedStatement ps = con.prepareStatement("Select date,pay_no,emp_id,name,accno,year,month,total_pay from payment where pay_no=? or name=?");
                ps.setString(1, textfiled_text);
                ps.setString(2, textfiled_text);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String date=rs.getString(1);
                    String payno=rs.getString(2);
                    String emp_id=rs.getString(3);
                    String name=rs.getString(4);
                    String accno=rs.getString(5);
                    String year=rs.getString(6);
                    String month=rs.getString(7);
                    float totalpay=rs.getFloat(8);

                    model.addRow(new Object[]{date,payno,emp_id,name,accno,year,month,totalpay});
                    f2.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"No Records Found!");
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            panel3.add(scroll);
            f2.add(panel3);
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Please Enter something to search!");
            f2.setVisible(false);
        }
        f2.setBounds(350, 50,1100, 500);
        f2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f2.setVisible(true);

    }

    public static void main(String[] args) {
        new paymentHistory();
    }

}

