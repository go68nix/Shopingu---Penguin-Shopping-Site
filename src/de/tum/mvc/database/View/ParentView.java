package de.tum.mvc.database.View;

import de.tum.mvc.database.Controller.Controller;
import de.tum.mvc.database.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.util.List;

public class ParentView extends VBox implements Observer {

    protected final Controller controller;
    protected final ObservableList<Product> products;

    public ParentView(Controller controller, List<Product> productsList) {
        this.controller = controller;
        this.products = FXCollections.observableArrayList(productsList);
        for (Product product : products) {
            product.addObserver(this);
        }
    }

    // TODO: Implement addProduct() method
    public void addProduct(Product product) {
        if(products.stream().filter(x->x.getId()==product.getId()).count()==0){
            products.add(product);
        } else {
            int a=products.indexOf(products.stream().filter(x->x.getId()==product.getId()).findFirst().orElse(null));
            products.set(a,product);
        }
    }

    protected void showAlert(Alert.AlertType alertType, String s, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType.name());
        alert.setHeaderText(s);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // TODO: Implement update() method
    @Override
    public void update() {
        products.clear();
        products.addAll(controller.getProducts());
    }
}
