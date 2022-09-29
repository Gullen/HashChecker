import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TextFile;

	
	
	private String fileChosen;
	private String hashCalculated;
	private String knownHash;
	
	private JRadioButton ButtonMD5;
	private JRadioButton ButtonSHA256;
	private JTextField TextKnownHash;
	private JTextField TextCalculatedHash;
	private JTextField TextCalculated;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/icon.png")));
		setTitle("Hash Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		ButtonGroup HashGroup = new ButtonGroup();
		
		ButtonMD5 = new JRadioButton("MD5");
		ButtonMD5.setBounds(26, 54, 81, 23);
		contentPane.add(ButtonMD5);
		ButtonMD5.setBackground(new Color(105, 105, 105));
		HashGroup.add(ButtonMD5);
		
		
		ButtonSHA256 = new JRadioButton("SHA256");
		ButtonSHA256.setBounds(109, 54, 81, 23);
		contentPane.add(ButtonSHA256);
		ButtonSHA256.setBackground(new Color(105, 105, 105));
		HashGroup.add(ButtonSHA256);
		
		JButton ButtonFile = new JButton("Choose A File");
		ButtonFile.setFont(new Font("Arial", Font.PLAIN, 12));
		ButtonFile.setBounds(26, 11, 112, 23);
		
		ButtonFile.setUI(new StyledButtonUI());
		ButtonFile.setBackground(new Color(10, 106, 255));
		ButtonFile.setForeground(new Color (255, 255, 255));
		
		contentPane.add(ButtonFile);
		
		TextFile = new JTextField();
		TextFile.setBounds(148, 12, 254, 20);
		contentPane.add(TextFile);
		TextFile.setEditable(false);
		TextFile.setColumns(20);
		
		TextKnownHash = new JTextField();
		TextKnownHash.setBounds(119, 91, 286, 20);
		contentPane.add(TextKnownHash);
		TextKnownHash.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Known Hash :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(26, 91, 94, 20);
		contentPane.add(lblNewLabel);
		
		TextCalculatedHash = new JTextField();
		TextCalculatedHash.setEditable(false);
		TextCalculatedHash.setColumns(20);
		TextCalculatedHash.setBounds(26, 183, 376, 20);
		contentPane.add(TextCalculatedHash);
		
		JLabel lblCAL = new JLabel("= = = = = C A L C U L A T E D   H A S H = = = = =");
		lblCAL.setHorizontalAlignment(SwingConstants.CENTER);
		lblCAL.setForeground(Color.BLACK);
		lblCAL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCAL.setBounds(26, 143, 376, 20);
		contentPane.add(lblCAL);
		
		TextCalculated = new JTextField();
		TextCalculated.setFont(new Font("Tahoma", Font.BOLD, 18));
		TextCalculated.setHorizontalAlignment(SwingConstants.CENTER);
		TextCalculated.setBackground(new Color(0, 0, 0));
		TextCalculated.setForeground(Color.WHITE);
		TextCalculated.setBounds(26, 221, 376, 29);
		contentPane.add(TextCalculated);
		TextCalculated.setColumns(10);
		TextCalculated.setBorder(null);
		
		JButton ButtonGo = new JButton("G O !");
		ButtonGo.setFont(new Font("Arial", Font.PLAIN, 14));
		ButtonGo.setBounds(196, 54, 206, 23);
		ButtonGo.setUI(new StyledButtonUI());
		ButtonGo.setBackground(new Color(10, 106, 255));
		ButtonGo.setForeground(new Color (255, 255, 255));
		contentPane.add(ButtonGo);
		
		
		ButtonFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				FileChooser dialog = new FileChooser();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				
				TextFile.setText(dialog.getFile());
				fileChosen = dialog.getFile();
			}		
		});
		
		ButtonGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				knownHash = TextKnownHash.getText();
				if (ButtonMD5.isSelected()) {
					try {
						hashCalculated = MD5Checksum.getMD5Checksum(fileChosen);
						changeVals();
					} catch (Exception e1) {
						e1.printStackTrace();
					}		
				}
				else if (ButtonSHA256.isSelected()) {
					try {
						hashCalculated = SHA256Checksum.getSHA256Checksum(fileChosen);
						changeVals();
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
				
			}
			
		});
	}
	
	public void changeVals() {
		TextCalculatedHash.setText(hashCalculated.toUpperCase());
		if (hashCalculated.toUpperCase().equals(knownHash.toUpperCase())) {
			TextCalculated.setBackground(new Color (0, 255, 0));
			TextCalculated.setText("P A S S I N G");
		} else{
			TextCalculated.setBackground(new Color (255, 0, 0));
			TextCalculated.setText("F A I L I N G");
		}
		if (knownHash.equals("")) {
			TextCalculated.setBackground(new Color (0, 0, 0));
			TextCalculated.setText("");
		}
	}
}
