package com.feisystems.bham.web.filter.gzip;

import javax.servlet.ServletException;

public class GzipResponseHeadersNotModifiableException extends ServletException {

	private static final long serialVersionUID = -4571646045387603392L;

	public GzipResponseHeadersNotModifiableException(String message) {
        super(message);
    }
}
