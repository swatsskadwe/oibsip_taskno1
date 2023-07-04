import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class ReservationSystem {
    private static int pnrCounter = 1;
    private Map<Integer, Reservation> reservations;

    public ReservationSystem() {
        reservations = new HashMap<>();
    }

    public void makeReservation(String name, String contactNumber, String trainNumber, String classType, String dateOfJourney, String origin, String destination) {
        Reservation reservation = new Reservation(name, contactNumber, trainNumber, classType, dateOfJourney, origin, destination);
        reservations.put(pnrCounter, reservation);

        JOptionPane.showMessageDialog(null, "Reservation successfully created with PNR: " + pnrCounter);
        pnrCounter++;
    }

    public void cancelReservation(int pnrNumber) {
        Reservation reservation = reservations.get(pnrNumber);
        if (reservation != null) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this reservation?", "Cancellation Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                reservations.remove(pnrNumber);
                JOptionPane.showMessageDialog(null, "Reservation with PNR " + pnrNumber + " has been canceled.");
            } else {
                JOptionPane.showMessageDialog(null, "Cancellation process aborted.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Reservation not found for PNR " + pnrNumber);
        }
    }
}

class Reservation {
    private String name;
    private String contactNumber;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String origin;
    private String destination;

    public Reservation(String name, String contactNumber, String trainNumber, String classType, String dateOfJourney, String origin, String destination) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nContact Number: " + contactNumber +
                "\nTrain Number: " + trainNumber +
                "\nClass Type: " + classType +
                "\nDate of Journey: " + dateOfJourney +
                "\nOrigin: " + origin +
                "\nDestination: " + destination;
    }
}

public class OnlineReservationSystem extends JFrame implements ActionListener {
    private ReservationSystem reservationSystem;
    private JTextField nameField, contactField, trainNumberField, classTypeField, dateField, originField, destinationField;
    private JButton reservationButton, cancelButton;

    public OnlineReservationSystem() {
        reservationSystem = new ReservationSystem();

        setTitle("Online Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 20);
        add(nameField);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(20, 50, 100, 20);
        add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(120, 50, 200, 20);
        add(contactField);

        JLabel trainNumberLabel = new JLabel("Train Number:");
        trainNumberLabel.setBounds(20, 80, 100, 20);
        add(trainNumberLabel);

        trainNumberField = new JTextField();
        trainNumberField.setBounds(120, 80, 200, 20);
        add(trainNumberField);

        JLabel classTypeLabel = new JLabel("Class Type:");
        classTypeLabel.setBounds(20, 110, 100, 20);
        add(classTypeLabel);

        classTypeField = new JTextField();
        classTypeField.setBounds(120, 110, 200, 20);
        add(classTypeField);

        JLabel dateLabel = new JLabel("Date of Journey:");
        dateLabel.setBounds(20, 140, 100, 20);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(120, 140, 200, 20);
        add(dateField);

        JLabel originLabel = new JLabel("Origin:");
        originLabel.setBounds(20, 170, 100, 20);
        add(originLabel);

        originField = new JTextField();
        originField.setBounds(120, 170, 200, 20);
        add(originField);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(20, 200, 100, 20);
        add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(120, 200, 200, 20);
        add(destinationField);

        reservationButton = new JButton("Make Reservation");
        reservationButton.setBounds(80, 230, 140, 30);
        reservationButton.addActionListener(this);
        add(reservationButton);

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(220, 230, 140, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineReservationSystem::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reservationButton) {
            String name = nameField.getText();
            String contactNumber = contactField.getText();
            String trainNumber = trainNumberField.getText();
            String classType = classTypeField.getText();
            String dateOfJourney = dateField.getText();
            String origin = originField.getText();
            String destination = destinationField.getText();

            reservationSystem.makeReservation(name, contactNumber, trainNumber, classType, dateOfJourney, origin, destination);

            // Clear input fields after reservation is made
            nameField.setText("");
            contactField.setText("");
            trainNumberField.setText("");
            classTypeField.setText("");
            dateField.setText("");
            originField.setText("");
            destinationField.setText("");
        } else if (e.getSource() == cancelButton) {
            String pnrNumberString = JOptionPane.showInputDialog(null, "Enter PNR number:");
            if (pnrNumberString != null && !pnrNumberString.isEmpty()) {
                int pnrNumber = Integer.parseInt(pnrNumberString);
                reservationSystem.cancelReservation(pnrNumber);
            }
        }
    }
}
