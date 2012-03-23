package reyes.melanio.portfolio.rsswebservice.actions;

import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.deleteFeed;
import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.findById;
import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;

public class DeleteFeedAction extends AbstractAction {

	Long feedId;

	public DeleteFeedAction(Long feedId) {
		super();
		this.feedId = feedId;
	}

	@Override
	public void execute() {
		try {
			Feed feed = findById(feedId);
			if (feed != null) {
				deleteFeed(feed);
			} else {
				sendRequestedFeedNotFoundException();
			}
		} catch (RssWebServiceException e) {
			setRssWebServiceException(e);
		}
	}

}
