package proitsw.com;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import jakarta.persistence.Column;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

@Route("cities")
public class CityListView extends VerticalLayout {

    private final CityService cityService;  // your service class

    public CityListView(CityService cityService) {
        this.cityService = cityService;
        Grid<City> grid = new Grid<>(City.class);

        // Fetch data from backend with defined page size
        DataProvider<City, Void> dataProvider = DataProvider.fromCallbacks(
                query -> cityService.findAll(query.getOffset(), query.getLimit()).stream(),
                query -> Math.toIntExact(cityService.count())
        );

        grid.setDataProvider(dataProvider);
        grid.setPageSize(10); // Page size set to 10

        // configure filters
        HeaderRow filterRow = grid.appendHeaderRow();
        for (Column<City> col : grid.getColumns()) {
            TextField filterField = new TextField();
            filterField.addValueChangeListener(event -> {
                dataProvider.addFilter(
                        city -> StringUtils.containsIgnoreCase(col.getKey(), filterField.getValue()));
            });
            filterRow.getCell(col).setComponent(filterField);
        }

        add(grid);
        setSizeFull();
    }
}
