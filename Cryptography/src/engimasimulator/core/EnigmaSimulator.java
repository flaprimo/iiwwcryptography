/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EnigmaSimulator.java
 *
 * Created on 25-mag-2009, 17.32.53
 */
package engimasimulator.core;

/**
 *
 * @author francescoburato
 */
public class EnigmaSimulator extends javax.swing.JFrame {

	private class SimpleTableModelListener implements javax.swing.event.TableModelListener {

		private javax.swing.table.TableModel model;
		private char[] current;

		public SimpleTableModelListener(javax.swing.JTable m) {
			this.model = m.getModel();
			this.current = new char[26];
			for (int i = 0; i < this.current.length; ++i) {
				this.current[i] = (this.model.getValueAt(i, 1).toString()).charAt(0);
			}
		}

		public void tableChanged(javax.swing.event.TableModelEvent evt) {
			int row = evt.getFirstRow(), col = evt.getColumn();
			if (col == 1) {
				String s = model.getValueAt(row, col).toString();
				if (s.length() != 1) {
					this.model.setValueAt(this.current[row] + "", row, col);
				} else {
					char c = (model.getValueAt(row, col).toString()).charAt(0);
					if (c < 'a' || c > 'z') {
						this.model.setValueAt(this.current[row] + "", row, col);
					} else {
						this.current[row] = c;
					}
				}
			} else {
				String s = model.getValueAt(row, col).toString();
				if(s.length()!= 1){
					this.model.setValueAt(""+ (char)('a'+row), row, col);
				} else {
					char c = (model.getValueAt(row, col).toString()).charAt(0);
					if (c < 'a' || c > 'z') {
						this.model.setValueAt(""+ (char)('a'+row), row, col);
					}
				}
			}
		}
	}

