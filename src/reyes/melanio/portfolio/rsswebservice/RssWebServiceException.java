package reyes.melanio.portfolio.rsswebservice;

@SuppressWarnings("serial")
public class RssWebServiceException extends Exception {

	private int errorCode;
	private String errorText;
	private Exception exception;

	public RssWebServiceException(int errorCode, String errorText,
			Exception exception) {
		this.errorCode = errorCode;
		this.errorText = errorText;
		this.exception = exception;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
