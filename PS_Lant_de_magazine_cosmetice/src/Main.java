
import view.LoginView;
import presenter.LoginPresenter;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        new LoginPresenter(loginView);

        loginView.setVisible(true);
    }
}
