package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    MyDecompressorInputStream(InputStream inputStream){
        in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
