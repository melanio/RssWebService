package reyes.melanio.portfolio.rsswebservice.actions;

import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.findById;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedItemDao;

public class RetrieveAllFeedItemsAction extends AbstractAction {

	private Feed feed;

	public RetrieveAllFeedItemsAction(Long feedId) {
		super();
		this.feed = findById(feedId);
	}

	@Override
	public void execute() {
		if (checkFeed(feed)) {
			webServiceResponse.setFeedItems(FeedItemDao.findByFeedId(feed
					.getId()));
		}

	}

}
