import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Menu {
    private JFrame frame;
    private JTextArea textArea;

    public Menu() {
        // Create the main JFrame
        frame = new JFrame("Menu Example");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create a JTextArea for displaying text
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create the menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem itemDateTime = new JMenuItem("Show Date/Time");
        JMenuItem itemSaveToFile = new JMenuItem("Save to File");
        JMenuItem itemChangeColor = new JMenuItem("Change Background Color");
        JMenuItem itemExit = new JMenuItem("Exit");

        // Add menu items to the menu
        fileMenu.add(itemDateTime);
        fileMenu.add(itemSaveToFile);
        fileMenu.add(itemChangeColor);
        fileMenu.add(itemExit);
        menuBar.add(fileMenu);

        // Add menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Register action listeners for menu items
        itemDateTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show current date/time in the text area
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                textArea.append(formattedDateTime + "\n");
            }
        });

        itemSaveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save contents of text area to a file
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    try (PrintWriter writer = new PrintWriter(filename)) {
                        writer.println(textArea.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        itemChangeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change background color to random hue of orange
                Random random = new Random();
                float hue = 0.05f + random.nextFloat() * 0.05f; // Adjust range for orange hues
                Color randomColor = Color.getHSBColor(hue, 1.0f, 1.0f);
                frame.getContentPane().setBackground(randomColor);
            }
        });

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the program
                frame.dispose();
                System.exit(0);
            }
        });

        // Set initial random background color
        Random random = new Random();
        float hue = 0.05f + random.nextFloat() * 0.05f; // Initial random orange hue
        Color initialColor = Color.getHSBColor(hue, 1.0f, 1.0f);
        frame.getContentPane().setBackground(initialColor);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }
}
