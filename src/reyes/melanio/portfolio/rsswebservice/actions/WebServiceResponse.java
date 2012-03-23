package reyes.melanio.portfolio.rsswebservice.actions;

import reyes.melanio.portfolio.rsswebservice.data.FeedItem;

public class WebServiceResponse {
	private int responseCode;
	private String errorText;
	private Long feedId;
	private FeedItem[] feedItems;

	WebServiceResponse() {
		this.responseCode = 0;
		this.feedItems = new FeedItem[0];

	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public Long getFeedId() {
		return feedId;
	}

	public void setFeedId(Long feedId) {
		this.feedId = feedId;
	}

	public FeedItem[] getFeedItems() {
		return feedItems;
	}

	public void setFeedItems(FeedItem[] feedItems) {
		this.feedItems = feedItems;
	}

}
