import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JFileChooser;

public class FileChooser extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileChosen;
	
	public static void main(String[] args) {
		
		try {
			FileChooser dialog = new FileChooser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FileChooser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FileChooser.class.getResource("/images/icon.png")));
		setTitle("Chose A File");
		setBounds(100, 100, 553, 381);
		getContentPane().setLayout(new BorderLayout());
		{
			JFileChooser fileChooser = new JFileChooser();
			getContentPane().add(fileChooser, BorderLayout.CENTER);
			
			int result = fileChooser.showOpenDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				setFile(fileChooser.getSelectedFile().getPath());
				fileChooser.setVisible(false);
			} else if (result == JFileChooser.CANCEL_OPTION) {
			}
		}
		
	}
	
	public void setFile(String file) { fileChosen = file; }
	public String getFile() { return fileChosen; }

}
