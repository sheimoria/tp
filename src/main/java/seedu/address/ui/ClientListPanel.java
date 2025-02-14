package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.client.SortCriteria;

/**
 * Panel containing the list of clients.
 */
public class ClientListPanel extends UiPart<Region> {
    private static final String FXML = "ClientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);

    private final ObservableList<Client> clientList;

    @FXML
    private Label clientCount;
    @FXML
    private Label sortMethod;
    @FXML
    private ListView<Client> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ClientListPanel(ObservableList<Client> clientList) {
        super(FXML);
        assert clientList != null;
        this.clientList = clientList;
        clientCount.setText(String.format("%d Clients", clientList.size()));
        sortMethod.setText("Sorted by: Default");
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new ClientListViewCell());
    }

    public void setClientCount() {
        clientCount.setText(String.format("%d Clients", clientList.size()));
    }

    public void setSortCriteria(SortCriteria criteria) {
        sortMethod.setText(String.format("Sorted by: %s", criteria));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class ClientListViewCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClientCard(client, getIndex() + 1).getRoot());
            }
        }
    }

}
