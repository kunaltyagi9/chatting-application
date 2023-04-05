package chattingapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server implements ActionListener
{
    JTextField text; // Declare Globally
    JPanel p2;
    /**
    *No static variable cannot be reference from a static context.
     * Because All The class with object declare as static
     */

    static Box vertical = Box.createVerticalBox();
    static JFrame frame = new JFrame();
    //Send for Data output to the Client
    static DataOutputStream outputStream;
    Server()
    {
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,100,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        frame.add(p1);
        
        // Arrow Pic for Close the Panel
        ImageIcon icon = new  ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image icon2 = icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel back = new JLabel(icon3);
        //JLabel set in the Frame 
        back.setBounds(5,20,25,25);
       
        //Image is added on the Panel through object 'p1'
        p1.add(back);
        
        //Close the Panel through MOUSE Listener 
         back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        }); //In this Frame is Closed
       
         
         // For Profile Photo
         ImageIcon icon4 = new  ImageIcon(ClassLoader.getSystemResource("icons/1.ng"));
        Image icon5 = icon4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon icon6 = new ImageIcon(icon5);
        JLabel profile = new JLabel(icon6);
        
        //JLabel set in the Frame   
        profile.setBounds(40,10,50,50);
        
        //Image is added on the Panel through object 'p1'
        p1.add(profile);
         
         
         // Video Pic show at the Panel
        ImageIcon icon7 = new  ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image icon8 = icon7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon icon9 = new ImageIcon(icon8);
        JLabel video = new JLabel(icon9);
        
        //JLabel set in the Frame   
        video.setBounds(300,20,30,30);
        p1.add(video);
       
        
         // Phone Pic show at the Panel
        ImageIcon icon10 = new  ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image icon11 = icon10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon icon12 = new ImageIcon(icon11);
        JLabel phone = new JLabel(icon12);
        
        //JLabel set in the Frame   
        phone.setBounds(360,20,35,30);
        
        //Image is added on the Panel through object 'p1'
        p1.add(phone);
       
        
        
        // 3Icon Pic show at the Panel
        ImageIcon icon13 = new  ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image icon14 = icon13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon icon15 = new ImageIcon(icon14);
        JLabel moredetail = new JLabel(icon15);
        
        //JLabel set in the Frame   
        moredetail.setBounds(420,20,10,25);
        
        //Image is added on the Panel through object 'p1'
        p1.add(moredetail);

        //Main Title Set Server Side
        JLabel title = new JLabel("Server-Side");
        title.setBounds(197,50,100,15);
        title.setForeground(new Color(229, 229, 237));
        title.setFont(new Font("SAN SERIF",Font.BOLD,17));
        p1.add(title);

        //Profile Name set
        JLabel name = new JLabel("Anonymous User");
        name.setBounds(100,19,150,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF",Font.BOLD,17));
        p1.add(name);

        //Profile status set
        JLabel status = new JLabel("Online");
        status.setBounds(100,39,150,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN SERIF",Font.BOLD,12));
        p1.add(status); 
        
        
        //Set for Main Panel
        p2 = new JPanel();
        p2.setBounds(5,75,440,570);
        frame.add(p2);
        
        //User TextField
        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        frame.add(text);

        //Set Send Button
        JButton send = new JButton("Send");
        send.setBounds(320, 655,123,40);
        send.setBackground(new Color(7,102,84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("ARIAL", Font.PLAIN, 18 ));
        send.addActionListener(this);
        frame.add(send);

        frame.setSize(450,700);
        // Panel Auto Display at this location when Run the Program
        frame.setLocation(200,20);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.WHITE);

        frame.setVisible(true);
    }

   //  This is An Abstract Method of ActionListener.
    public void actionPerformed(ActionEvent event)
    {
        try
        {
            // String is Not Pass in JPanel, then using JLabel And then JPanel used Successfully.
            String out = text.getText();
            JPanel p3 = formatLabel(out);

            // Your Element Place Top,Bottom ,Center.
            p2.setLayout(new BorderLayout());
            // Your Message is Sent at Show Panel Right Side.Means At the End of the Panel
            JPanel right = new JPanel(new BorderLayout());
            right.add(p3, BorderLayout.LINE_END);

            vertical.add(right); // Align Right Side
            //One Message Box to another Message Box,Distance between 10 Spaces.
            vertical.add(Box.createVerticalStrut(10));

            //Vertical your is Show Message from at the Page Start Means Inner Message Panel.
            p2.add(vertical, BorderLayout.PAGE_START); // p2 is Main Panel

            //Server Send the Message using write-UTF, Message Pass in outputStream object
            outputStream.writeUTF(out);

            //When one Message is Sent TextBox is show Empty
            text.setText("");

            frame.repaint();
            frame.invalidate();
            frame.validate();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public static JPanel formatLabel(String out)
    {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        // Using HTML & CSS for Adjust Width of Deliver Message Box
        JLabel output = new JLabel("<html><p style = \"width: 150px\"> "+ out +"</p></html>");

        // set font for Show Display Message in a Box.
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(6, 248, 11, 126));
        output.setOpaque(true); // Set for Box Message Background Color

        // Adjust Text at the Box Upper, Lower, Right Left
        output.setBorder(new EmptyBorder(12,12,12,50));
        panel.add(output);

        //Show Time for Message Deliver.
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();

        //Show Current Computer Time
        time.setText(simpleDateFormat.format(calendar.getTime()));

        panel.add(time);
        return panel;
    }

    public static void main(String args[])
    {
        new Server(); // This is Anonymous Class
        try
        {
            ServerSocket serverSocket = new ServerSocket(6001);
            while (true)
            {
                // For Client Accept Message
                Socket socket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                while (true)
                {
                    //When Message came at the Server, Server Read the Message
                    // Client Message is Store the String Object message.
                    String message = inputStream.readUTF();

                    // Now Display the Message at the Panel.
                    // formatLabel Adjust the Message at the Panel with Background Color, Fonts etc...
                    JPanel panel = formatLabel(message);

                    //Message at the Left-Side
                    JPanel left = new JPanel(new BorderLayout());

                    //Client Message Display at the Start of the Panel.
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);

                    frame.validate();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
