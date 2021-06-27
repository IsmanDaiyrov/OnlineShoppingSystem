package com.company;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public final class frame extends JFrame {

    private static final long serialVersionUID = 0;
    private static final int field_width = 15;
    private static final Color background_color = new Color(255, 255, 255);
    private cart my_items;
    private JTextField my_total;

    public frame(final List<items> the_items) {
        super("Welcome!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        my_items = new cart();
        my_total = new JTextField("$0.00", field_width);
        add(makeTotalPanel(), "North");
        add(makeItemsPanel(the_items), "Center");
        add(makeCheckBoxPanel(), "South");
        pack();
        setVisible(true);
    }

    private JPanel makeTotalPanel() {
        my_total.setEditable(false);
        my_total.setEnabled(false);
        my_total.setDisabledTextColor(Color.black);
        final JPanel p = new JPanel();
        p.setBackground(Color.red);
        final JLabel l = new JLabel("ViewInvoice total");
        l.setForeground(Color.white);
        p.add(l);
        p.add(my_total);
        return p;
    }

    private JPanel makeItemsPanel(final List<items> the_items) {
        final JPanel p = new JPanel(new GridLayout(the_items.size(), 1));
        for (items item : the_items) {
            addItem(item, p);
        }
        return p;
    }

    private JPanel makeCheckBoxPanel() {
        final JPanel p = new JPanel();
        p.setBackground(Color.red);
        final JCheckBox cb = new JCheckBox("Premium Customer");
        cb.setForeground(Color.white);
        p.add(cb);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
                my_items.setDiscount(cb.isSelected());
                updateTotal();
            }
        });
        return p;
    }

    private void addItem(final items the_item, final JPanel the_panel) {

        final JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sub.setBackground(background_color);
        final JTextField quantity = new JTextField(3);
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        quantity.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
                updateItem(the_item, quantity);
                quantity.transferFocus();
            }
        });

        quantity.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent the_event) {
                updateItem(the_item, quantity);
            }

        });

        sub.add(quantity);
        final JLabel l = new JLabel(the_item.toString());
        l.setForeground(Color.black);
        sub.add(l);
        the_panel.add(sub);

    }

    private void updateItem(final items the_item, final JTextField the_quantity) {
        final String text = the_quantity.getText().trim();
        int number;
        try {
            number = Integer.parseInt(text);
            if (number < 0) {
                throw new NumberFormatException();
            }
        } catch (final NumberFormatException e) {
            number = 0;
            the_quantity.setText("");
        }
        my_items.add(new itemOrder(the_item, number));
        updateTotal();
    }

    private void updateTotal() {
        final double total = my_items.getTotal();
        my_total.setText(NumberFormat.getCurrencyInstance().format(total));
    }
}
