import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class about_us extends JFrame {
    JFrame abt;
    JLabel d1, d2,d3,d4;
    JLabel e1,e2,e3,e4;
    JTextArea desc;
    JLabel heading;
    JLabel background;

    about_us() throws IOException {

        FileReader reader = new FileReader("doc/About.txt");

        abt =new JFrame();
        abt.setBounds(350, 50,1100, 720);
        abt.setLayout(null);
        abt.setResizable(false);
        abt.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        heading = new JLabel("About");
        heading.setFont(new Font("Arial",Font.BOLD,30));
        heading.setBounds(500, 0,200, 40);
        abt.add(heading);

        TitledBorder title;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        title = BorderFactory.createTitledBorder(blackline,"Hello Everyone");
        title.setTitleFont(new Font("Arial",Font.BOLD,18));

        desc = new JTextArea();
        desc.setFont(new Font("Arial",Font.BOLD,18));
        desc.read(reader, "doc/About.txt");
        desc.setBounds(50,100,1000,200);
        desc.setEditable(false);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setVisible(true);
        desc.setBorder(title);
        abt.add(desc);

        JLabel cont = new JLabel("Made by");
        cont.setFont(new Font("Arial",Font.BOLD,18));
        cont.setSize(200,30);
        cont.setLocation(30,360);
        abt.add(cont);

        d1 = new JLabel("Adarsh Singh Nishen 1961055");
        d1.setFont(new Font("Arial",Font.BOLD,14));
        d1.setSize(300,30);
        d1.setLocation(30,390);
        abt.add(d1);

        e1 = new JLabel("adarsh.nishen@btech.christuniversity.in");
        e1.setFont(new Font("Arial",Font.BOLD,14));
        e1.setSize(300,30);
        e1.setLocation(30,420);
        abt.add(e1);

        d2 = new JLabel("Sarthak Swapnil Dubey 1961056");
        d2.setFont(new Font("Arial",Font.BOLD,14));
        d2.setSize(300,30);
        d2.setLocation(30,460);
        abt.add(d2);

        e2 = new JLabel("sarthak.swapnil@btech.christuniversity.in");
        e2.setFont(new Font("Arial",Font.BOLD,14));
        e2.setSize(310,30);
        e2.setLocation(30,490);
        abt.add(e2);

        d3 = new JLabel("Ashmit Singh 1961062");
        d3.setFont(new Font("Arial",Font.BOLD,14));
        d3.setSize(300,30);
        d3.setLocation(30,530);
        abt.add(d3);

        e3 = new JLabel("ashmit.singh@btech.christuniversity.in");
        e3.setFont(new Font("Arial",Font.BOLD,14));
        e3.setSize(300,30);
        e3.setLocation(30,560);
        abt.add(e3);

        d4 = new JLabel("Abuzar Ahmed Quadri 1961063");
        d4.setFont(new Font("Arial",Font.BOLD,14));
        d4.setSize(300,30);
        d4.setLocation(30,600);
        abt.add(d4);

        e4 = new JLabel("abuzar.ahmed@btech.christuniversity.in");
        e4.setFont(new Font("Arial",Font.BOLD,14));
        e4.setSize(300,30);
        e4.setLocation(30,630);
        abt.add(e4);

        background=new JLabel();
        background.setIcon(new ImageIcon("images/w.jpg"));
        background.setSize(1100,720);
        abt.add(background);
        abt.setVisible(true);

    }
    public static void main(String[] args) throws IOException {
        new about_us();
    }
}
