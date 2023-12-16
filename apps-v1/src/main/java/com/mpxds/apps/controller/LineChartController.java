package com.mpxds.apps.controller;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mpxds.apps.service.DataService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Component
@ManagedBean
@ViewScoped
public class LineChartController {
    //
    @Autowired
    private DataService dataService;

    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        //
        lineModel = new LineChartModel();
        
        LineChartSeries s = new LineChartSeries();
        s.setLabel("População");

        dataService.getLineChartData().forEach(s::set);

        lineModel.addSeries(s);
        lineModel.setLegendPosition("e");

        Axis y = lineModel.getAxis(AxisType.Y);
        
        y.setMin(0.5);
        y.setMax(700);
        y.setLabel("Milhões");

        Axis x = lineModel.getAxis(AxisType.X);
        
        x.setMin(0);
        x.setMax(7);
        x.setTickInterval("1");
        x.setLabel("Número Ansiosos");
    }

    public LineChartModel getLineModel() {
        //
        return lineModel;
    }
}