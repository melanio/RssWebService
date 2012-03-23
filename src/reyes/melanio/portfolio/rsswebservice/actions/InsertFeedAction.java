package reyes.melanio.portfolio.rsswebservice.actions;

import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.findByUrl;
import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.insertFeed;

import java.net.MalformedURLException;

import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.error.Errors;

public class InsertFeedAction extends AbstractAction {

	private String url;

	public InsertFeedAction(String url) {
		super();
		this.url = url;
	}

	@Override
	public void execute() {
		try {
			Long feedId = -1L;
			Feed feed = findByUrl(url);
			if (feed != null) {
				feedId = feed.getId();
			} else {
				feed = new Feed(url);
				feedId = insertFeed(feed);
			}
			webServiceResponse.setFeedId(feedId);
		} catch (MalformedURLException e) {
			setRssWebServiceException(new RssWebServiceException(
					Errors.INVALID_URL, Errors.INVALID_URL_TEXT, e));
		} catch (RssWebServiceException e) {
			setRssWebServiceException(e);
		}
	}

}
