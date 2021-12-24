import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 *  A complete class to demonstrate a shape-drawing graphical user interface
 *
 * @author      Brandon Dombrowsky
 * @version     2021-11-30
 */
public class ShapeDrawing extends JFrame {
    //-----------------------------------------------------------------------------------
    //      INSTANCE VARIABLES
    //-----------------------------------------------------------------------------------
    /**     The main drawing panel                              */
    private JPanel              panel;
    /**     The drawing area                                    */
    private JPanel              drawArea;
    /**     The toolkit                                         */
    private Toolkit             toolkit;
    /**     The shape selection combobox                        */
    private JComboBox<String>   shapeChoice;
    /**     The color selection combobox                        */
    private JComboBox<String>   colorChoice;
    /**     The draw button                                     */
    private JButton             draw;
    /**     The x-coordinate text field                         */
    private JTextField          xCoordinate;
    /**     The y-coordinate text field                         */
    private JTextField          yCoordinate;
    /**     The slider; 100-400 range                           */
    private JSlider             slider;
    /**     The x-coordinate label                              */
    private JLabel              label1;
    /**     The y-coordinate label                              */
    private JLabel              label2;
    /**     The shape option text field w/dummy value           */
    private String shapeOption  = "triangle";
    /**     The color option text field w/dummy value           */
    private String colorOption  = "purple";
    /**     The x-coordinate temp String holder w/dummy value   */
    private String xCoordOption = "0";
    /**     The y-coordinate temp String holder w/dummy value   */
    private String yCoordOption = "0";
    /**     The slider value                                    */
    private int    sliderAmt    = 0;
    /**     The x-coordinate integer holder                     */
    private int    xCoord       = 0;
    /**     The x-coordinate integer holder                     */
    private int    yCoord       = 0;
    //-----------------------------------------------------------------------------------
    //      CONSTRUCTOR
    //-----------------------------------------------------------------------------------
    /**
     * ShapeDrawing graphical user interface constructor; to include two comboboxes, a slider, two labels and
     * textfields, and a single button to draw user specified shape
     */
    public ShapeDrawing() {
        setSize(700, 700);
        setTitle("Shape Drawing");

        // initialize GUI in the middle of screen
        toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width  - getWidth())  / 2,
                    (screenSize.height - getHeight()) / 2);

        // prevent resizing and implement exit on window close
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JPanel main-panel stuff
        panel = new JPanel();
        getContentPane().add(panel); // attach to JFrame
        panel.setLayout(null); // adding widgets ourself w/o Layout Manager; absolute positioning

        // JPanel drawing-area-panel stuff
        drawArea = new DrawArea();
        drawArea.setBounds(30, 75, 630, 560);
        panel.add(drawArea);

        // JButton stuff
        draw = new JButton("Draw");
        draw.setBounds(580, 20, 80, 35);
        panel.add(draw);
        draw.addActionListener(new responseActionListener());

        // JTextField stuff; x-coordinate
        xCoordinate = new JTextField();
        xCoordinate.setBounds(200, 20, 40, 28);
        panel.add(xCoordinate);
        label1 = new JLabel("x:");
        label1.setBounds(187, 20, 40, 28);
        panel.add(label1);

        // JTextField stuff; y-coordinate
        yCoordinate = new JTextField();
        yCoordinate.setBounds(265, 20, 40, 28);
        panel.add(yCoordinate);
        label2 = new JLabel("y:");
        label2.setBounds(252, 20, 40, 28);
        panel.add(label2);

        // JSlider stuff
        slider = new JSlider(100, 400, 100);
        slider.setBounds(335, 15, 220, 45);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(50);
        panel.add(slider);

        // JComboBox stuff; shape
        String[] shapes = {"Circle", "Square"};
        shapeChoice = new JComboBox<>(shapes);
        shapeChoice.setBounds(20, 20, 70, 28);
        panel.add(shapeChoice);

        // JCombBox stuff; color
        String[] colors = {"Red", "Green", "Blue"};
        colorChoice = new JComboBox<>(colors);
        colorChoice.setBounds(100, 20, 70, 28);
        panel.add(colorChoice);

        // set visible to True so user will see GUI
        setVisible(true);
    }
    //-----------------------------------------------------------------------------------
    //      ACTION LISTENER
    //-----------------------------------------------------------------------------------
    /**
     * Single action listener to "hear" all widgets; triggered by draw button click
     */
    private class responseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shapeOption  = shapeChoice.getSelectedItem().toString();
            colorOption  = colorChoice.getSelectedItem().toString();
            xCoordOption = xCoordinate.getText();
            yCoordOption = yCoordinate.getText();
            sliderAmt    = slider.getValue();
            xCoord       = Integer.parseInt(xCoordOption);
            yCoord       = Integer.parseInt(yCoordOption);

            // ask the drawing area to repaint itself
            drawArea.repaint();
        }
    }
    //-----------------------------------------------------------------------------------
    //      PAINT COMPONENT
    //-----------------------------------------------------------------------------------
    /**
     * The main drawing function (paintCompontent) within class used to draw in graphical user interface
     */
    private class DrawArea extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // must call super class' graphics component that was passed as param

            if (shapeOption.equals("Square") && colorOption.equals("Red")) {
                g.setColor(Color.RED);
                g.fillRect(xCoord, yCoord, sliderAmt, sliderAmt);
            } else if (shapeOption.equals("Square") && colorOption.equals("Green")) {
                g.setColor(Color.GREEN);
                g.fillRect(xCoord, yCoord, sliderAmt, sliderAmt);
            } else if (shapeOption.equals("Square") && colorOption.equals("Blue")) {
                g.setColor(Color.BLUE);
                g.fillRect(xCoord, yCoord, sliderAmt, sliderAmt);
            } else if (shapeOption.equals("Circle") && colorOption.equals("Red")) {
                g.setColor(Color.RED);
                g.fillOval(xCoord, yCoord, sliderAmt, sliderAmt);
            } else if (shapeOption.equals("Circle") && colorOption.equals("Green")) {
                g.setColor(Color.GREEN);
                g.fillOval(xCoord, yCoord, sliderAmt, sliderAmt);
            } else {
                g.setColor(Color.BLUE);
                g.fillOval(xCoord, yCoord, sliderAmt, sliderAmt);
            }
        }
    }
    //-----------------------------------------------------------------------------------
    //      MAIN
    //-----------------------------------------------------------------------------------
    /**
     * Main function to initialize graphical user interface
     * @param args      standard Main fanfare
     */
    public static void main(String[] args) {
        ShapeDrawing gui = new ShapeDrawing();
    }
}