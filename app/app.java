package app;

import java.io.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.event.*;

import com.itextpdf.text.Document;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;


public class app extends javax.swing.JFrame{
	private static NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("th", "TH"));
	private static Order thisOrder = null;
	private int[] value = {0, 0, 0, 0};
	private String[] info = {null, null, null, null, null, null, null, null};
	private String[] card = {null, null, null};
	private String[] shipTo = {"Apple Store, Mahidol University\n999 Phuttamonthon 4 Road, Salaya, ", "73170", "Nakhon Pathom", "THAILAND"};
//	private String[] info = {"p", "k", "4", "10160", "b", "t", "p@p.p", "0881060571"};
//	private String[] card = {"4938497587456734", "12/23", "234"};
	private static OnlineCustomer c;
	private Font inputfieldFont = new Font(".AppleSystemUIFont", Font.PLAIN, 16);
	private String[] args;
	Border emptyBorder = BorderFactory.createEmptyBorder();
	
	public app() {
		
		try {
	         // Set the look and feel to Nimbus
	         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

		
        // Set the size and title of the JFrame
		SpinnerModel model1 = new SpinnerNumberModel(0, 0, 100, 1);
		SpinnerModel model2 = new SpinnerNumberModel(0, 0, 100, 1);
		SpinnerModel model3 = new SpinnerNumberModel(0, 0, 100, 1);
		SpinnerModel model4 = new SpinnerNumberModel(0, 0, 100, 1);

        setSize(1132, 655);
        setTitle("Apple Store");
        setResizable(false);
        URL iconURL = getClass().getResource("/asset/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the visible components
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);

        // Create a JLabel with the background image
        URL backgroundURL = getClass().getResource("/asset/MacBook Pro 16_ - 1.png");
        ImageIcon background = new ImageIcon(backgroundURL);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Create a JButton to place over the background image
        JSpinner mbp14spn = new JSpinner(model1);
        mbp14spn.setBounds(240, 490, 56, 26);
        JSpinner mba13spn = new JSpinner(model2);
        mba13spn.setBounds(490, 490, 56, 26);
        JSpinner ipp11spn = new JSpinner(model3);
        ipp11spn.setBounds(740, 490, 56, 26);
        JSpinner ipa10spn = new JSpinner(model4);
        ipa10spn.setBounds(990, 490, 56, 26);
        
        URL backgroundBtnURL = getClass().getResource("/asset/Frame1.png");
        ImageIcon backgroundBtn = new ImageIcon(backgroundBtnURL);
        JButton checkoutBtn = new JButton(backgroundBtn);
        checkoutBtn.setBounds(853, 550, 200, 50);
        checkoutBtn.setOpaque(true);
        
        JLabel totalAmnt = new JLabel();
        totalAmnt.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 24)); // Set font size and style
        totalAmnt.setForeground(Color.WHITE);
        totalAmnt.setBounds(100, 525, 1000, 100);
        
        JLabel warningLabel = new JLabel();
        warningLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 16)); // Set font size and style
        warningLabel.setForeground(Color.RED);
        warningLabel.setBounds(500, 525, 1000, 100);
        warningLabel.setText("Do not let the bag empty.");
        warningLabel.setVisible(false);
        
        
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                 value[0] = (Integer) mbp14spn.getValue();
                 value[1] = (Integer) mba13spn.getValue();
                 value[2] = (Integer) ipp11spn.getValue();
                 value[3] = (Integer) ipa10spn.getValue();
                int sum = value[0]*73900 + value[1]*43900 + value[2]*32900 + value[3]*23900;
                totalAmnt.setText("Total: " + formatter.format(sum));
            }
        };
        
        
        checkoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(value[0] + value[1] + value[2] + value[3] > 0) {
            		
            		// Create a new JFrame for the next page
                    page2();

                    // Hide the current page
                    warningLabel.setVisible(false);
                    setVisible(false);
            	}else {
            		warningLabel.setVisible(true);
            	}
                
            }
        });
        
        mbp14spn.addChangeListener(listener);
        mba13spn.addChangeListener(listener);
        ipp11spn.addChangeListener(listener);
        ipa10spn.addChangeListener(listener);
        
        

        // Add the JLabel to the content pane
        contentPane.add(backgroundLabel);

        // Add the JButton to the content pane, positioned on top of the JLabel
        contentPane.add(mbp14spn);
        contentPane.add(mba13spn);
        contentPane.add(ipp11spn);
        contentPane.add(ipa10spn);
        contentPane.add(checkoutBtn);
        contentPane.add(totalAmnt);
        contentPane.add(warningLabel);
//        contentPane.add(panel);
//        contentPane.add(modalButton);
        
        contentPane.setComponentZOrder(mbp14spn, 0);
        contentPane.setComponentZOrder(mba13spn, 0);
        contentPane.setComponentZOrder(ipp11spn, 0);
        contentPane.setComponentZOrder(ipa10spn, 0);
        contentPane.setComponentZOrder(checkoutBtn, 0);
        contentPane.setComponentZOrder(totalAmnt, 0);
        contentPane.setComponentZOrder(warningLabel, 0);
