package org.snobot.nt.auton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

public class AutonPanel extends JPanel
{

    private JButton mSaveButton;
    private JTextArea mTextArea;
    private JPanel mBoolPanel;

    /**
     * Constructor.
     */
    public AutonPanel()
    {
        setPreferredSize(new Dimension(300, 150));
        setLayout(new BorderLayout());

        mTextArea = new JTextArea("No auton received");
        mSaveButton = new JButton("Send & Save");
        mBoolPanel = new JPanel();
        JPanel buttonAndSuccesPanel = new JPanel();

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(mTextArea);

        buttonAndSuccesPanel.setLayout(new GridLayout(2, 1));
        buttonAndSuccesPanel.add(mSaveButton);
        buttonAndSuccesPanel.add(mBoolPanel);

        this.add(pane, BorderLayout.CENTER);
        this.add(buttonAndSuccesPanel, BorderLayout.SOUTH);
        setParseSuccess(false);
    }

    /**
     * Registers a listener to the button.
     * 
     * @param aListener
     *            The listener
     */
    public void addSaveListener(ActionListener aListener)
    {
        mSaveButton.addActionListener(aListener);
    }

    /**
     * Gets the text area.
     * 
     * @return The text area
     */
    public JTextArea getTextArea()
    {
        return this.mTextArea;
    }

    /**
     * Sets if the robot successfully parsed the command.
     * 
     * @param aParseSuccess
     *            True if it was successful
     */
    public void setParseSuccess(boolean aParseSuccess)
    {
        if (aParseSuccess)
        {
            mBoolPanel.setBackground(Color.GREEN);
        }
        else
        {
            mBoolPanel.setBackground(Color.RED);
        }
    }

    /**
     * Adds a listener to the text area for changes.
     * 
     * @param aListener
     *            The listener
     */
    public void addTextChangedListener(DocumentListener aListener)
    {
        mTextArea.getDocument().addDocumentListener(aListener);
    }
}
