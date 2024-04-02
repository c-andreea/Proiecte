package presenter;

import model.repository.ProductService;
import model.repository.StoreService;
import model.User;
import model.repository.UserService;
import view.AdministratorView;
import view.ILoginView; // Adjusted import
import view.EmployeeView1;
import view.ManagerView;
//import EmployeePresenter;

public class LoginPresenter {
    private ILoginView view;
    private UserService userService;
    private ProductService productService;
    private StoreService storeService;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.userService = new UserService();
        this.productService = new ProductService();
        this.storeService = new StoreService();
        addLoginListener();
    }

    private void addLoginListener() {
        view.addLoginListener(e -> doLogin());
    }

    private void doLogin() {
        String email = view.getEmail();
        String password = view.getPassword();
        User user = userService.authenticate(email, password);

        if (user != null) {
            view.closeView();

            switch (user.getRole()) {
                case "Manager":

                    ManagerView managerView = new ManagerView();
                    ManagerPresenter managerPresenter = new ManagerPresenter(managerView, productService);
                    managerView.setPresenter(managerPresenter);
                    managerView.setVisible(true);
                    break;
                case "Employee":

                    EmployeeView1 employeeView = new EmployeeView1();
                    EmployeePresenter employeePresenter = new EmployeePresenter(employeeView, productService, storeService, user);
                    employeeView.setPresenter(employeePresenter);
                    employeePresenter.initStoreData();
                    employeePresenter.loadProductsForStore();
                    employeeView.setVisible(true);
                    break;
                case "Administrator":

                    AdministratorView administratorView = new AdministratorView();
                    AdministratorPresenter administratorPresenter = new AdministratorPresenter(administratorView, userService);
                    administratorView.setPresenter(administratorPresenter);
                    administratorPresenter.displayAllUsers();
                    administratorView.setVisible(true);
                    break;
                default:
                    view.displayErrorMessage("Unsupported role");
                    break;
            }
        } else {
            view.displayErrorMessage("Login failed");
        }
    }


}
