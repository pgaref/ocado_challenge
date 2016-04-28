package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * An example to show how we can create a dynamic chart.
*/
public class TimeSeriesChart extends JPanel{

    /** The time series data. */
    private TimeSeries series;
    
	// origin at top left
	int width = 300;
	int height = 200;
    
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public JPanel initTimeSeriesChart(final String title) {
        this.series = new TimeSeries(title, Millisecond.class);

        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        final JPanel content = new JPanel(new BorderLayout());
        final ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
        
        chartPanel.setMaximumDrawWidth(Integer.MAX_VALUE);
        chartPanel.setMaximumDrawHeight(Integer.MAX_VALUE);
        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
        
        //Sets the size of whole window (JPanel)
        chartPanel.setPreferredSize(new Dimension(width, height));
        return content;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Routing Strategy Stats",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );

        final XYPlot plot = result.getXYPlot();

        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);

        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);

        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.0, 50.0);
        yaxis.setAutoRange(true);

        return result;
    }
    
    /**
	 * @return the series
	 */
	public TimeSeries getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(TimeSeries series) {
		this.series = series;
	}

}  