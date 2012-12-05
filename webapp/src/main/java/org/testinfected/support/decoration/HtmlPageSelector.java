package org.testinfected.support.decoration;

import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.testinfected.support.util.MimeTypes;

public class HtmlPageSelector implements Selector {

    public boolean select(Response response) {
        return isOk(response) && isHtml(response);
    }

    private boolean isOk(Response response) {
        return response.getCode() == Status.OK.getCode();
    }

    private boolean isHtml(Response response) {
        return
            contentTypeOf(response) != null &&
            contentTypeOf(response).startsWith(MimeTypes.TEXT_HTML);
    }

    private String contentTypeOf(Response response) {
        return response.getValue("Content-Type");
    }
}