//        contentPane.setComponentZOrder(panel, 0);
//        contentPane.setComponentZOrder(modalButton, 0);
        // Set the JPanel as the main content pane of the JFrame
        setContentPane(contentPane);

        // Display the JFrame
        setVisible(true);
    }
	
	private void page2() {
//		boolean flag = true;
		JFrame nextFrame = new JFrame("Apple Store");
        nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextFrame.setResizable(false);
        nextFrame.setSize(1132, 655);
        URL iconURL = getClass().getResource("/asset/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        nextFrame.setIconImage(icon.getImage());
        
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        
        URL backgroundURL = getClass().getResource("/asset/page2.png");
        ImageIcon background = new ImageIcon(backgroundURL);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        
        URL backgrouBtnURL = getClass().getResource("/asset/Frame2.png");
        ImageIcon backgroundBtn = new ImageIcon(backgrouBtnURL);
        JButton checkoutBtn = new JButton(backgroundBtn);
        checkoutBtn.setBounds(853, 550, 200, 50);
        checkoutBtn.setOpaque(true);
        
        JLabel totalAmnt = new JLabel();
        totalAmnt.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12)); // Set font size and style
        totalAmnt.setForeground(Color.BLACK);
        totalAmnt.setBounds(950, -20, 1000, 100);
        int sum = value[0]*73900 + value[1]*43900 + value[2]*32900 + value[3]*23900;
        totalAmnt.setText("Total: " + formatter.format(sum));
        
        /**
         * info[0] = (String) fnameField.getText();
                    info[1] = (String) lnameField.getText();
                    info[2] = (String) addressField.getText();
                    info[3] = (String) zipField.getText();
                    info[4] = (String) provinceField.getText();
                    info[5] = (String) countryField.getText();
                    info[6] = (String) emailField.getText();
                    info[7] = (String) phoneField.getText();
                    card[0] = (String) cardnumberField.getText();
                    card[1] = (String) expField.getText();
                    card[2] = (String) cvvField.getText();
         */
        
        JTextField fnameField = new JTextField(20);
        fnameField.setOpaque(false); // set the text field to be transparent
        fnameField.setBounds(75, 185, 375, 50);
        fnameField.setBackground(new Color(0,0,0,0));
        fnameField.setForeground(Color.WHITE);
        fnameField.setFont(inputfieldFont);
        fnameField.setCaretColor(Color.WHITE);
        fnameField.setBorder(emptyBorder);
        fnameField.setText(info[0]);
        fnameField.setDocument(new JTextFieldLimit(50));
        
        
        JTextField lnameField = new JTextField(20);
        lnameField.setOpaque(false); // set the text field to be transparent
        lnameField.setBounds(75, 240, 375, 50);
        lnameField.setBackground(new Color(0,0,0,0));
        lnameField.setForeground(Color.WHITE);
        lnameField.setFont(inputfieldFont);
        lnameField.setCaretColor(Color.WHITE);
        lnameField.setBorder(emptyBorder);
        lnameField.setText(info[1]);
        lnameField.setDocument(new JTextFieldLimit(50));
        
        JTextField addressField = new JTextField(20);
        addressField.setOpaque(false); // set the text field to be transparent
        addressField.setBounds(75, 295, 375, 50);
        addressField.setBackground(new Color(0,0,0,0));
        addressField.setForeground(Color.WHITE);
        addressField.setFont(inputfieldFont);
        addressField.setCaretColor(Color.WHITE);
        addressField.setBorder(emptyBorder);
        addressField.setText(info[2]);
        addressField.setDocument(new JTextFieldLimit(100));
        
        JTextField zipField = new JTextField(5);
        zipField.setOpaque(false); // set the text field to be transparent
        zipField.setBounds(75, 350, 182, 50);
        zipField.setBackground(new Color(0,0,0,0));
        zipField.setForeground(Color.WHITE);
        zipField.setFont(inputfieldFont);
        zipField.setCaretColor(Color.WHITE);
        zipField.setBorder(emptyBorder);
        zipField.setText(info[3]);
        zipField.setDocument(new JTextFieldLimit(5));
        
        JTextField provinceField = new JTextField(20);
        provinceField.setOpaque(false); // set the text field to be transparent
        provinceField.setBounds(275, 350, 182, 50);
        provinceField.setBackground(new Color(0,0,0,0));
        provinceField.setForeground(Color.WHITE);
        provinceField.setFont(inputfieldFont);
        provinceField.setCaretColor(Color.WHITE);
        provinceField.setBorder(emptyBorder);
        provinceField.setText(info[4]);
        provinceField.setDocument(new JTextFieldLimit(20));
        
        JTextField countryField = new JTextField(20);
        countryField.setOpaque(false); // set the text field to be transparent
        countryField.setBounds(75, 405, 375, 50);
        countryField.setBackground(new Color(0,0,0,0));
        countryField.setForeground(Color.WHITE);
        countryField.setFont(inputfieldFont);
        countryField.setCaretColor(Color.WHITE);
        countryField.setBorder(emptyBorder);
        countryField.setText(info[5]);
        countryField.setDocument(new JTextFieldLimit(20));
        
        JTextField emailField = new JTextField(20);
        emailField.setOpaque(false); // set the text field to be transparent
        emailField.setBounds(590, 185, 375, 50);
        emailField.setBackground(new Color(0,0,0,0));
        emailField.setForeground(Color.WHITE);
        emailField.setFont(inputfieldFont);
        emailField.setCaretColor(Color.WHITE);
        emailField.setBorder(emptyBorder);
        emailField.setText(info[6]);
        emailField.setDocument(new JTextFieldLimit(50));
        
        JTextField phoneField = new JTextField(12);
        phoneField.setOpaque(false); // set the text field to be transparent
        phoneField.setBounds(590, 240, 375, 50);
        phoneField.setBackground(new Color(0,0,0,0));
        phoneField.setForeground(Color.WHITE);
        phoneField.setFont(inputfieldFont);
        phoneField.setCaretColor(Color.WHITE);
        phoneField.setBorder(emptyBorder);
        phoneField.setText(info[7]);
        phoneField.setDocument(new JTextFieldLimit(10));
        
        JTextField cardnumberField = new JTextField(16);
        cardnumberField.setOpaque(false); // set the text field to be transparent
        cardnumberField.setBounds(625, 350, 375, 50);
        cardnumberField.setBackground(new Color(0,0,0,0));
        cardnumberField.setForeground(Color.WHITE);
        cardnumberField.setFont(inputfieldFont);
        cardnumberField.setCaretColor(Color.WHITE);
        cardnumberField.setBorder(emptyBorder);
        cardnumberField.setText(card[0]);
        cardnumberField.setDocument(new JTextFieldLimit(16));

        JTextField expField = new JTextField(4);
        expField.setOpaque(false); // set the text field to be transparent
        expField.setBounds(625, 405, 200, 50);
        expField.setBackground(new Color(0,0,0,0));
        expField.setForeground(Color.WHITE);
        expField.setFont(inputfieldFont);
        expField.setCaretColor(Color.WHITE);
        expField.setBorder(emptyBorder);
        expField.setText(card[1]);
        expField.setDocument(new JTextFieldLimit(5));
        
        JTextField cvvField = new JTextField(3);
        cvvField.setOpaque(false); // set the text field to be transparent
        cvvField.setBounds(865, 405, 100, 50);
        cvvField.setBackground(new Color(0,0,0,0));
        cvvField.setForeground(Color.WHITE);
        cvvField.setFont(inputfieldFont);
        cvvField.setCaretColor(Color.WHITE);
        cvvField.setBorder(emptyBorder);
        cvvField.setText(card[2]);
        cvvField.setDocument(new JTextFieldLimit(3));
        
        JLabel warningLabel = new JLabel();
        warningLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 16)); // Set font size and style
        warningLabel.setForeground(Color.RED);
        warningLabel.setBounds(500, 525, 1000, 100);
        warningLabel.setText("Please fullfill every field correctly.");
        warningLabel.setVisible(false);
        
        URL backgroundVISAURL = getClass().getResource("/asset/VISA.png");
        ImageIcon backgroundVISA = new ImageIcon(backgroundVISAURL);
        JButton VISABtn = new JButton(backgroundVISA);
        VISABtn.setBounds(916, 354, 50, 32);
        VISABtn.setOpaque(true);
        VISABtn.setVisible(false);
        
        URL backgroundAEURL = getClass().getResource("/asset/AE.png");
        ImageIcon backgroundAE = new ImageIcon(backgroundAEURL);
        JButton AEBtn = new JButton(backgroundAE);
        AEBtn.setBounds(916, 354, 50, 32);
        AEBtn.setOpaque(true);
        AEBtn.setVisible(false);

        URL backgroundJCBURL = getClass().getResource("/asset/JCB.png");
        ImageIcon backgroundJCB = new ImageIcon(backgroundJCBURL);
        JButton JCBBtn = new JButton(backgroundJCB);
        JCBBtn.setBounds(916, 354, 50, 32);
        JCBBtn.setOpaque(true);
        JCBBtn.setVisible(false);
        
        URL backgroundMCURL = getClass().getResource("/asset/MC.png");
        ImageIcon backgroundMC = new ImageIcon(backgroundMCURL);
        JButton MCBtn = new JButton(backgroundMC);
        MCBtn.setBounds(916, 354, 50, 32);
        MCBtn.setOpaque(true);
        MCBtn.setVisible(false);
        
        JLabel invalidLabel = new JLabel();
        invalidLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 12)); // Set font size and style
        invalidLabel.setForeground(Color.RED);
        invalidLabel.setBounds(916, 320, 50, 100);
        invalidLabel.setText("Invalid");
        invalidLabel.setVisible(false);
        
        JLabel invalidZIPLabel = new JLabel();
        invalidZIPLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 12)); // Set font size and style
        invalidZIPLabel.setForeground(Color.RED);
        invalidZIPLabel.setBounds(195, 320, 50, 100);
        invalidZIPLabel.setText("Invalid");
        invalidZIPLabel.setVisible(false);
        
        JLabel invalidMailLabel = new JLabel();
        invalidMailLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 12)); // Set font size and style
        invalidMailLabel.setForeground(Color.RED);
        invalidMailLabel.setBounds(910, 152, 50, 100);
        invalidMailLabel.setText("Invalid");
        invalidMailLabel.setVisible(false);
        
        JLabel invalidPhoneLabel = new JLabel();
        invalidPhoneLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 12)); // Set font size and style
        invalidPhoneLabel.setForeground(Color.RED);
        invalidPhoneLabel.setBounds(910, 208, 50, 100);
        invalidPhoneLabel.setText("Invalid");
        invalidPhoneLabel.setVisible(false);
        
        JLabel invalidEXPLabel = new JLabel();
        invalidEXPLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 12)); // Set font size and style
        invalidEXPLabel.setForeground(Color.RED);
        invalidEXPLabel.setBounds(780, 376, 50, 100);
        invalidEXPLabel.setText("Invalid");
        invalidEXPLabel.setVisible(false);
        
        JLabel invalidCVVLabel = new JLabel();
        invalidCVVLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 12)); // Set font size and style
        invalidCVVLabel.setForeground(Color.RED);
        invalidCVVLabel.setBounds(910, 376, 50, 100);
        invalidCVVLabel.setText("Invalid");
        invalidCVVLabel.setVisible(false);
        
        JRadioButton option1 = new JRadioButton();
        option1.setVisible(true);
        option1.setBounds(439, 486, 125, 67);
        option1.setForeground(Color.WHITE);
        option1.setSelected(true);
        JRadioButton option2 = new JRadioButton();
        option2.setVisible(true);
        option2.setBounds(565, 486, 125, 67);
        option2.setForeground(Color.WHITE);
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        
        // add radio buttons to the panel
        
        URL backgroundBackBtnURL = getClass().getResource("/asset/Frame 12.png");
        ImageIcon backgroundBackBtn = new ImageIcon(backgroundBackBtnURL);
        JButton backBtn = new JButton(backgroundBackBtn);
        backBtn.setBounds(0, 550, 200, 50);
        backBtn.setOpaque(true);
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	dispose();
            	nextFrame.setVisible(false);
            	setVisible(true);
            }
        });
              
        cardnumberField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                detectWord();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                detectWord();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                detectWord();
            }

            private void detectWord() {
                String number = cardnumberField.getText();
                if (CreditCard.checkType(number) == CreditCard.CardType.VISA) {
                	
                	VISABtn.setVisible(false);
                	AEBtn.setVisible(false);
                	JCBBtn.setVisible(false);
                	MCBtn.setVisible(false);
                	invalidLabel.setVisible(false);
                	
                	VISABtn.setVisible(true);
                	
                }else if(CreditCard.checkType(number) == CreditCard.CardType.AMERICANEXPRESS) {
                	VISABtn.setVisible(false);
                	AEBtn.setVisible(false);
                	JCBBtn.setVisible(false);
                	MCBtn.setVisible(false);
                	invalidLabel.setVisible(false);
                	
                	AEBtn.setVisible(true);
                }else if(CreditCard.checkType(number) == CreditCard.CardType.JCB) {
                	VISABtn.setVisible(false);
                	AEBtn.setVisible(false);
                	JCBBtn.setVisible(false);
                	MCBtn.setVisible(false);
                	invalidLabel.setVisible(false);
                	
                	JCBBtn.setVisible(true);
                }else if(CreditCard.checkType(number) == CreditCard.CardType.MASTERCARD) {
                	VISABtn.setVisible(false);
                	AEBtn.setVisible(false);
                	JCBBtn.setVisible(false);
                	MCBtn.setVisible(false);
                	invalidLabel.setVisible(false);
                	
                	MCBtn.setVisible(true);
                }else if(number.length() >= 15 && CreditCard.checkType(number) == null){
                	VISABtn.setVisible(false);
                	AEBtn.setVisible(false);
                	JCBBtn.setVisible(false);
                	MCBtn.setVisible(false);
                	invalidLabel.setVisible(false);
                	
                	invalidLabel.setVisible(true);
                	
                }else{
                	VISABtn.setVisible(false);
                	AEBtn.setVisible(false);
                	JCBBtn.setVisible(false);
                	MCBtn.setVisible(false);
                	invalidLabel.setVisible(false);
                }
            }
        });
        
        checkoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		info[0] = (String) fnameField.getText();
                    info[1] = (String) lnameField.getText();
                    info[2] = (String) addressField.getText();
                    info[3] = (String) zipField.getText();
                    info[4] = (String) provinceField.getText();
                    info[5] = (String) countryField.getText();
                    info[6] = (String) emailField.getText();
                    info[7] = (String) phoneField.getText();
                    card[0] = (String) cardnumberField.getText();
                    card[1] = (String) expField.getText();
                    card[2] = (String) cvvField.getText();
            	
            	
            		// Create a new JFrame for the next page
                if((option1.isSelected() || option2.isSelected()) && !info[0].equals("") && !info[1].equals("") && !info[2].equals("") && !info[3].equals("") && !info[4].equals("") && !info[5].equals("") && !info[6].equals("") && !info[7].equals("") && !card[0].equals("") && !card[1].equals("") && !card[2].equals("") && CreditCard.checkType(card[0]) != null && isZIP(info[3]) && isMail(info[6]) && isPhone(info[7]) && isEXP(card[1]) && isCVV(card[2])) {
//                	for(int i = 0; i < 8; i++) {
//                		System.out.println(info[i]);
//                	}
//                	&& isZIP(info[3]) && isMail(info[6]) && isPhone(info[7]) && isEXP(card[1]) && isCVV(card[2])
                	args = new String[] {"CARD", CreditCard.checkType(card[0]).toString(), card[0]};
                    page3();
                    
                    if(option1.isSelected()) {
                    	shipTo[0] = info[2];
                    	shipTo[1] = info[3];
                    	shipTo[2] = info[4];
                    	shipTo[3] = info[5];
                    }
                    
                    // Hide the current page
                    invalidZIPLabel.setVisible(false);
                    invalidMailLabel.setVisible(false);
                    invalidPhoneLabel.setVisible(false);
                    invalidEXPLabel.setVisible(false);
                    invalidCVVLabel.setVisible(false);
                    warningLabel.setVisible(false);
                    nextFrame.setVisible(false);
                }else {
                	if(CreditCard.checkType(card[0]) == null) {invalidLabel.setVisible(true);}else {invalidLabel.setVisible(false);}
                	if(!isZIP(info[3])) {invalidZIPLabel.setVisible(true);}else {invalidZIPLabel.setVisible(false);}
                	if(!isMail(info[6])) {invalidMailLabel.setVisible(true);}else {invalidMailLabel.setVisible(false);}
                	if(!isPhone(info[7])) {invalidPhoneLabel.setVisible(true);}else {invalidPhoneLabel.setVisible(false);}
                	if(!isEXP(card[1])) {invalidEXPLabel.setVisible(true);}else {invalidEXPLabel.setVisible(false);}
                	if(!isCVV(card[2])) {invalidCVVLabel.setVisible(true);}else {invalidCVVLabel.setVisible(false);}
                	warningLabel.setVisible(true);
                }
            }
        });
        
        
        
        contentPane.add(backgroundLabel);
        contentPane.add(checkoutBtn);
        contentPane.add(fnameField);
        contentPane.add(lnameField);
        contentPane.add(addressField);
        contentPane.add(zipField);
        contentPane.add(provinceField);
        contentPane.add(countryField);
        contentPane.add(emailField);
        contentPane.add(phoneField);
        contentPane.add(totalAmnt);
        contentPane.add(warningLabel);
        contentPane.add(cardnumberField);
        contentPane.add(expField);
        contentPane.add(cvvField);
        contentPane.add(VISABtn);
        contentPane.add(AEBtn);
        contentPane.add(JCBBtn);
        contentPane.add(MCBtn);
        contentPane.add(invalidLabel);
        contentPane.add(invalidZIPLabel);
        contentPane.add(invalidMailLabel);
        contentPane.add(invalidPhoneLabel);
        contentPane.add(invalidEXPLabel);
        contentPane.add(invalidCVVLabel);
        contentPane.add(backBtn);
        contentPane.add(option1);
        contentPane.add(option2);
        
        contentPane.setComponentZOrder(checkoutBtn, 0);
        contentPane.setComponentZOrder(fnameField, 0);
        contentPane.setComponentZOrder(lnameField, 0);
        contentPane.setComponentZOrder(addressField, 0);
        contentPane.setComponentZOrder(zipField, 0);
        contentPane.setComponentZOrder(provinceField, 0);
        contentPane.setComponentZOrder(countryField, 0);
        contentPane.setComponentZOrder(emailField, 0);
        contentPane.setComponentZOrder(phoneField, 0);
        contentPane.setComponentZOrder(totalAmnt, 0);
        contentPane.setComponentZOrder(warningLabel, 0);
        contentPane.setComponentZOrder(cardnumberField, 0);
        contentPane.setComponentZOrder(expField, 0);
        contentPane.setComponentZOrder(cvvField, 0);
        contentPane.setComponentZOrder(VISABtn, 0);
        contentPane.setComponentZOrder(AEBtn, 0);
        contentPane.setComponentZOrder(JCBBtn, 0);
        contentPane.setComponentZOrder(MCBtn, 0);
        contentPane.setComponentZOrder(invalidLabel, 0);
        contentPane.setComponentZOrder(invalidZIPLabel, 0);
        contentPane.setComponentZOrder(invalidMailLabel, 0);
        contentPane.setComponentZOrder(invalidPhoneLabel, 0);
        contentPane.setComponentZOrder(invalidEXPLabel, 0);
        contentPane.setComponentZOrder(invalidCVVLabel, 0);
        contentPane.setComponentZOrder(backBtn, 0);
        contentPane.setComponentZOrder(option1, 0);
        contentPane.setComponentZOrder(option2, 0);

        nextFrame.setContentPane(contentPane);
        nextFrame.setVisible(true);
	}
	
	private void page3() {
		int[] positiony = {106, 181, 257, 332};
		int[] positionNum = {141, 210, 292, 370};
		JLabel[] products = {null, null, null, null};
		JLabel[] numBadges = {null, null, null, null};
		JLabel[] priceBadges = {null, null, null, null};
		
		c = new OnlineCustomer(info[0] + " " + info[1], info[6], info[3]);
		thisOrder = new Order(c);
		for(int i = 0; i < 4; i++) {
			if(value[i] > 0) {
			thisOrder.addItem(i+1, value[i]);
			}
		}
		thisOrder.setPayment(args);
//		System.out.println(thisOrder.log());
		URL productIImgURL = getClass().getResource("/asset/mbp14.png");
		URL productIIImgURL = getClass().getResource("/asset/mba136.png");
		URL productIIIImgURL = getClass().getResource("/asset/ipp11.png");
		URL productIVImgURL = getClass().getResource("/asset/ipa109.png");
		ImageIcon productIImg = new ImageIcon(productIImgURL);
		ImageIcon productIIImg = new ImageIcon(productIIImgURL);
		ImageIcon productIIIImg = new ImageIcon(productIIIImgURL);
		ImageIcon productIVImg = new ImageIcon(productIVImgURL);

        // Create a new JLabel with the image
        products[0] = new JLabel(productIImg);
        products[1] = new JLabel(productIIImg);
        products[2] = new JLabel(productIIIImg);
        products[3] = new JLabel(productIVImg);
        
        for(int i = 0; i < 4; i++) {
        	numBadges[i] = new JLabel();
        	priceBadges[i] = new JLabel();
        	
        	numBadges[i].setVisible(false);
        	priceBadges[i].setVisible(false);
        	
        }
        
        products[0].setVisible(false);
        products[1].setVisible(false);
        products[2].setVisible(false);
        products[3].setVisible(false);
        
        for(int i = 0; i < thisOrder.getItems().size(); i++) {
        	int n = getBarcode(thisOrder.getItems().get(i));
        	products[n].setBounds(44, positiony[i], 1044, 75);
        	products[n].setVisible(true);
        	
        	numBadges[n].setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16));
        	numBadges[n].setText(thisOrder.getItems().get(i).getQuantity() + "");
        	numBadges[n].setBounds(696, positionNum[i], 40, 19);
        	numBadges[n].setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16)); // Set font size and style
        	numBadges[n].setForeground(Color.WHITE);
        	numBadges[n].setHorizontalAlignment(SwingConstants.CENTER);
        	numBadges[n].setVisible(true);
        	
        	priceBadges[n].setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16));
        	priceBadges[n].setText(formatter.format(thisOrder.getItems().get(i).getTotal()));
        	priceBadges[n].setBounds(850, positionNum[i], 200, 30);
        	priceBadges[n].setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16)); // Set font size and style
        	priceBadges[n].setForeground(Color.WHITE);
        	priceBadges[n].setHorizontalAlignment(SwingConstants.RIGHT);
        	priceBadges[n].setVisible(true);
        }
        
		
		JLabel totalAmnt = new JLabel();
        totalAmnt.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16)); // Set font size and style
        totalAmnt.setForeground(Color.WHITE);
        totalAmnt.setBounds(0, 40, 1132, 100);
        totalAmnt.setHorizontalAlignment(SwingConstants.CENTER);
        double sum = thisOrder.getTotalPrice() - thisOrder.getShippingFee();
        totalAmnt.setText(formatter.format(sum));
        
        JLabel totalAmnt2 = new JLabel();
        totalAmnt2.setFont(inputfieldFont); // Set font size and style
        totalAmnt2.setForeground(Color.WHITE);
        totalAmnt2.setBounds(850, 405, 200, 100);
        totalAmnt2.setHorizontalAlignment(SwingConstants.RIGHT);
        totalAmnt2.setText(formatter.format(sum));
        
        JLabel shippingLb = new JLabel();
        shippingLb.setFont(inputfieldFont); // Set font size and style
        shippingLb.setForeground(Color.WHITE);
        shippingLb.setBounds(850, 440, 200, 100);
        shippingLb.setHorizontalAlignment(SwingConstants.RIGHT);
        shippingLb.setText(formatter.format(thisOrder.getShippingFee()));
        
        JLabel sumAmnt = new JLabel();
        sumAmnt.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 16)); // Set font size and style
        sumAmnt.setForeground(Color.WHITE);
        sumAmnt.setBounds(850, 490, 200, 100);
        sumAmnt.setHorizontalAlignment(SwingConstants.RIGHT);
        sumAmnt.setText(formatter.format(thisOrder.getTotalPrice()));
        
        JLabel taxLb = new JLabel();
        taxLb.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 10)); // Set font size and style
        taxLb.setForeground(Color.WHITE);
        taxLb.setBounds(952, 518, 1000, 100);
        taxLb.setText(formatter.format(thisOrder.getTotalPrice()*7/100));
		
		JFrame nextFrame = new JFrame("Apple Store");
        nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextFrame.setResizable(false);
        nextFrame.setSize(1132, 655);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        
        URL backgroundURL = getClass().getResource("/asset/page3.png");
        ImageIcon background = new ImageIcon(backgroundURL);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        
        URL backgroundBtnURL = getClass().getResource("/asset/Frame 11.png");
        ImageIcon backgroundBtn = new ImageIcon(backgroundBtnURL);
        JButton checkoutBtn = new JButton(backgroundBtn);
        checkoutBtn.setBounds(820, 575, 230, 50);
        checkoutBtn.setOpaque(true);
        
        URL backgroundBackBtnURL = getClass().getResource("/asset/Frame 13.png");
        ImageIcon backgroundBackBtn = new ImageIcon(backgroundBackBtnURL);
        JButton backBtn = new JButton(backgroundBackBtn);
        backBtn.setBounds(0, 575, 200, 50);
        backBtn.setOpaque(true);
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	dispose();
            	nextFrame.setVisible(false);
            	setVisible(true);
            }
        });
        
        checkoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	page4();
            	nextFrame.setVisible(false);
            }
        });
        
        contentPane.add(backgroundLabel);
        contentPane.add(checkoutBtn);
        contentPane.add(totalAmnt);
        contentPane.add(totalAmnt2);
        contentPane.add(shippingLb);
        contentPane.add(sumAmnt);
        contentPane.add(taxLb);
        contentPane.add(backBtn);
        
        for(int i = 0; i < 4; i++) {
        	contentPane.add(products[i]);
        	contentPane.setComponentZOrder(products[i], 0);
        	contentPane.add(numBadges[i]);
        	contentPane.setComponentZOrder(numBadges[i], 0);
        	contentPane.add(priceBadges[i]);
        	contentPane.setComponentZOrder(priceBadges[i], 0);
        }
        
        contentPane.setComponentZOrder(checkoutBtn, 0);
        contentPane.setComponentZOrder(totalAmnt, 0);
        contentPane.setComponentZOrder(totalAmnt2, 0);
        contentPane.setComponentZOrder(shippingLb, 0);
        contentPane.setComponentZOrder(sumAmnt, 0);
        contentPane.setComponentZOrder(taxLb, 0);
        contentPane.setComponentZOrder(backBtn, 0);
        
        

        // Add some components to the next page
        URL iconURL = getClass().getResource("/asset/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        nextFrame.setIconImage(icon.getImage());

        // Make the next page visible
        nextFrame.setContentPane(contentPane);
        nextFrame.setVisible(true);
	}
	
	
	private void page4() {
		String[] products = {null, null, null, null};
		products[0] = "Z159                    MBP 14 SPG     ";
		products[1] = "Z160                    MBA 13.6 MDN   ";
		products[2] = "A356                    IPP 11 SPG     ";
		products[3] = "A357                    IPA 10.9 BLU   ";
		
		JFrame nextFrame = new JFrame("Apple Store");
        nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextFrame.setResizable(false);
        nextFrame.setSize(1132, 655);
        
//        URL URL = getClass().getResource("/asset/page3.png");
        URL iconURL = getClass().getResource("/asset/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        nextFrame.setIconImage(icon.getImage());
        
        URL backgroundURL = getClass().getResource("/asset/page4.png");
        ImageIcon background = new ImageIcon(backgroundURL);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        
        JButton backBtn = new JButton();
        backBtn.setBounds(476, 562, 179, 46);
        backBtn.setBorder(emptyBorder);
        backBtn.setOpaque(false);
        backBtn.setBackground(new Color(0,0,0,0));
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	dispose();
            	nextFrame.setVisible(false);
            	setVisible(true);
            }
        });
        
        JPanel panel = new JPanel();
        JButton modalButton = new JButton();
        modalButton.setBounds(499, 344, 134, 156);
        modalButton.setOpaque(false);
        modalButton.setBorder(emptyBorder);
        modalButton.setBackground(new Color(0,0,0,0));
        modalButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setDialogTitle("Download Invoice");
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    Document document = new Document();
                    // Create a new PdfWriter
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename + "/invoice" + c.getCustomerID() + ".pdf"));
                    com.itextpdf.text.Font invoiceFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10, com.itextpdf.text.Font.NORMAL);
                    com.itextpdf.text.Font boldInvoiceFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10, com.itextpdf.text.Font.BOLD);
                    com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);
                    com.itextpdf.text.Font infoFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 6, com.itextpdf.text.Font.ITALIC);
                    com.itextpdf.text.Font tableFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10, com.itextpdf.text.Font.NORMAL);

                    Paragraph divider = new Paragraph("_______________________________________________________________________________________", invoiceFont);
                    divider.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    Paragraph headerT = new Paragraph("ITEM NO.         DESCRIPTION          Q.ORDERED          Q.SHIPPED          UNIT PRICE         EXTENDED PRICE", boldInvoiceFont);
                    // Open the document
                    URL imageIURL = getClass().getResource("/asset/logo.png");
                    com.itextpdf.text.Image imageI = com.itextpdf.text.Image.getInstance(imageIURL);
                    
                    
                    
                    Date crDate = new Date();
                    document.open();
                    
                    Paragraph paragraph = new Paragraph();
