package io.restall.http;

import org.gradle.api.UncheckedIOException;
import org.gradle.internal.impldep.org.apache.commons.io.IOUtils;
import org.gradle.internal.impldep.org.simpleframework.http.Request;
import org.gradle.internal.impldep.org.simpleframework.http.Response;
import org.gradle.internal.impldep.org.simpleframework.http.core.Container;
import org.gradle.internal.impldep.org.simpleframework.http.resource.Context;
import org.gradle.internal.impldep.org.simpleframework.http.resource.Index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import static io.restall.http.ContentTypeHandler.getFileExtension;

public class ConfigurableFileServerContainer implements Container {
    private final Context context;

    public ConfigurableFileServerContainer(Context context) {
        this.context = context;
    }

    public void handle(Request req, Response resp) {
        Index requestIndex = this.context.getIndex(req.getTarget());
        File targetFile = requestIndex.getFile();
        if(!targetFile.exists()) {
            resp.setCode(404);

            try {
                resp.getPrintStream().println(String.format("File \'%s\' does not exist", new Object[]{targetFile.getAbsolutePath()}));
                resp.commit();
            } catch (IOException var14) {
                throw new UncheckedIOException(var14);
            }
        }

        String fileName = requestIndex.getName();
        String contentType = ContentTypeHandler.getContentType(getFileExtension(fileName));

        resp.set("Content-Type", contentType);
        OutputStream output = null;

        try {
            output = resp.getOutputStream();
            if(contentType.startsWith("text/")) {
                resp.set("Content-Encoding", Charset.defaultCharset().name());
                FileReader e = new FileReader(requestIndex.getFile());
                IOUtils.copy(e, output);
                IOUtils.closeQuietly(e);
            } else {
                FileInputStream e1 = new FileInputStream(requestIndex.getFile());
                IOUtils.copy(e1, output);
                IOUtils.closeQuietly(e1);
            }
        } catch (IOException var12) {
            throw new UncheckedIOException(var12);
        } finally {
            IOUtils.closeQuietly(output);
        }

    }



}

