package reyes.melanio.portfolio.rsswebservice.actions;

import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.error.Errors;

public abstract class AbstractAction implements IAction {
	protected WebServiceResponse webServiceResponse = null;

	public AbstractAction() {
		webServiceResponse = new WebServiceResponse();
	}

	public WebServiceResponse getResponse() {
		return webServiceResponse;
	}

	protected void setRssWebServiceException(RssWebServiceException exception) {
		if (exception.getException() != null) {
			exception.getException().printStackTrace();
		}
		webServiceResponse.setResponseCode(exception.getErrorCode());
		webServiceResponse.setErrorText(exception.getErrorText());
	}

	protected void sendRequestedFeedNotFoundException() {
		webServiceResponse.setResponseCode(Errors.REQUESTED_FEED_NOT_FOUND);
		webServiceResponse.setErrorText(Errors.REQUESTED_FEED_NOT_FOUND_TEXT);
	}

	protected boolean checkFeed(Feed feed) {
		boolean result = true;
		if (feed == null) {
			result = false;
			setRssWebServiceException(new RssWebServiceException(
					Errors.FAILED_TO_RETRIEVE_FEEDITEMS,
					Errors.FAILED_TO_RETRIEVE_FEEDITEMS_TEXT, null));
		}
		return result;
	}

	public abstract void execute();

}
