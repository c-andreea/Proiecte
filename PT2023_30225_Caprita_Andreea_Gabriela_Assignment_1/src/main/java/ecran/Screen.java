package ecran;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JFrame implements ActionListener{

   // private final JFrame frame;
   // private final JPanel p1;
   // private final JPanel p2;
   // FlowLayout layot;

    public Screen(int width, int height) {
       // frame = new JFrame("Calculator");
       // p1=new JPanel();
      //  p2=new JPanel();
       // layot=new FlowLayout();

        setPreferredSize(new Dimension(width,height));

       // p1.setLayout(new GridLayout(4,2));
       // p2.setLayout(new GridLayout(3,1,10,10));

       // frame.setPreferredSize(new Dimension(700, 500));
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(2, 2));


       // frame.add(p1,BorderLayout.NORTH);
        //frame.add(p2,BorderLayout.SOUTH);
        addTextBox();
        addButtons();

    }

    public void addTextBox()
    {
        JLabel pol1=new JLabel("Polinomul 1:");
        JLabel pol2=new JLabel("Polinomul 2:");

        JTextField text= new JTextField();
        text.setBounds(12, 500-465, (700-165)/2, 28);
     ///   p1.add(pol1);
      ///  p1.add(text);

        add(text);
        JTextField text1= new JTextField();
        text.setPreferredSize(new Dimension(50,40));
       // p1.add(pol2);
       // p1.add(text1);
    }

    public void addButtons() {
      //  p2.add(new JPanel());
        //p2.add(new JPanel());
       // p2.add(new JPanel());
       // GridBagConstraints g= new GridBagConstraints();
       // JPanel row2=new JPanel();

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(10, 8));
      //  g.gridx = 0;
      //  g.gridy = 0;
        //row2.add(emptyPanel, g);

       /* JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setPreferredSize(new Dimension(10, 8));
        g.gridx = 1;
        g.gridy = 1;
        row2.add(emptyPanel1, g);*/


      //  row2.setLayout(new GridLayout(2,1,10,10));
        //p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
       JButton button1=new JButton("+");
       button1.setPreferredSize(new Dimension(80,60));
      // g.gridx=0;
      // g.gridy=0;
      // g.fill=GridBagConstraints.BOTH;
     //  g.insets=new Insets(10,10,10,10);
     //  g.weightx=1.0;
     //  g.weighty=1.0;
     //   row2.add(button1,g);



       JButton button2=new JButton("-");
        button2.setPreferredSize(new Dimension(80,60));
       // g.gridx=1;
      //  g.gridy=0;
     //   g.insets=new Insets(10,0,10,10);
     //   row2.add(button2,g);

        JButton button3=new JButton("/");
        button2.setPreferredSize(new Dimension(80,60));
       // g.gridx=0;
      //  g.gridy=1;
      ///  g.insets=new Insets(10,10,10,10);
      //  row2.add(button3,g);
//
       JButton button4=new JButton("x");
        button2.setPreferredSize(new Dimension(80,60));
       // g.gridx=1;
       // g.gridy=1;
       // g.insets=new Insets(10,0,10,10);
      //  row2.add(button4,g);
//p2.add(row2);


    }


    /*public void show() {
      JFrame frame=new JFrame("calculator");
      frame.setContentPane(new Screen(500,700));
      frame.pack();
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
