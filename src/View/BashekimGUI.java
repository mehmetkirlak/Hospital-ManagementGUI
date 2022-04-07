package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doctorId;
	private JTable tablo_doktor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField fld_addClinic;
	private JTable table_worker;
	private JTable table_clinic;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTextField fld_clinicId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "Id";
		colDoctorName[1] = "TC no";
		colDoctorName[2] = "Ad";
		colDoctorName[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getTckn();
			doctorData[2] = bashekim.getDoctorList().get(i).getName();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colDoctorName[0] = "Id";
		colDoctorName[1] = "Poliklinik Ýsmi";
		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		// WorkerModel
				DefaultTableModel workerModel = new DefaultTableModel();
				Object[] colWorker = new Object[2];
				colWorker[0] = "ID";
				colWorker[1] = "Ad Soyad";
				workerModel.setColumnIdentifiers(colWorker);
				Object[] workerData = new Object[2];

		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz, Say\u0131n " + bashekim.getName());
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 11, 296, 34);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("C\u0131k\u0131s Yap");
		btnNewButton.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 13));
		btnNewButton.setBounds(610, 11, 114, 23);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(Color.WHITE);
		w_tab.setBounds(10, 56, 714, 394);
		w_pane.add(w_tab);

		JPanel w_doktor = new JPanel();
		w_doktor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Y\u00F6netimi", null, w_doktor, null);
		w_doktor.setLayout(null);

		JLabel label1 = new JLabel("Ad Soyad");
		label1.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		label1.setBounds(544, 14, 155, 22);
		w_doktor.add(label1);

		fld_dName = new JTextField();
		fld_dName.setBounds(544, 35, 155, 22);
		w_doktor.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel label2 = new JLabel("TCKN");
		label2.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		label2.setBounds(544, 68, 155, 22);
		w_doktor.add(label2);

		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(544, 90, 155, 22);
		w_doktor.add(fld_dTcno);

		JLabel label3 = new JLabel("\u015Eifre");
		label3.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		label3.setBounds(544, 126, 155, 22);
		w_doktor.add(label3);

		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(544, 148, 155, 22);
		w_doktor.add(fld_dPass);

		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							fld_dName.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
		btn_addDoctor.setBounds(544, 186, 155, 34);
		w_doktor.add(btn_addDoctor);

		JLabel label4 = new JLabel("Kullan\u0131c\u0131 ID");
		label4.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		label4.setBounds(544, 240, 155, 22);
		w_doktor.add(label4);

		fld_doctorId = new JTextField();
		fld_doctorId.setColumns(10);
		fld_doctorId.setBounds(544, 262, 155, 22);
		w_doktor.add(fld_doctorId);

		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorId.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					if (Helper.confirm("sure")) {
						try {
							boolean control = bashekim.delDoctor(Integer.valueOf(fld_doctorId.getText()));
							if (control) {
								Helper.showMsg("success");
								fld_doctorId.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoctor.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
		btn_delDoctor.setBounds(543, 302, 155, 34);
		w_doktor.add(btn_delDoctor);

		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 10, 524, 345);
		w_doktor.add(w_scrollDoktor);

		tablo_doktor = new JTable(doctorModel);
		tablo_doktor.setBackground(Color.WHITE);
		w_scrollDoktor.setViewportView(tablo_doktor);
		tablo_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorId.setText(tablo_doktor.getValueAt(tablo_doktor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});
		tablo_doktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectId = Integer
							.parseInt(tablo_doktor.getValueAt(tablo_doktor.getSelectedRow(), 0).toString());
					String selectTcno = tablo_doktor.getValueAt(tablo_doktor.getSelectedRow(), 1).toString();
					String selectPass = tablo_doktor.getValueAt(tablo_doktor.getSelectedRow(), 2).toString();
					String selectName = tablo_doktor.getValueAt(tablo_doktor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.updateDoctor(selectId, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(449, 11, 250, 344);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 11, 250, 344);
		w_clinic.add(scrollPane_1_1);

		table_clinic = new JTable(clinicModel);
		table_clinic.setBackground(Color.WHITE);
		scrollPane_1_1.setViewportView(table_clinic);
		table_clinic.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_clinicId.setText(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}
			}
		});
		table_clinic.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectId = Integer
							.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					String selectName = table_clinic.getValueAt(table_clinic.getSelectedRow(), 1).toString();

					try {
						boolean control = clinic.updateClinic(selectId, selectName);
						if (control) {
							Helper.showMsg("success");
							fld_doctorId.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		fld_addClinic = new JTextField();
		fld_addClinic.setColumns(10);
		fld_addClinic.setBounds(277, 33, 152, 22);
		w_clinic.add(fld_addClinic);

		JLabel lblPoliklinik = new JLabel("Poliklinik");
		lblPoliklinik.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		lblPoliklinik.setBounds(277, 11, 155, 22);
		w_clinic.add(lblPoliklinik);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_addClinic.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_addClinic.getText())) {
							Helper.showMsg("success");
							fld_addClinic.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
		btn_addClinic.setBounds(277, 57, 153, 34);
		w_clinic.add(btn_addClinic);
		
		fld_clinicId = new JTextField();
		fld_clinicId.setColumns(10);
		fld_clinicId.setBounds(277, 118, 152, 29);
		w_clinic.add(fld_clinicId);
		
		JLabel label4_1 = new JLabel("Poliklinik ID");
		label4_1.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		label4_1.setBounds(277, 96, 152, 22);
		w_clinic.add(label4_1);
		
		JButton btn_delClinic = new JButton("Sil");
		btn_delClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicId.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					if (Helper.confirm("sure")) {
						try {
							boolean control = clinic.delClinic(Integer.valueOf(fld_clinicId.getText()));
							if (control) {
								Helper.showMsg("success");
								fld_clinicId.setText(null);
								updateClinicModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delClinic.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
		btn_delClinic.setBounds(276, 150, 155, 34);
		w_clinic.add(btn_delClinic);
		
		JComboBox cmb_selectDoctor = new JComboBox();
		cmb_selectDoctor.setBounds(278, 266, 152, 34);
		for(int i = 0 ; i < bashekim.getDoctorList().size() ; i++) {
			cmb_selectDoctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(),bashekim.getDoctorList().get(i).getName()));
		}
		w_clinic.add(cmb_selectDoctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow>=0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicId = Integer.parseInt(selClinic);
					Item doctorItem = (Item) cmb_selectDoctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicId);
						if(control) {
							Helper.showMsg("success");
						}else {
							Helper.showMsg("error");
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen poliklinik seçiniz...");
				}
			}
		});
		btn_addWorker.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
		btn_addWorker.setBounds(278, 305, 152, 34);
		w_clinic.add(btn_addWorker);
		
		JLabel lblPoliklinik_1 = new JLabel("Poliklinik");
		lblPoliklinik_1.setFont(new Font("Cascadia Mono", Font.PLAIN, 17));
		lblPoliklinik_1.setBounds(277, 192, 155, 22);
		w_clinic.add(lblPoliklinik_1);
		
		JButton btn_selWorker = new JButton("Ekle");
		btn_selWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);

				} else {
					Helper.showMsg("lutfen bir poliklinik secin");

				}
			}
		});
		btn_selWorker.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
		btn_selWorker.setBounds(277, 214, 153, 34);
		w_clinic.add(btn_selWorker);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tablo_doktor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getTckn();
			doctorData[2] = bashekim.getDoctorList().get(i).getName();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