	/** Creates new form EnigmaSimulator */
	private void prepare() {
		try {
			this.enigma = new Enigma();
			this.comboFirstSet = false;
			this.currentIndexes = new int[3];
			setCombos();
			this.CifraButton.doClick();
			this.PannelloTable.getModel().addTableModelListener(new SimpleTableModelListener(this.PannelloTable));
			char[] pannello = new char[26];
			for(int i = 0; i < pannello.length;++i)
				pannello[i] = (char)('a' + i);
			this.enigma.setPanel(pannello);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	private void setSpinnerChar() {
		if (this.comboFirstSet) {
			this.spinnerChar = this.enigma.getCurrentOffset();
			this.Scamb1Spinner.setValue("" + this.spinnerChar[0]);
			this.Scamb2Spinner.setValue("" + this.spinnerChar[1]);
			this.Scamb3Spinner.setValue("" + this.spinnerChar[2]);
		}
	}

	public EnigmaSimulator() {
		initComponents();
		prepare();
	}

	public void setCombos() {
		int possibleScamb = enigma.getPossibleScamb();
		if (this.Scamb1Combo.getSelectedItem() == null && this.Scamb2Combo.getSelectedItem() == null && this.Scamb3Combo.getSelectedItem() == null) {
			for (int i = 0; i < possibleScamb; ++i) {
				this.Scamb1Combo.addItem(i);
				this.Scamb2Combo.addItem(i);
				this.Scamb3Combo.addItem(i);
			}
		}
		int sc1 = ((Integer) (this.Scamb1Combo.getSelectedItem())).intValue(),
			sc2 = ((Integer) (this.Scamb2Combo.getSelectedItem())).intValue(),
			sc3 = ((Integer) (this.Scamb3Combo.getSelectedItem())).intValue();
		while (sc1 == sc2 || sc1 == sc3 || sc2 == sc3) {
			if (sc1 == sc2) {
				sc2 = (sc2 + 1) % possibleScamb;
			}
			if (sc3 == sc2) {
				sc3 = (sc3 + 1) % possibleScamb;
			}
			if (sc3 == sc1) {
				sc3 = (sc3 + 1) % possibleScamb;
			}
		}
		this.currentIndexes[0] = sc1;
		this.currentIndexes[1] = sc2;
		this.currentIndexes[2] = sc3;
		this.Scamb1Combo.setSelectedIndex(sc1);
		this.Scamb2Combo.setSelectedIndex(sc2);
		this.Scamb3Combo.setSelectedIndex(sc3);
		this.comboFirstSet = true;
		this.enigma.setUsed(this.currentIndexes);
	}

	private char[] getOffsets() {
		char[] res = new char[3];
		res[0] = (char) ((this.Scamb1Spinner.getValue()).toString().charAt(0) - 'A' + 'a');
		res[1] = (char) ((this.Scamb2Spinner.getValue()).toString().charAt(0) - 'A' + 'a');
		res[2] = (char) ((this.Scamb3Spinner.getValue()).toString().charAt(0) - 'A' + 'a');
		return res;

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          buttonGroup1 = new javax.swing.ButtonGroup();
          jPanel1 = new javax.swing.JPanel();
          jLabel1 = new javax.swing.JLabel();
          jPanel2 = new javax.swing.JPanel();
          jLabel2 = new javax.swing.JLabel();
          Scamb1Combo = new javax.swing.JComboBox();
          jLabel3 = new javax.swing.JLabel();
          Scamb2Combo = new javax.swing.JComboBox();
          jLabel4 = new javax.swing.JLabel();
          Scamb3Combo = new javax.swing.JComboBox();
          jLabel5 = new javax.swing.JLabel();
          jPanel3 = new javax.swing.JPanel();
          jLabel6 = new javax.swing.JLabel();
          jLabel7 = new javax.swing.JLabel();
          jLabel8 = new javax.swing.JLabel();
          jLabel9 = new javax.swing.JLabel();
          Scamb1Spinner = new javax.swing.JSpinner();
          Scamb2Spinner = new javax.swing.JSpinner();
          Scamb3Spinner = new javax.swing.JSpinner();
          jSeparator1 = new javax.swing.JSeparator();
          jSeparator2 = new javax.swing.JSeparator();
          jPanel4 = new javax.swing.JPanel();
          jLabel10 = new javax.swing.JLabel();
          jScrollPane1 = new javax.swing.JScrollPane();
          PannelloTable = new javax.swing.JTable();
          PanelButton = new javax.swing.JButton();
          jSeparator3 = new javax.swing.JSeparator();
          jPanel5 = new javax.swing.JPanel();
          jScrollPane2 = new javax.swing.JScrollPane();
          SourceTextArea = new javax.swing.JTextArea();
          jSeparator4 = new javax.swing.JSeparator();
          jScrollPane3 = new javax.swing.JScrollPane();
          ReciptTextArea = new javax.swing.JTextArea();
          jSeparator5 = new javax.swing.JSeparator();
          CifraButton = new javax.swing.JRadioButton();
          DecifraButton = new javax.swing.JRadioButton();
          jLabel11 = new javax.swing.JLabel();
          jLabel12 = new javax.swing.JLabel();
          ProcessButton = new javax.swing.JButton();
          NormalizeButton = new javax.swing.JButton();
          InvertButton = new javax.swing.JButton();

          buttonGroup1.add(this.CifraButton);
          buttonGroup1.add(this.DecifraButton);

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

          jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13));
          jLabel1.setText("Impostazione Enigma");

          jLabel2.setText("Scambiatore 1");

          Scamb1Combo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Scamb1ComboActionPerformed(evt);
               }
          });

          jLabel3.setText("Scambiatore 2");

          Scamb2Combo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Scamb2ComboActionPerformed(evt);
               }
          });

          jLabel4.setText("Scambiatore 3");

          Scamb3Combo.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Scamb3ComboActionPerformed(evt);
               }
          });

          jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13));
          jLabel5.setText("Ordine Scambiatori");

          org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
          jPanel2.setLayout(jPanel2Layout);
          jPanel2Layout.setHorizontalGroup(
               jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel2Layout.createSequentialGroup()
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                         .add(jPanel2Layout.createSequentialGroup()
                              .add(jLabel2)
                              .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                              .add(Scamb1Combo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .add(jPanel2Layout.createSequentialGroup()
                              .add(jLabel3)
                              .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                              .add(Scamb2Combo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .add(jPanel2Layout.createSequentialGroup()
                              .add(jLabel4)
                              .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                              .add(Scamb3Combo, 0, 97, Short.MAX_VALUE))
                         .add(jLabel5))
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          jPanel2Layout.setVerticalGroup(
               jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                    .add(jLabel5)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel2)
                         .add(Scamb1Combo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel3)
                         .add(Scamb2Combo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel4)
                         .add(Scamb3Combo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
          );

          jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13));
          jLabel6.setText("Impostazione chiave");

          jLabel7.setText("Scambiatore 1");

          jLabel8.setText("Scambiatore 2");

          jLabel9.setText("Scambiatore 3");

          Scamb1Spinner.setModel(new javax.swing.SpinnerListModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));

          Scamb2Spinner.setModel(new javax.swing.SpinnerListModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));

          Scamb3Spinner.setModel(new javax.swing.SpinnerListModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));

          org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
          jPanel3.setLayout(jPanel3Layout);
          jPanel3Layout.setHorizontalGroup(
               jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel3Layout.createSequentialGroup()
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                         .add(jLabel6)
                         .add(jPanel3Layout.createSequentialGroup()
                              .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                   .add(jLabel8)
                                   .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                   .add(jLabel7))
                              .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                              .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                   .add(Scamb1Spinner, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                   .add(Scamb2Spinner)
                                   .add(Scamb3Spinner))))
                    .addContainerGap(46, Short.MAX_VALUE))
          );
          jPanel3Layout.setVerticalGroup(
               jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel3Layout.createSequentialGroup()
                    .add(jLabel6)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel7)
                         .add(Scamb1Spinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel8)
                         .add(Scamb2Spinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel9)
                         .add(Scamb3Spinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(736, 736, 736))
          );

          jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 13));
          jLabel10.setText("Pannello a prese multiple");

          PannelloTable.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][] {
                    {"a", "a"},
                    {"b", "b"},
                    {"c", "c"},
                    {"d", "d"},
                    {"e", "e"},
                    {"f", "f"},
                    {"g", "g"},
                    {"h", "h"},
                    {"i", "i"},
                    {"j", "j"},
                    {"k", "k"},
                    {"l", "l"},
                    {"m", "m"},
                    {"n", "n"},
                    {"o", "o"},
                    {"p", "p"},
                    {"q", "q"},
                    {"r", "r"},
                    {"s", "s"},
                    {"t", "t"},
                    {"u", "u"},
                    {"v", "v"},
                    {"w", "w"},
                    {"x", "x"},
                    {"y", "y"},
                    {"z", "z"}
               },
               new String [] {
                    "Da", "A"
               }
          ) {
               Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
               };

               public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
               }
          });
          PannelloTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
          jScrollPane1.setViewportView(PannelloTable);

          PanelButton.setText("Conferma");
          PanelButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PanelButtonActionPerformed(evt);
               }
          });

          org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
          jPanel4.setLayout(jPanel4Layout);
          jPanel4Layout.setHorizontalGroup(
               jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jLabel10)
               .add(jPanel4Layout.createSequentialGroup()
                    .add(47, 47, 47)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
               .add(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(PanelButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addContainerGap())
          );
          jPanel4Layout.setVerticalGroup(
               jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel4Layout.createSequentialGroup()
                    .add(jLabel10)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 440, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                    .add(PanelButton))
          );

          org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
               jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
               .add(jPanel1Layout.createSequentialGroup()
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
               jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel1Layout.createSequentialGroup()
                    .add(jLabel1)
                    .add(4, 4, 4)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                         .add(jPanel1Layout.createSequentialGroup()
                              .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                              .add(10, 10, 10)
                              .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                              .add(9, 9, 9)
                              .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                              .addContainerGap(217, Short.MAX_VALUE))
                         .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          );

          jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

          SourceTextArea.setColumns(20);
          SourceTextArea.setLineWrap(true);
          SourceTextArea.setRows(5);
          jScrollPane2.setViewportView(SourceTextArea);

          ReciptTextArea.setColumns(20);
          ReciptTextArea.setLineWrap(true);
          ReciptTextArea.setRows(5);
          jScrollPane3.setViewportView(ReciptTextArea);

          jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

          CifraButton.setText("Cifra");

          DecifraButton.setText("Decifra");

          jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 13));
          jLabel11.setText("Testo di partenza");

          jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 13));
          jLabel12.setText("Testo di arrivo");

          ProcessButton.setText("Avvia processo");
          ProcessButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ProcessButtonActionPerformed(evt);
               }
          });

          NormalizeButton.setText("Normalizza");
          NormalizeButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    NormalizeButtonActionPerformed(evt);
               }
          });

          InvertButton.setText("Inverti Caselle");
          InvertButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    InvertButtonActionPerformed(evt);
               }
          });

          org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
          jPanel5.setLayout(jPanel5Layout);
          jPanel5Layout.setHorizontalGroup(
               jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel5Layout.createSequentialGroup()
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                         .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                         .add(jLabel11)
                         .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                         .add(jLabel12)
                         .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                         .add(CifraButton)
                         .add(DecifraButton)
                         .add(NormalizeButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                         .add(ProcessButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                         .add(InvertButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                    .addContainerGap())
          );
          jPanel5Layout.setVerticalGroup(
               jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel5Layout.createSequentialGroup()
                    .add(jLabel11)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel12)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 258, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
               .add(jSeparator5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
               .add(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(CifraButton)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(DecifraButton)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(NormalizeButton)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(ProcessButton)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(InvertButton)
                    .addContainerGap(388, Short.MAX_VALUE))
          );

          org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                    .addContainerGap())
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents

	private void Scamb1ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Scamb1ComboActionPerformed
		// TODO add your handling code here:
		if (this.comboFirstSet && this.currentIndexes[0] != ((Integer) (this.Scamb1Combo.getSelectedItem())).intValue()) {
			this.setCombos();
			int[] vett = new int[3];
			vett[0] = ((Integer) (this.Scamb1Combo.getSelectedItem())).intValue();
			vett[1] = ((Integer) (this.Scamb2Combo.getSelectedItem())).intValue();
			vett[2] = ((Integer) (this.Scamb3Combo.getSelectedItem())).intValue();
			this.enigma.setUsed(vett);
			setSpinnerChar();
		}
	}//GEN-LAST:event_Scamb1ComboActionPerformed

	private void Scamb2ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Scamb2ComboActionPerformed
		// TODO add your handling code here:
		if (this.comboFirstSet && this.currentIndexes[1] != ((Integer) (this.Scamb2Combo.getSelectedItem())).intValue()) {
			this.setCombos();
			int[] vett = new int[3];
			vett[0] = ((Integer) (this.Scamb1Combo.getSelectedItem())).intValue();
			vett[1] = ((Integer) (this.Scamb2Combo.getSelectedItem())).intValue();
			vett[2] = ((Integer) (this.Scamb3Combo.getSelectedItem())).intValue();
			this.enigma.setUsed(vett);
			setSpinnerChar();
		}
	}//GEN-LAST:event_Scamb2ComboActionPerformed

	private void Scamb3ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Scamb3ComboActionPerformed
		// TODO add your handling code here:
		if (this.comboFirstSet && this.currentIndexes[2] != ((Integer) (this.Scamb3Combo.getSelectedItem())).intValue()) {
			this.setCombos();
			int[] vett = new int[3];
			vett[0] = ((Integer) (this.Scamb1Combo.getSelectedItem())).intValue();
			vett[1] = ((Integer) (this.Scamb2Combo.getSelectedItem())).intValue();
			vett[2] = ((Integer) (this.Scamb3Combo.getSelectedItem())).intValue();
			this.enigma.setUsed(vett);
			setSpinnerChar();
		}
	}//GEN-LAST:event_Scamb3ComboActionPerformed

	private void NormalizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NormalizeButtonActionPerformed
		// TODO add your handling code here:
		if (this.CifraButton.isSelected()) {
			this.SourceTextArea.setText(Enigma.enigmaClearTextFormatter(this.SourceTextArea.getText()));
		} else if (this.DecifraButton.isSelected()) {
			this.SourceTextArea.setText(Enigma.enigmaClearTextFormatter(this.SourceTextArea.getText()).toUpperCase());
		}
}//GEN-LAST:event_NormalizeButtonActionPerformed

	private void ProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcessButtonActionPerformed
		// TODO add your handling code here:
		if (!SourceTextArea.getText().equals("") && this.CifraButton.isSelected()) {
			this.enigma.reset();
			this.enigma.setOffsets(this.getOffsets());
			char[] c = SourceTextArea.getText().toCharArray();
			String res = "";
			for (int i = 0; i < c.length; ++i) {
				res = res + this.enigma.encodeChar(c[i]);
			}
			res = Enigma.enigmaCipherTextFormatter(res);
			ReciptTextArea.setText(res);
			this.setSpinnerChar();
		} else if (!SourceTextArea.getText().equals("") && this.DecifraButton.isSelected()) {
			this.enigma.reset();
			this.enigma.setOffsets(this.getOffsets());
			char[] c = SourceTextArea.getText().toCharArray();
			String res = "";
			for (int i = 0; i < c.length; ++i) {
				res = res + this.enigma.decodeChar(c[i]);
			}
			res = Enigma.enigmaClearTextFormatter(res);
			ReciptTextArea.setText(res);
			this.setSpinnerChar();
		}
}//GEN-LAST:event_ProcessButtonActionPerformed

	private void InvertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvertButtonActionPerformed
		// TODO add your handling code here:
		this.SourceTextArea.setText(this.ReciptTextArea.getText());
		this.ReciptTextArea.setText("");
	}//GEN-LAST:event_InvertButtonActionPerformed

	private void PanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PanelButtonActionPerformed
		// TODO add your handling code here:
		int[] control = new int[26];
		char[] panel = new char[26];
		for(int i = 0; i < control.length; ++i){
			char c = (this.PannelloTable.getValueAt(i,1).toString()).charAt(0);
			panel[i] = c;
			++control[(char)(c-'a')];
			}
		boolean ancoraBuona = true;
		for(int i = 0;ancoraBuona&& i < control.length;++i)
			ancoraBuona = control[i] == 1;
		if(!ancoraBuona)
			for(int i = 0; i < panel.length;++i)
				panel[i] = (char)('a' + i);
		this.enigma.setPanel(panel);
		panel = this.enigma.getPanel();
		for(int i = 0; i < panel.length; ++i)
			this.PannelloTable.setValueAt(panel[i], i, 1);
}//GEN-LAST:event_PanelButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new EnigmaSimulator().setVisible(true);
			}
		});
	}
	private boolean comboFirstSet;
	private int[] currentIndexes;
	private char[] spinnerChar;
	private Enigma enigma;
     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JRadioButton CifraButton;
     private javax.swing.JRadioButton DecifraButton;
     private javax.swing.JButton InvertButton;
     private javax.swing.JButton NormalizeButton;
     private javax.swing.JButton PanelButton;
     private javax.swing.JTable PannelloTable;
     private javax.swing.JButton ProcessButton;
     private javax.swing.JTextArea ReciptTextArea;
     private javax.swing.JComboBox Scamb1Combo;
     private javax.swing.JSpinner Scamb1Spinner;
     private javax.swing.JComboBox Scamb2Combo;
     private javax.swing.JSpinner Scamb2Spinner;
     private javax.swing.JComboBox Scamb3Combo;
     private javax.swing.JSpinner Scamb3Spinner;
     private javax.swing.JTextArea SourceTextArea;
     private javax.swing.ButtonGroup buttonGroup1;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel3;
     private javax.swing.JLabel jLabel4;
     private javax.swing.JLabel jLabel5;
     private javax.swing.JLabel jLabel6;
     private javax.swing.JLabel jLabel7;
     private javax.swing.JLabel jLabel8;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JPanel jPanel3;
     private javax.swing.JPanel jPanel4;
     private javax.swing.JPanel jPanel5;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JScrollPane jScrollPane3;
     private javax.swing.JSeparator jSeparator1;
     private javax.swing.JSeparator jSeparator2;
     private javax.swing.JSeparator jSeparator3;
     private javax.swing.JSeparator jSeparator4;
     private javax.swing.JSeparator jSeparator5;
     // End of variables declaration//GEN-END:variables
}
