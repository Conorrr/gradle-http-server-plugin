package io.restall.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class ContentTypeHandler {

    private final static Map<String, String> CONTENT_TYPES = new HashMap<>();

    static {
        CONTENT_TYPES.put("html", "text/html");
        CONTENT_TYPES.put("htm", "text/html");
        CONTENT_TYPES.put("shtml", "text/html");
        CONTENT_TYPES.put("css", "text/css");
        CONTENT_TYPES.put("xml", "text/xml");
        CONTENT_TYPES.put("rss", "text/xml");
        CONTENT_TYPES.put("gif", "image/gif");
        CONTENT_TYPES.put("jpeg", "image/jpeg");
        CONTENT_TYPES.put("jpg", "image/jpeg");
        CONTENT_TYPES.put("js", "application/x-javascript");
        CONTENT_TYPES.put("txt", "text/plain");
        CONTENT_TYPES.put("htc", "text/x-component");
        CONTENT_TYPES.put("mml", "text/mathml");
        CONTENT_TYPES.put("png", "image/png");
        CONTENT_TYPES.put("ico", "image/x-icon");
        CONTENT_TYPES.put("jng", "image/x-jng");
        CONTENT_TYPES.put("wbmp", "image/vnd.wap.wbmp");
        CONTENT_TYPES.put("jar", "application/java-archive");
        CONTENT_TYPES.put("war", "application/java-archive");
        CONTENT_TYPES.put("ear", "application/java-archive");
        CONTENT_TYPES.put("hqx", "application/mac-binhex40");
        CONTENT_TYPES.put("pdf", "application/pdf");
        CONTENT_TYPES.put("cco", "application/x-cocoa");
        CONTENT_TYPES.put("jardiff", "application/x-java-archive-diff");
        CONTENT_TYPES.put("jnlp", "application/x-java-jnlp-file");
        CONTENT_TYPES.put("run", "application/x-makeself");
        CONTENT_TYPES.put("pl", "application/x-perl");
        CONTENT_TYPES.put("pm", "application/x-perl");
        CONTENT_TYPES.put("prc", "application/x-pilot");
        CONTENT_TYPES.put("pdb", "application/x-pilot");
        CONTENT_TYPES.put("rar", "application/x-rar-compressed");
        CONTENT_TYPES.put("rpm", "application/x-redhat-package-manager");
        CONTENT_TYPES.put("sea", "application/x-sea");
        CONTENT_TYPES.put("swf", "application/x-shockwave-flash");
        CONTENT_TYPES.put("sit", "application/x-stuffit");
        CONTENT_TYPES.put("tcl", "application/x-tcl");
        CONTENT_TYPES.put("tk", "application/x-tcl");
        CONTENT_TYPES.put("der", "application/x-x509-ca-cert");
        CONTENT_TYPES.put("pem", "application/x-x509-ca-cert");
        CONTENT_TYPES.put("crt", "application/x-x509-ca-cert");
        CONTENT_TYPES.put("xpi", "application/x-xpinstall");
        CONTENT_TYPES.put("zip", "application/zip");
        CONTENT_TYPES.put("deb", "application/octet-stream");
        CONTENT_TYPES.put("bin", "application/octet-stream");
        CONTENT_TYPES.put("exe", "application/octet-stream");
        CONTENT_TYPES.put("dll", "application/octet-stream");
        CONTENT_TYPES.put("dmg", "application/octet-stream");
        CONTENT_TYPES.put("eot", "application/octet-stream");
        CONTENT_TYPES.put("iso", "application/octet-stream");
        CONTENT_TYPES.put("img", "application/octet-stream");
        CONTENT_TYPES.put("msi", "application/octet-stream");
        CONTENT_TYPES.put("msp", "application/octet-stream");
        CONTENT_TYPES.put(" msm", "application/octet-stream");
        CONTENT_TYPES.put("mp3", "audio/mpeg");
        CONTENT_TYPES.put("ra", "audio/x-realaudio");
        CONTENT_TYPES.put("mpeg mpg", "video/mpeg");
        CONTENT_TYPES.put("mov", "video/quicktime");
        CONTENT_TYPES.put("flv", "video/x-flv");
        CONTENT_TYPES.put("avi", "video/x-msvideo");
        CONTENT_TYPES.put("wmv", "video/x-ms-wmv");
        CONTENT_TYPES.put("asx", "video/x-ms-asf");
        CONTENT_TYPES.put("asf", "video/x-ms-asf");
        CONTENT_TYPES.put("mng", "video/x-mng");
    }

    static String getContentType(String extension) {
        if (extension == null) {
            return "application/octetstream";
        }

        return Optional.ofNullable(CONTENT_TYPES.get(extension)).orElse("application/octetstream");
    }

    static String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return null;
        }

        return filename.substring(filename.lastIndexOf('.'));
    }


}
