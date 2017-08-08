package connectors.opentsdb;

import org.apache.hadoopts.data.series.Messreihe;

import java.net.MalformedURLException;
import java.util.Vector;

/**
 * Created by kamir on 04.08.17.
 */
public class OpenTSDBClient {

    OpenTSDBConnector connector = null;

    public static OpenTSDBClient getClient() throws MalformedURLException {


        OpenTSDBClient client = new OpenTSDBClient();

        try {
            client.connector = new OpenTSDBConnector();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return client;
    }

    public static OpenTSDBClient getClient(String host) throws MalformedURLException {


        OpenTSDBClient client = new OpenTSDBClient();

        try {
            client.connector = new OpenTSDBConnector(host);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return client;
    }

    public static OpenTSDBClient getColocatedLocalClient() throws MalformedURLException {


        OpenTSDBClient client = new OpenTSDBClient();

        try {
            client.connector = new OpenTSDBConnector("127.0.0.1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return client;
    }


    public void storeMessreiheNow(Messreihe row) throws Exception {
        connector.storeMessreihe(row, connector);
    }

    public void storeMessreiheWithOffset(Messreihe row, long t0) throws Exception {
        connector.storeMessreihe(row, connector, t0);
    }

    public void storeBucketData(Vector<Messreihe> bucketData, long t0) throws Exception {
        connector.storeBucketData(bucketData, connector, t0);
    }

    public Messreihe readTimeSeriesForMetric( String metric ) throws Exception {
        String aggregator = "sum";
        Messreihe m = connector.readTimeSeriesForMetric( metric , aggregator, connector );
        return m;
    }

}