package com.dictionary.ui;

import com.dictionary.bo.WordBO;
import com.dictionary.dao.WordDAO;
import com.dictionary.dto.WordDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WordPresentation extends JFrame {

    private final WordBO wordBLL;

    // Input fields
    private final JTextField arabicField = new JTextField(20);
    private final JTextField urduField = new JTextField(20);
    private final JTextField rootIdField = new JTextField(20);
    private final JTextField patternIdField = new JTextField(20); 

    private final JTextArea listArea = new JTextArea(12, 50);
    private final JScrollPane listScroll = new JScrollPane(listArea);

    private final JLabel statusLabel = new JLabel(" ");

    public WordPresentation() {
        wordBLL = new WordBO(new WordDAO());
        setTitle("Arabic → Urdu Dictionary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 480);
        setLocationRelativeTo(null); 
        initUI();
    }

    private void initUI() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Word"));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0;
        inputPanel.add(new JLabel("Arabic Word:"), g);
        g.gridx = 1; g.gridy = 0;
        inputPanel.add(arabicField, g);

        g.gridx = 0; g.gridy = 1;
        inputPanel.add(new JLabel("Urdu Meaning:"), g);
        g.gridx = 1; g.gridy = 1;
        inputPanel.add(urduField, g);

        g.gridx = 0; g.gridy = 2;
        inputPanel.add(new JLabel("Root ID:"), g);
        g.gridx = 1; g.gridy = 2;
        inputPanel.add(rootIdField, g);

        g.gridx = 0; g.gridy = 3;
        inputPanel.add(new JLabel("Pattern ID:"), g); 
        g.gridx = 1; g.gridy = 3;
        inputPanel.add(patternIdField, g); 

        // Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 6));
        JButton addBtn = new JButton("Add Word");
        JButton deleteBtn = new JButton("Delete Word");
        JButton updateBtn = new JButton("Update Meaning");
        JButton showListBtn = new JButton("Show List");

        buttonsPanel.add(addBtn);
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(updateBtn);
        buttonsPanel.add(showListBtn);

        listArea.setEditable(false);
        listArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        listScroll.setVisible(false);

        statusLabel.setForeground(Color.BLUE);

        JPanel top = new JPanel(new BorderLayout(6, 6));
        top.add(inputPanel, BorderLayout.CENTER);
        top.add(buttonsPanel, BorderLayout.SOUTH);

        JPanel center = new JPanel(new BorderLayout(6, 6));
        center.add(listScroll, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(statusLabel, BorderLayout.SOUTH);

        // ✅ Add Word Button Updated
        addBtn.addActionListener(e -> {
            String arabic = arabicField.getText().trim();
            String urdu = urduField.getText().trim();
            String rootText = rootIdField.getText().trim();
            String patternText = patternIdField.getText().trim();

            if (rootText.isEmpty() || patternText.isEmpty()) {
                statusLabel.setText("Enter both Root ID and Pattern ID.");
                return;
            }

            int rootId, patternId;
            try {
                rootId = Integer.parseInt(rootText);
                patternId = Integer.parseInt(patternText);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Root ID and Pattern ID must be numbers.");
                return;
            }

            String msg = wordBLL.addWord(arabic, urdu, rootId, patternId);
            statusLabel.setText(msg);

            if (msg.toLowerCase().contains("success")) {
                clearInputs();
                listScroll.setVisible(false);
                revalidate();
                repaint();
            }
        });

        // Delete Word
        deleteBtn.addActionListener(e -> {
            String arabic = arabicField.getText().trim();
            if (arabic.isEmpty()) {
                statusLabel.setText("Enter Arabic word to delete.");
                return;
            }
            WordDTO found = wordBLL.searchWord(arabic);
            if (found == null) {
                statusLabel.setText("Word not found. Cannot delete.");
                return;
            }
            int id = found.getWordID();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete '" + found.getArabicWord() + "' (ID " + id + ")?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                statusLabel.setText(wordBLL.deleteWord(id));
                clearInputs();
            }
        });

        // Update Word
        updateBtn.addActionListener(e -> {
            String arabic = arabicField.getText().trim();
            String newMeaning = urduField.getText().trim();

            if (arabic.isEmpty()) {
                statusLabel.setText("Enter Arabic word to update.");
                return;
            }

            WordDTO found = wordBLL.searchWord(arabic);
            if (found == null) {
                statusLabel.setText("Word not found.");
                return;
            }

            statusLabel.setText(wordBLL.updateWord(found.getWordID(), newMeaning));
            clearInputs();
        });

        showListBtn.addActionListener(e -> {
            List<WordDTO> words = wordBLL.getAllWords();
            StringBuilder sb = new StringBuilder();
            for (WordDTO w : words) {
                sb.append(String.format("%d - %s - %s - RootID: %d - PatternID: %d%n",
                        w.getWordID(), w.getArabicWord(), w.getUrduMeaning(), w.getRootID(), w.getPatternID()));
            }
            listArea.setText(sb.toString());
            listScroll.setVisible(true);
        });
    }

    private void clearInputs() {
        arabicField.setText("");
        urduField.setText("");
        rootIdField.setText("");
        patternIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordPresentation().setVisible(true));
    }
}
