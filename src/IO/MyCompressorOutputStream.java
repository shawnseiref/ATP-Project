package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    MyCompressorOutputStream(OutputStream o) {
        out = o;
    }

    @Override
    public void write(int b) throws IOException {

    }
}
