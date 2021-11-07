package com.cza.test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class UI{
	private JFrame jframe;
	private JPanel jPanel;
	public UI(XYSeries series) {
		super();
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"thread-time graph", // chart title
				"number of threads", // x axis label
				"run time(ms)", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
 
		jframe=new ChartFrame("", chart);
		jframe.pack();
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;
	}
	public void dispose() {
		jframe.dispose();
	}
	public JPanel getPanel() {
		return jPanel;
	}

	public JFrame getFrame() {
		return jframe;
	}
	
}
