package ui;

import model.ChequingAccount;
import model.Event;
import model.EventLog;
import model.ListOfChequingAccount;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents the GUI of an ATM Machine
public class AtmMachineGUI extends JFrame implements ActionListener, ListSelectionListener {
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private JTextField nameField;
    private JTextField balanceField;
    private JButton addButton;
    private JButton removeButton;
    private JList<String> accountList;
    private ArrayList<ChequingAccount> accounts;
    private DefaultListModel<String> accountModel;
    private GridBagConstraints gb;
    private ListOfChequingAccount chequingAccounts;

    private JButton saveButton;
    private JButton loadButton;
    private static final String CHEQ = "./data/chequing.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ImageIcon smile;
    private ImageIcon frown;
    private JLabel smileLabel;
    private JLabel frownlabel;
    private JFrame smileFrame;
    private JFrame frownFrame;

    // EFFECTS: constructs the gui
    public AtmMachineGUI() {
        super("Atm Machine");
        init();
        setLayout(new GridBagLayout());
        gb = new GridBagConstraints();
        layoutGUI();
        setButtons(addButton, removeButton, saveButton, loadButton); // todo
        setGUI();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event el : EventLog.getInstance()) {
                    System.out.println(el.getDescription());
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: initializes the fields of the application
    public void init() {
        nameLabel = new JLabel("Name:");
        balanceLabel = new JLabel("Balance:");
        nameField = new JTextField(20);
        balanceField = new JTextField(10);
        addButton = new JButton("Add Account");
        removeButton = new JButton("Remove Account");
        saveButton = new JButton("Save Accounts");
        loadButton = new JButton("Load Accounts");
        accountModel = new DefaultListModel<>();
        chequingAccounts = new ListOfChequingAccount("Accounts");
        accountList = new JList<>(accountModel);
        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountList.addListSelectionListener(this);
        accounts = new ArrayList<>();
        jsonWriter = new JsonWriter(CHEQ);
        jsonReader = new JsonReader(CHEQ);
        smile = new ImageIcon("./data/smile.png");
        frown = new ImageIcon("./data/frown.png");
        smileLabel = new JLabel(smile);
        frownlabel = new JLabel(frown);
        smileFrame = new JFrame("Smile");
        frownFrame = new JFrame("Frown");
    }

    // MODIFIES: this
    // EFFECTS: create the layout of the GUI
    public void layoutGUI() {
        firstRow();
        secondRow();
        thirdRow();
        fourthRow();
        fifthRow();

    }

    // EFFECTS: creates name label and text field
    public void firstRow() {
        gb.gridx = 0;
        gb.gridy = 0;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(nameLabel, gb);

        gb.gridx = 1;
        gb.gridy = 0;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(nameField, gb);
    }

    // EFFECTS: creates balance label and text field
    public void secondRow() {
        gb.gridx = 0;
        gb.gridy = 1;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(balanceLabel, gb);

        gb.gridx = 1;
        gb.gridy = 1;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(balanceField, gb);
    }

    // EFFECTS: creates add and remove buttons
    public void thirdRow() {
        gb.gridx = 0;
        gb.gridy = 2;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(addButton, gb);

        gb.gridx = 1;
        gb.gridy = 2;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(removeButton, gb);
    }

    // EFFECTS: creates save and load buttons
    public void fourthRow() {
        gb.gridx = 0;
        gb.gridy = 3;
        gb.anchor = GridBagConstraints.LINE_END;
        gb.insets = new Insets(10, 10, 0, 0);
        add(saveButton, gb);

        gb.gridx = 1;
        gb.gridy = 3;
        gb.anchor = GridBagConstraints.LINE_START;
        gb.insets = new Insets(10, 0, 0, 10);
        add(loadButton, gb);
    }

    // EFFECTS: creates box for account list
    public void fifthRow() {
        gb.gridx = 0;
        gb.gridy = 4;
        gb.gridwidth = 2;
        gb.fill = GridBagConstraints.BOTH;
        gb.weightx = 1.0;
        gb.weighty = 1.0;
        gb.insets = new Insets(10, 10, 10, 10);
        add(new JScrollPane(accountList), gb);
    }

    // EFFECTS: set action listeners for buttons
    public void setButtons(JButton addButton, JButton removeButton, JButton saveButton, JButton loadButton) {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    // EFFECTS: sets the properties of the GUI
    public void setGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addFunction();
            displaySmile();
        }
        if (e.getSource() == removeButton) {
            removeFunction();
            displayFrown();
        }
        if (e.getSource() == saveButton) {
            saveFunction();
        }
        if (e.getSource() == loadButton) {
            loadFunction();
        }
    }

    // EFFECTS: displays a smile image in a separate window
    public void displaySmile() {
        smileFrame.getContentPane().add(smileLabel);
        smileFrame.pack();
        smileFrame.setVisible(true);
    }

    // EFFECTS: displays a frown image in a separate window
    public void displayFrown() {
        frownFrame.getContentPane().add(frownlabel);
        frownFrame.pack();
        frownFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds account to list and displays it
    public void addFunction() {
        String name = nameField.getText();
        double balance = Double.parseDouble(balanceField.getText());
        ChequingAccount account = new ChequingAccount(name, balance);
        chequingAccounts.addAccount(account);
        accounts.add(account);
        accountModel.addElement(account.toString());
        nameField.setText("");
        balanceField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: removes account and takes it off gui
    public void removeFunction() {
        int index = accountList.getSelectedIndex();
        ChequingAccount ca = accounts.get(index);
        chequingAccounts.removeAccountSpecific(ca);
        accounts.remove(index);
        accountModel.removeElementAt(index);
    }

    // EFFECTS: makes list of type ListOfChequingAccount
    public ListOfChequingAccount loca() {
        ListOfChequingAccount loca = new ListOfChequingAccount("Accounts");
        for (ChequingAccount ca : accounts) {
            loca.addAccount(ca);
        }
        return loca;
    }

    // MODIFIES: CHEQ
    // EFFECTS: saves accounts to JSON file
    public void saveFunction() {
        try {
            jsonWriter.open();
            jsonWriter.writeChequing(loca());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // EFFECT: loads accounts from JSON file
    public void loadFunction() {
        try {
            ListOfChequingAccount loca = jsonReader.readCheq();
            int size = loca.length();
            for (int i = 0; i < size; i++) {
                ChequingAccount ca = loca.getAccount(i);
                accounts.add(ca);
                accountModel.addElement(ca.toString());
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (accountList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }
}
