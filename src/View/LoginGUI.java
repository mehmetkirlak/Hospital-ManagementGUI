package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;

public class LoginGUI extends JFrame {

	private JPanel w_panel;
	private JTextField fld_hastaTC;
	private JTextField fld_doktorTC;
	private JPasswordField fld_hastaPass;
	private JPasswordField fld_doktorPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 340);
		w_panel = new JPanel();
		w_panel.setBackground(Color.WHITE);
		w_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_panel);
		w_panel.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("narval.png")));
		lbl_logo.setBounds(162, 11, 117, 113);
		lbl_logo.setHorizontalAlignment(SwingConstants.LEFT);
		w_panel.add(lbl_logo);

		JTabbedPane w_tabpanel = new JTabbedPane(JTabbedPane.TOP);
		w_tabpanel.setBounds(10, 131, 464, 159);
		w_panel.add(w_tabpanel);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpanel.addTab("Hasta Giri\u015Fi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		fld_hastaTC = new JTextField();
		fld_hastaTC.setBounds(152, 11, 246, 26);
		w_hastaLogin.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);

		JLabel lbl_hastaTCno = new JLabel("T.C. Kimlik No");
		lbl_hastaTCno.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lbl_hastaTCno.setBounds(10, 11, 123, 20);
		w_hastaLogin.add(lbl_hastaTCno);

		JLabel lbl_hastaPassword = new JLabel("\u015Eifre");
		lbl_hastaPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lbl_hastaPassword.setBounds(10, 51, 123, 20);
		w_hastaLogin.add(lbl_hastaPassword);

		JButton btn_hastaRegister = new JButton("Kay\u0131t ol");
		btn_hastaRegister.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		btn_hastaRegister.setBounds(239, 82, 210, 38);
		w_hastaLogin.add(btn_hastaRegister);

		JButton btn_hastaLogin = new JButton("Giris");
		btn_hastaLogin.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		btn_hastaLogin.setBounds(10, 82, 219, 38);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(152, 48, 246, 26);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpanel.addTab("Doktor Giri\u015Fi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lbl_doktorTCno = new JLabel("T.C. Kimlik No");
		lbl_doktorTCno.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lbl_doktorTCno.setBounds(23, 11, 123, 20);
		w_doktorLogin.add(lbl_doktorTCno);

		fld_doktorTC = new JTextField();
		fld_doktorTC.setColumns(10);
		fld_doktorTC.setBounds(156, 11, 281, 26);
		w_doktorLogin.add(fld_doktorTC);

		JLabel lbl_doktorPassword = new JLabel("\u015Eifre");
		lbl_doktorPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lbl_doktorPassword.setBounds(23, 51, 123, 20);
		w_doktorLogin.add(lbl_doktorPassword);

		JButton btn_doktorGiris = new JButton("Giris");
		btn_doktorGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorTC.getText().length() == 0 || fld_doktorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM [user]");
						while (rs.next()) {
							if (fld_doktorTC.getText().equals(rs.getString("TCKN"))
									&& fld_doktorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTckn(rs.getString("TCKN"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTckn(rs.getString("TCKN"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorGiris.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 14));
		btn_doktorGiris.setBounds(23, 82, 414, 38);
		w_doktorLogin.add(btn_doktorGiris);

		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setBounds(156, 48, 281, 26);
		w_doktorLogin.add(fld_doktorPass);
	}
}
