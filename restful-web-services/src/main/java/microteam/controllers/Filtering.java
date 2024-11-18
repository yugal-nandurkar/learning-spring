package microteam.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import microteam.generic.FilterBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController// Step 34.2
public class Filtering { // Static & Dynamic Filtering

    @GetMapping("/filtering") // Step 35.1
    // FilterBean: Step 35.2
    public FilterBean filtering(){
        // Step 36
        FilterBean filterBean = new FilterBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("BeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return filterBean; // tut returns mappingJacksonValue
    }

    // FilterBean: Step 34.3

    @GetMapping("/filtering-list")
    //public List<FilterBean> filteringList(){ // Step 35.3
    public MappingJacksonValue filteringList(){ // Step 37
        List<FilterBean> list = Arrays.asList(new FilterBean("value1", "value2", "value3"),
                new FilterBean("value4", "value5", "value6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("BeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
    // FilterBean: Step 35.4
    // User: Step 38

}
