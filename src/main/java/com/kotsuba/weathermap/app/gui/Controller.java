package com.kotsuba.weathermap.app.gui;

import com.kotsuba.weathermap.app.gui.TypeInfo;
import com.kotsuba.weathermap.core.WeatherMapException;
import com.kotsuba.weathermap.core.api.Methods;
import com.kotsuba.weathermap.core.data.WeatherData;
import com.kotsuba.weathermap.core.data.WeatherDataImpl;
import com.kotsuba.weathermap.core.entity.Favorites;
import com.kotsuba.weathermap.core.entity.WeatherInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    private Favorites favorites = null;
    private WeatherData data = new WeatherDataImpl();
    private List<WeatherInfo> weatherInfo = new ArrayList<>();
    private TypeInfo typeInfo = new TypeInfo();
    private String selectedCountry;
    private ObservableList<String> favoritesList = null;

    @FXML
    Label status;

    @FXML
    ScrollPane scrollPane;

    @FXML
    GridPane pane;

    @FXML
    ComboBox<String> country;

    @FXML
    ComboBox<String> type;

    @FXML
    public void onSearch() {
        try {
            pane.getChildren().clear();
            status.setTextFill(Color.BLACK);
            status.setText("...");
            selectedCountry = country.getEditor().getText();
            if(!isCorrectSymbol(selectedCountry)){
                status.setText("Допустимы только символы латинского алфавита");
            }else {
                if ((type.getSelectionModel().getSelectedIndex()) != -1) {
                    if ((type.getSelectionModel().getSelectedIndex()) == 0) {
                        weatherInfo = data.getToday(selectedCountry);
                    } else if ((type.getSelectionModel().getSelectedIndex()) == 1) {
                        weatherInfo = data.getDays5(selectedCountry);
                    } else {
                        weatherInfo = data.getDays16(selectedCountry);

                    }
                } else {
                    status.setText("Выберите период");
                }
                int i = 0;
                scrollPane.setVvalue(0);
                for (WeatherInfo weather : weatherInfo) {
                    pane.add(new ImageView(new Image(Methods.getImgPath(weather))), 0, i);
                    pane.add(new Button(weather.toString()), 1, i);
                    pane.getRowConstraints().add(new RowConstraints(160));
                    pane.setAlignment(Pos.CENTER);
                    i++;
                }
            }
        } catch (WeatherMapException ex) {
            status.setTextFill(Color.RED);
            status.setText(ex.getMessage());
            pane.getChildren().clear();
        }
    }

    @FXML
    public void onAddFavorites() {
        try {
            status.setText("...");
            favorites.addCountry(country.getEditor().getText());
            favoritesList = FXCollections.observableArrayList(favorites.getCountryList());
            country.setItems(favoritesList);
        } catch (WeatherMapException ex) {
            status.setTextFill(Color.RED);
            status.setText(ex.getMessage());
            pane.getChildren().clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            pane.setHgap(10);
            pane.setVgap(10);
            ObservableList<String> typeList = FXCollections.observableArrayList(typeInfo.getTypeList());
            type.setItems(typeList);
            favorites = Favorites.getInstanse();
            favoritesList = FXCollections.observableArrayList(favorites.getCountryList());
            country.setItems(favoritesList);
        } catch (WeatherMapException ex) {
            status.setTextFill(Color.RED);
            status.setText(ex.getMessage());
        }
    }
    public boolean isCorrectSymbol(String nameCountry){
        Pattern p = Pattern.compile("[a-z-A-Z]+");
        Matcher m = p.matcher(nameCountry);
        return m.matches();
    }

}
