package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setBackground(Color.WHITE);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hosgeldiniz, Sayýn "+doctor.getName());
		lblNewLabel.setBounds(10, 11, 296, 34);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("C\u0131k\u0131s Yap");
		btnNewButton.setBounds(610, 11, 114, 23);
		btnNewButton.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 13));
		contentPane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 56, 714, 394);
		w_tab.setBackground(Color.WHITE);
		contentPane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("\u00C7al\u0131\u015Fma saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 156, 20);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"}));
		select_time.setBounds(176, 11, 70, 20);
		w_whour.add(select_time);
		
		JButton btn_addwhour = new JButton("Ekle");
		btn_addwhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				if (date.length() == 0) {
					Helper.showMsg("Lutfen gecerli bir tarih girin");

				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMsg("success");

						} else {
							Helper.showMsg("error");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addwhour.setBounds(256, 9, 64, 23);
		w_whour.add(btn_addwhour);
	}
}
