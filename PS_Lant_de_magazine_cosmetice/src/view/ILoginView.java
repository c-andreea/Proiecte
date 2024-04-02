package view;

import java.awt.event.ActionListener;

public interface ILoginView {
    String getEmail();
    String getPassword();
    void displayErrorMessage(String errorMessage);
    void closeView();
    void addLoginListener(ActionListener loginListener);
}