//                    Paragraph tab = new Paragraph("               ", invoiceFont);
                    
                    Paragraph infoTxt = new Paragraph("INVOICE NO: AL" + c.getCustomerID()*543 + ", INVOICE DATE: " + crDate.toString(), infoFont);
                    infoTxt.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    
                    paragraph.add(infoTxt);
                    paragraph.add(imageI);
                    paragraph.add(new Paragraph("Apple Store, Mahidol University\n999 Phuttamonthon 4 Road, \nSalaya, Nakhon Pathom, \n73170, THAILAND", headerFont));
                    paragraph.add(divider);
                    paragraph.add(new Paragraph("[CUSTOMER NO. ]             [SALES ORDER NO. ]              [CUSTOMER P.O.NO. ]              [TERMS]\n", boldInvoiceFont));
                    paragraph.add(new Paragraph("        [" + c.getCustomerID()*1656 + "]                               [" + c.getCustomerID()*5616 + "]                               [" + info[7] + "]                    [" + CreditCard.checkType(card[0]) + " Credit Card]", tableFont));
                    paragraph.add(new Paragraph(crDate.toString() + "\n" + c.getName() + "\n" + c.getEmail() , invoiceFont));
                    paragraph.add(divider);
                    paragraph.add(new Paragraph("SOLD TO\n", boldInvoiceFont));
                    paragraph.add(new Paragraph(c.getName() + "\n" + info[2] + "\n" + info[4] + "\n" + info[5] + ", " + info[3], invoiceFont));
                    paragraph.add(divider);
                    paragraph.add(new Paragraph("SHIP TO\n", boldInvoiceFont));
                    paragraph.add(new Paragraph(c.getName() + "\n" + shipTo[0] + "\n" + shipTo[2] + "\n" + shipTo[3] + ", " + shipTo[1], invoiceFont));
                    paragraph.add(divider);
                    paragraph.add(headerT);
                    String tabb = "                       ";
                    for(int i = 0; i < thisOrder.getItems().size(); i++) {
                    	int n = getBarcode(thisOrder.getItems().get(i));
                    	int amount = thisOrder.getItems().get(i).getQuantity();
                    	String unitP = formatter.format(thisOrder.getItems().get(i).getPrice() - (thisOrder.getItems().get(i).getPrice()*7/100));
                    	String totalPwoT = formatter.format(thisOrder.getItems().get(i).getTotal() - (thisOrder.getItems().get(i).getTotal()*7/100));
                    	
                    	paragraph.add(new Paragraph(products[n] + tabb + amount + tabb + amount + tabb + unitP + tabb + totalPwoT, invoiceFont));
                    }
                    paragraph.add(new Paragraph("SHPF                    SHIPPING FEE" + tabb + 1 + tabb + 0 + tabb + formatter.format(c.getShippingFee()) + tabb + formatter.format(c.getShippingFee()), invoiceFont));
                    paragraph.add(new Paragraph("\nCOMMENT", boldInvoiceFont));
                    
                    Paragraph totalB = new Paragraph("SUB-TOTAL                 " + formatter.format(thisOrder.getTotalPrice() - (thisOrder.getTotalPrice()*7/100)), boldInvoiceFont);
                    totalB.add("\nVAT                 " + formatter.format((thisOrder.getTotalPrice()*7/100)));
                    totalB.add("\nTOTAL(VAT INCLUDED)                 " + formatter.format((thisOrder.getTotalPrice())));
                    totalB.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    
                    paragraph.add(totalB);
                    paragraph.add(divider);
                    Paragraph ackldg = new Paragraph("This is a computer generated invoice\nwhich does not require any signature", invoiceFont);
                    ackldg.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    paragraph.add(ackldg);
                    
