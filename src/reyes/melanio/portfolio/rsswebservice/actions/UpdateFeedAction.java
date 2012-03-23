package reyes.melanio.portfolio.rsswebservice.actions;

import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.findById;
import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.updateFeed;
import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.error.Errors;

public class UpdateFeedAction extends AbstractAction {

	private Feed feed;
	private String url;

	public UpdateFeedAction(Long feedId, String url) {
		super();
		this.feed = findById(feedId);
		this.url = url;
	}

	@Override
	public void execute() {
		try {
			if (feed != null) {
				feed.setURL(url);
				updateFeed(feed);
			} else {
				sendRequestedFeedNotFoundException();
			}
		} catch (Exception e) {
			setRssWebServiceException(new RssWebServiceException(
					Errors.INVALID_URL, Errors.INVALID_URL_TEXT, e));
		}
	}

}
