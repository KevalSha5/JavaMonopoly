package board.gui;

import board.exceptions.HousingError;
import board.places.places.Street;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlacesItem extends JPanel {

    private int width;
    private int height;
    private JPanel landingPanel;
    private JPanel developmentPanel;
    private JPanel namePanel;
    private JLabel priceLabel;
    private JLabel nameLabel;
    private Dimension placesDimension;
    protected BuildableItem buildableItemOnBoard;
    protected Street streetOfBuildableItem;

    public PlacesItem() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        placesDimension = new Dimension((int) screenSize.getWidth() / 11, (int) screenSize.getHeight() / 11);
        setSize(placesDimension);
        setPreferredSize(placesDimension);

        this.width = (int) placesDimension.getWidth();
        this.height = (int) placesDimension.getHeight();

        setLayout(new BorderLayout());

        add(setTopPanel(""), BorderLayout.NORTH);
        add(setBottomPanel(), BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }

    public PlacesItem(boolean buildingPurpose) {
        setLayout(new BorderLayout());

        placesDimension = new Dimension(175, 100);
        setSize(placesDimension);
        setPreferredSize(placesDimension);
        this.width = (int) placesDimension.getWidth();
        this.height = (int) placesDimension.getHeight();

        add(setTopPanel(""), BorderLayout.NORTH);
        add(setDevelopmentalBottomPanel(), BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }

    public void addToken(JLabel label) {

        landingPanel.add(label);
        landingPanel.revalidate();
        landingPanel.repaint();
    }

    public void removeToken(JLabel label) {

        landingPanel.remove(label);
        landingPanel.revalidate();
        landingPanel.repaint();
    }

    public void addHouse() {

        JLabel house = new JLabel();
        house.setOpaque(true);
        house.setBackground(Color.green);
        house.setBorder(BorderFactory.createLineBorder(Color.black));
        house.setPreferredSize(new Dimension(developmentPanel.getPreferredSize().width / 4 - 1, developmentPanel.getPreferredSize().height));
        developmentPanel.add(house);
        developmentPanel.revalidate();
        developmentPanel.repaint();



    }

    public void removeHouse() {
        developmentPanel.remove(0);
        developmentPanel.revalidate();
        developmentPanel.repaint();

    }

    public void addHotel() {

        JLabel hotel = new JLabel();
        hotel.setOpaque(true);
        hotel.setBackground(Color.red);
        hotel.setBorder(BorderFactory.createLineBorder(Color.black));
        hotel.setPreferredSize(new Dimension((int) (developmentPanel.getPreferredSize().width / 4), developmentPanel.getPreferredSize().height));
        developmentPanel.add(hotel);
        developmentPanel.revalidate();
        developmentPanel.repaint();
    }

    public void removeHotel() {
        developmentPanel.remove(0);
        developmentPanel.revalidate();
        developmentPanel.repaint();
    }

    private JPanel setTopPanel(String name) {

        JPanel topPanel = new JPanel(new BorderLayout());

        nameLabel = new JLabel();
        nameLabel.setFont(new Font(null, Font.PLAIN, 12));

        namePanel = new JPanel();
        namePanel.add(nameLabel);

        developmentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        developmentPanel.setPreferredSize(new Dimension(this.width, (int) (this.height * .08)));

        topPanel.add(namePanel, BorderLayout.CENTER);
        topPanel.add(developmentPanel, BorderLayout.SOUTH);

        return topPanel;
    }

    private JPanel setBottomPanel() {

        JPanel bottomPanel = new JPanel(new BorderLayout());

        landingPanel = new JPanel();

        priceLabel = new JLabel();
        priceLabel.setFont(new Font(null, Font.PLAIN, 10));

        JPanel pricePanel = new JPanel();
        pricePanel.add(priceLabel);

        bottomPanel.add(landingPanel, BorderLayout.CENTER);
        bottomPanel.add(pricePanel, BorderLayout.SOUTH);

        return bottomPanel;
    }

    private JPanel setDevelopmentalBottomPanel() {

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel actionPanel = new JPanel();

        JButton addHouseButton = new JButton("+ House");
        JButton removeHouseButton = new JButton("- House");

        addHouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    streetOfBuildableItem.buildHouse();
                    buildableItemOnBoard.addHouse();
                    addHouse();
                } catch (HousingError ex) {
                    JOptionPane.showMessageDialog(null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        removeHouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    streetOfBuildableItem.sellHouse();
                    buildableItemOnBoard.removeHouse();
                    removeHouse();
                } catch (HousingError ex) {
                    JOptionPane.showMessageDialog(null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        actionPanel.add(addHouseButton);
        actionPanel.add(removeHouseButton);


        priceLabel = new JLabel();
        priceLabel.setFont(new Font(null, Font.PLAIN, 10));

        JPanel pricePanel = new JPanel();
        pricePanel.add(priceLabel);

        bottomPanel.add(actionPanel, BorderLayout.CENTER);
        bottomPanel.add(pricePanel, BorderLayout.SOUTH);

        return bottomPanel;

    }

    protected void setPlaceName(String name) {
        nameLabel.setText(name);
    }

    protected String getPlaceName() {
        return nameLabel.getText();
    }

    protected void setPrice(int price) {
        priceLabel.setText("$" + price);
    }

    protected void setBuildingCost(int cost) {
        priceLabel.setText("Building cost: $" + cost);
    }

    protected void setStreetColor(Color c) {
        namePanel.setBackground(c);
    }
}
