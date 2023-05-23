package proitsw.com;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import jakarta.persistence.Column;
import org.apache.commons.lang3.StringUtils;
import com.vaadin.flow.function.ValueProvider;



import java.awt.*;

@Route("cities")
public class CityListView extends VerticalLayout {

    private final CityService cityService;  // your service class

    public CityListView(CityService cityService) {
        this.cityService = cityService;
        Grid<City> grid = new Grid<>(City.class);
        ListDataProvider<City> dataProvider = new ListDataProvider<>(cityService.findAll(0, Integer.MAX_VALUE));
        grid.setDataProvider(dataProvider);

        // configure filters
        HeaderRow filterRow = grid.appendHeaderRow();
        for (Grid.Column<City> col : grid.getColumns()) {
            TextField filterField = new TextField();
            ValueProvider<City, String> valueProvider = col::getValue;
            filterField.addValueChangeListener(event -> {
                dataProvider.addFilter(
                        city -> StringUtils.containsIgnoreCase(valueProvider.apply(city), filterField.getValue()));
            });
            filterRow.getCell(col).setComponent(filterField);
        }

        add(grid);
        setSizeFull();
    }
}
