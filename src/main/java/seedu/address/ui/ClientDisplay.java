package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.client.Client;
import seedu.address.model.policy.Policy;

/**
 * An UI component that displays information of select {@code Client} in the right panel
 */
public class ClientDisplay extends UiPart<Region> {

    private static final String FXML = "ClientDisplayPanel.fxml";
    private final Client client;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label birthday;
    @FXML
    private Label lastContacted;
    @FXML
    private Label email;
    @FXML
    private ListView<Policy> policyListView;
    @FXML
    private Label note;
    @FXML
    private VBox clientDisplayCard;

    /**
     * Creates a {@code ClientDisplay} with the given {@code Client} to display.
     */
    public ClientDisplay(Client client) {
        super(FXML);
        this.client = client;
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        email.setText(client.getEmail().value);
        address.setText(client.getAddress().value);
        if (client.getBirthday() != null) {
            birthday.setText(client.getBirthday().toString());
        } else {
            birthday.setText("-");
        }
        if (client.getLastContacted() != null) {
            lastContacted.setText("Last contacted: " + client.getLastContacted().toString());
        } else {
            lastContacted.setText("Last contacted: -");
        }
        policyListView.setItems(client.getPolicies().asUnmodifiableObservableList());
        policyListView.setCellFactory(listView -> new PolicyListViewCell());
        clientDisplayCard.getChildren().add(policyListView);
        note.setText(client.getNote().value);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Policy} using a {@code PolicyCard}.
     */
    class PolicyListViewCell extends ListCell<Policy> {
        @Override
        protected void updateItem(Policy policy, boolean empty) {
            super.updateItem(policy, empty);

            if (empty || policy == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PolicyCard(policy, getIndex() + 1).getRoot());
            }
        }
    }
}

