package dgodek.company.GUI;

import javafx.scene.control.Alert;

class WindowThrowingWarnings {
    void getWarningBox(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