//                    paragraph.setFont(invoiceFont);
                    // Add content to the document
                    document.add(paragraph);
                    
                    
                    // Close the document
                    document.close();
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        
        
        
        contentPane.add(backgroundLabel);
        contentPane.add(panel);
        contentPane.add(modalButton);
        contentPane.add(backBtn);
        
        contentPane.setComponentZOrder(panel, 0);
        contentPane.setComponentZOrder(modalButton, 0);
        contentPane.setComponentZOrder(backBtn, 0);
        
        nextFrame.setContentPane(contentPane);
        nextFrame.setVisible(true);
	}
	
	

    public static void main(String[] args) {
//    	System.out.println(UIManager.getSystemLookAndFeelClassName());
        app frame = new app();
    }
    
    
    
    @SuppressWarnings("serial")
	class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
    
    private static boolean isZIP(String str) {
    	if(str.length() != 5) {
    		return false;
    	}
    	try {
    		Integer.parseInt(str);
    		return true;
    	}catch(NumberFormatException e) {}
    	return false;
    }
    
    private static boolean isMail(String str) {
    	if(str.contains("@") && str.contains(".") && str.indexOf("@")>0 && str.indexOf(".")>2 && str.length()>4) {
    		return true;
    	}
    	return false;
    }
    
    private static boolean isPhone(String str) {
    	
    	if(str.length() != 10) {
    		return false;
    	}
    	try {
    		Long.parseLong(str);
    		return true;
    	}catch(NumberFormatException e) {}
    	return false;
    }
    
    private static boolean isEXP(String str) {
    	if(str.length() != 5) {
    		return false;
    	}
    	String val[] = str.split("\\/");
    	if(val[0].length() != 2 || val[1].length() != 2) {return false;}
    	try {
    		Integer.parseInt(val[0]);
    		Integer.parseInt(val[1]);		
    		return true;
    	}catch(NumberFormatException e) {}
    	return false;
    }
    
    private static boolean isCVV(String str) {
    	if(str.length() != 3) {
    		return false;
    	}
    	try {
    		Integer.parseInt(str);
    		return true;
    	}catch(NumberFormatException e) {}
    	return false;
    }
    
    private static int getBarcode(Item i) {
    	switch(i.getName()) {
    	case "MacBook Pro":
    		return 0;
    	case "MacBook Air":
    		return 1;
    	case "iPad Pro":
    		return 2;
    	case "iPad Air":
    		return 3;
    	default:
    		return -1;
    	}
    }
    



}
