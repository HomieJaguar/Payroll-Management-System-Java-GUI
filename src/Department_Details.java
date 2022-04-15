import TextPrompt.TextPrompt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

public class Department_Details implements ActionListener
{


    JButton refresh;
    JFrame f1,f2;
    JPanel panel1,panel2;
    JTextField search;
    JButton delete;
    JTable t1,t2;
    ImageIcon background;
    ImageIcon tbackground;
    DefaultTableModel model;
    JLabel Header_s;

    JButton Add_Department, Search_Button,Back, Update_Department;

    String[] column={ "Department ID", "Department Name",};

    public Department_Details()
    {
        f1=new JFrame();
        search=new JTextField();
        search.setFont(new Font("Arial",Font.BOLD,13));
        search.setBounds(780,90,150,30);
        new TextPrompt("Search by ID/Name",search);
        f1.add(search);


        Back =new JButton("Back");
        Back.setBounds(280,90,110,30);
        Back.setFont(new Font("Arial",Font.BOLD,14));
        Back.setForeground(Color.WHITE);
        Back.setBackground(new Color(75,0,130));
        Back.addActionListener(this);
        f1.add(Back);

        Search_Button=new JButton("Search");
        Search_Button.setBounds(950,90,110,30);
        Search_Button.setFont(new Font("Arial",Font.BOLD,14));
        Search_Button.setBackground(new Color(140, 50, 205));
        Search_Button.setForeground(Color.WHITE);
        Search_Button.addActionListener(this);
        f1.add(Search_Button);


        refresh =new JButton("Refresh");
        refresh.setBounds(550,90,110,30);
        refresh.setFont(new Font("Arial",Font.BOLD,14));
        refresh.addActionListener(this);
        refresh.setForeground(Color.WHITE);
        refresh.setBackground(new Color(255,127,80));
        f1.add(refresh);


        Add_Department =new JButton("Add Dep");
        Add_Department.setBounds(10,90,110,30);
        Add_Department.setFont(new Font("Arial",Font.BOLD,14));
        Add_Department.addActionListener(this);
        Add_Department.setForeground(Color.WHITE);
        Add_Department.setBackground(new Color(50,205,50));
        f1.add(Add_Department);

        Update_Department = new JButton("Update Dep");
        Update_Department.setBounds(415,90,110,30);
        Update_Department.setFont(new Font("Arial",Font.BOLD,14));
        Update_Department.addActionListener(this);
        Update_Department.setForeground(Color.WHITE);
        Update_Department.setBackground(new Color(50,205,50));
        f1.add(Update_Department);

        delete =new JButton("Delete");
        delete.setBounds(140,90,110,30);
        delete.setFont(new Font("Arial",Font.BOLD,14));
        delete.addActionListener(this);
        delete.setForeground(Color.WHITE);
        delete.setBackground(new Color(178,34,34));
        f1.add(delete);

        panel1=new JPanel();
        panel1.setBounds(10,5,1050,75);
        panel1.setLayout(null);
        panel1.setBorder(BorderUIResource.getEtchedBorderUIResource());

        background=new ImageIcon("images/image.png");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(1050,75,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel backs=new JLabel(background);
        backs.setLayout(null);
        backs.setBounds(0,0,1050,75);

        Header_s=new JLabel("Department Details");
        Header_s.setFont(new Font("Arial", Font.BOLD, 20));
        Header_s.setSize(250,80);
        Header_s.setForeground(new Color(255,255,255));
        Header_s.setLocation(355,2);

        panel1.add(Header_s);
        panel1.add(backs);
        f1.add(panel1);

        panel2=new JPanel();
        panel2.setBounds(10,130,1050,450);
        panel2.setLayout(null);
        panel2.setBorder(BorderUIResource.getEtchedBorderUIResource());

        tbackground=new ImageIcon("images/image.png");
        Image imgs=background.getImage();
        Image temps=imgs.getScaledInstance(1050,75,Image.SCALE_SMOOTH);
        background=new ImageIcon(temps);
        JLabel tbacks=new JLabel(background);
        backs.setLayout(null);
        backs.setBounds(0,0,1050,75);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        t1=new JTable(model);
        t1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t1.setFont(new Font("Arial",Font.PLAIN,15));
        t1.setBackground(new Color(255,214,200));
        JScrollPane scroll=new JScrollPane(t1);
        JTableHeader th = t1.getTableHeader();
        th.setFont(new Font("Arial",Font.BOLD,12));
        th.setPreferredSize(new Dimension(0, 50));
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(10,10,1035,450);

        JTableHeader ta=t1.getTableHeader();
        ta.setBackground(Color.PINK);
        ta.setFont(new Font("Arial",Font.BOLD,14));
        t1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
            String sql="SELECT Depart_id,Depart_name from department";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            int i=0;
            while(rs.next()){
                String Dep_id=rs.getString("Depart_id");
                String Dep_name=rs.getString("Depart_name");
                model.addRow(new Object[]{Dep_id,Dep_name});
                i++;
            }
            t1.getColumnModel().getColumn(0).setPreferredWidth(40);
            t1.getColumnModel().getColumn(1).setPreferredWidth(125);
            t1.setRowHeight(30);

            if(i<1){
                JOptionPane.showMessageDialog(null,
                        "No Records Found!");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        panel2.add(tbacks);
        panel2.add(scroll);

        f1.add(panel2);
        f1.setBounds(350, 50,1080, 650);
        f1.setLayout(null);
        f1.setVisible(true);
    }

    public static void main(String[] args)
    {

        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        }catch(Exception e) {
            e.printStackTrace();
        }
        new Department_Details();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==Search_Button)
        {
            SearchDisplayResult();
        }
        if(e.getSource()==Add_Department)
        {
            new add_department();
        }
        if(e.getSource()==Update_Department)
        {
            new Update_Dept();
        }
        if(e.getSource()==Back)
        {
            f1.dispose();
            new dashboard();
        }
        if(e.getSource()==delete)
        {
            delete_row();
        }
        if(e.getSource()==refresh){
            model.setRowCount(0);
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                String sql="SELECT Depart_id,Depart_name from department";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                int i=0;
                while(rs.next()){
                    String Dep_id=rs.getString("Depart_id");
                    String Dep_name=rs.getString("Depart_name");
                    model.addRow(new Object[]{Dep_id,Dep_name});
                    i++;
                }
                t1.getColumnModel().getColumn(0).setPreferredWidth(40);
                t1.getColumnModel().getColumn(1).setPreferredWidth(125);
                t1.setRowHeight(30);

                if(i<1){
                    JOptionPane.showMessageDialog(null,
                            "No Records Found!");
                }
            }
            catch (ClassNotFoundException | SQLException es) {
                es.printStackTrace();
            }
        }
    }
    public void delete_row()
    {
        int row = t1.getSelectedRow();
        if(row>=0) {
            System.out.println(row);
            String cell = t1.getModel().getValueAt(row, 0).toString();
            String sql = "Delete from department where Depart_id=" + cell;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                PreparedStatement ps = con.prepareStatement(sql);
                ps.execute();

                JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                model.removeRow(row);
            } catch (ClassNotFoundException | SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Please Select the row to delete!");
        }
    }

    private void SearchDisplayResult() {
        String textfiled_text = search.getText();

        if(!(textfiled_text.isEmpty())) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/paroll", "root", "");
                PreparedStatement ps = con.prepareStatement("SELECT Depart_id,Depart_name from department where Depart_id=? or Depart_name=?");
                ps.setString(1, textfiled_text);
                ps.setString(2, textfiled_text);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    model.setRowCount(0);
                    String Dep_id=rs.getString("Depart_id");
                    String Dep_name=rs.getString("Depart_name");
                    model.addRow(new Object[]{Dep_id,Dep_name});
                }
                else{
                    JOptionPane.showMessageDialog(null,"No Records Found!");
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Please Enter something to search!");
        }
    }
}




