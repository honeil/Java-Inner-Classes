package oneil.hilary.zipcode;

import java.io.Closeable;

/**
 * Created by hilaryoneil on 2/3/17.
 */
interface Connection extends Closeable {

    String connect();

    String getIP();

    int getPort();

    void close();
}
