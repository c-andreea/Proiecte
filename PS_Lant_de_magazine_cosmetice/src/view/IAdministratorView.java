package view;

import model.User;
import presenter.AdministratorPresenter;

import javax.swing.table.TableModel;
import java.util.List;

public interface IAdministratorView {
    void setPresenter(AdministratorPresenter presenter);
    void setTableModel(TableModel model);
}
