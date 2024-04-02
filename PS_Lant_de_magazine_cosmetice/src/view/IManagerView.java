package view;

import presenter.ManagerPresenter;

import java.util.List;

public interface IManagerView {
    void setPresenter(ManagerPresenter presenter);
    void clearProductsTable();
     void populateManufacturers(List<String> manufacturers);

    void setProductToTableWithStoreName(String name, String s, String s1, String manufacturer, String storeName);
}
