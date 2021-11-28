import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URI;

public class BoardGUI{

    private final JPanel gui = new JPanel(new BorderLayout(4, 4));
    private JPanel chessBoard;
    private JPanel[][] chessBoardSquares;
    private JPanel bg;
    private JPanel dashboard;
    private final JLabel message = new JLabel(
            "The Miner is on its way to find the Gold!");
   

    public BoardGUI(){
        
    }
       
     public void initializeBoard(int n){  

        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        var back = new JButton("<");
        tools.add(back); 
        var next = new JButton(">");
        tools.add(next); 
        tools.addSeparator();
        var fastview = new JButton(">>");
        tools.add(fastview); 
        tools.addSeparator();
        tools.add(message);

        BorderLayout layout = new BorderLayout(4,4);
        layout.setHgap(10);
        bg = new JPanel(layout);
        chessBoardSquares = new JPanel[n][n];
        
        chessBoard = new JPanel(new GridLayout(0, n+1));
       
        bg.add(chessBoard, BorderLayout.CENTER);
        chessBoard.setBorder(new LineBorder(Color.GRAY));
        
        // the input for positions
        dashboard = new JPanel();
        dashboard.setPreferredSize(new Dimension(200, 0));
        dashboard.setBorder(new LineBorder(Color.GRAY));
        dashboard.add(new JLabel("Board Size: "));
        dashboard.add(new JTextField(1));
        dashboard.add(new JLabel("Beacon Locations: "));
        dashboard.add(new JTextField(1));
        dashboard.add(new JTextField(1));
        dashboard.add(new JLabel("Pit Locations: "));
        dashboard.add(new JTextField(1));
        dashboard.add(new JTextField(1));
        dashboard.add(new JLabel("Gold Location: "));
        dashboard.add(new JTextField(1));
        dashboard.add(new JTextField(1));
        dashboard.add(new JSeparator(SwingConstants.HORIZONTAL));

        bg.add(dashboard, BorderLayout.LINE_START);
        gui.add(bg);

        // create the chess board squares
        // Insets panelMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JPanel p = new JPanel();
                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                p.setBorder(loweredetched);
                p.setBackground(Color.WHITE);
                chessBoardSquares[j][i] = p;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
      //  fill the top row
        for (int i = 0; i < n; i++) {
            chessBoard.add(new JLabel("" + (i+1), SwingConstants.CENTER));
        }
       // fill the black non-pawn piece row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }

    }

        public final JComponent getGui() {
            return gui;
        }

     public void piecesGUI(Tile tile){
    
        switch(tile.getClass().getName()){
            case "Beacon":
                var beaconImage= new JLabel();
                beaconImage.setSize(chessBoardSquares[tile.getX()][tile.getY()].getSize());
                beaconImage.setIcon(new ImageIcon(new ImageIcon("1.png").getImage().getScaledInstance(beaconImage.getWidth(), beaconImage.getHeight(), Image.SCALE_DEFAULT)));
                beaconImage.setHorizontalAlignment(JLabel.CENTER);
                beaconImage.setVerticalAlignment(JLabel.CENTER);

                chessBoardSquares[tile.getX()][tile.getY()].add(beaconImage);
                chessBoardSquares[tile.getX()][tile.getY()].revalidate(); // redraws layout
                break;  
            case "Pit":
                var pitImage= new JLabel();
                pitImage.setSize(chessBoardSquares[tile.getX()][tile.getY()].getSize());
                pitImage.setIcon(new ImageIcon(new ImageIcon("2.png").getImage().getScaledInstance(pitImage.getWidth(), pitImage.getHeight(), Image.SCALE_DEFAULT)));
                pitImage.setHorizontalAlignment(JLabel.CENTER);
                pitImage.setVerticalAlignment(JLabel.CENTER);

                chessBoardSquares[tile.getX()][tile.getY()].add(pitImage);
                chessBoardSquares[tile.getX()][tile.getY()].revalidate(); // redraws layout
                
                break;  
            case "Gold":
                var goldImage= new JLabel();
                goldImage.setSize(chessBoardSquares[tile.getX()][tile.getY()].getSize());
                goldImage.setIcon(new ImageIcon(new ImageIcon("3.png").getImage().getScaledInstance(goldImage.getWidth(), goldImage.getHeight(), Image.SCALE_DEFAULT)));
                goldImage.setHorizontalAlignment(JLabel.CENTER);
                goldImage.setVerticalAlignment(JLabel.CENTER);

                chessBoardSquares[tile.getX()][tile.getY()].add(goldImage);
                chessBoardSquares[tile.getX()][tile.getY()].revalidate(); // redraws layout
                break;  
        }

    }

    public void minerGui(Miner miner){
        var minerImage= new JLabel();
                minerImage.setSize(chessBoardSquares[miner.getX_position()][miner.getY_position()].getSize());
                minerImage.setIcon(new ImageIcon(new ImageIcon("4.png").getImage().getScaledInstance(minerImage.getWidth(), minerImage.getHeight(), Image.SCALE_DEFAULT)));
                minerImage.setHorizontalAlignment(JLabel.CENTER);
                minerImage.setVerticalAlignment(JLabel.CENTER);

                chessBoardSquares[miner.getX_position()][miner.getY_position()].add(minerImage);
                chessBoardSquares[miner.getX_position()][miner.getY_position()].revalidate(); // redraws layout
    }


    public void displayGui(int n){
        Runnable r = new Runnable() {

            @Override
            public void run() {
                initializeBoard(n);

                JFrame f = new JFrame("GOLD MINER");
                f.add(getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(new Dimension(900, 600));
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}



