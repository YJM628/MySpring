package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ResourceLoader {
    private  String location;

    public ResourceLoader(String location) {
        this.location = location;
    }

    public InputStream getInputStream() throws IOException {
        URL url = this.getClass().getClassLoader().getResource(location);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
